package com.gfg;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import org.apache.kafka.common.protocol.types.Field;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TransactionRepositoryInterf transactionRepositoryInterf;

    public String transact(String sender, String receiver, double amount, String reason) {

        // Validate to verify sender, receiver and sender's balance, etc
        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .senderId(sender).receiverId(receiver).amount(amount)
                .reason(reason).transactionStatusEnum(TransactionStatusEnum.PENDING)
                .build();
        transactionRepositoryInterf.save(transaction);

        // Publish the event after initializing the transaction which will be listened by all consumers
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER, sender);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER, receiver);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT, amount);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTION_ID, transaction.getTransactionId());

        kafkaTemplate.send(CommonConstants.TRANSACTION_CREATED_TOPIC, objectMapper.writeValueAsString(jsonObject));
        return transaction.getTransactionId();
    }

    @KafkaListener(topics = CommonConstants.WALLET_UPDATED_TOPIC, groupId = "ewallet_group")
    public void updateTransaction(String message) throws ParseException {
        log.debug("In TransactionService.updateTransaction with message: {}", message);

        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String senderId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER);
        String receiverId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER);
        Double amount = data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT);
        String transactionId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTION_ID);
        String walletUpdatedStatus = (String) data.get(CommonConstants.WALLET_UPDATED_TOPIC_STATUS);


        TransactionStatusEnum transactionStatusEnum;
        String receiverEmail = null;
        JSONObject senderObj = getUserFromUserService(senderId);
        String senderEmail = senderObj.get("email");


        if (walletUpdatedStatus.equalsIgnoreCase(CommonConstants.WALLET_UPDATED_TOPIC_SUCCESS)) {
            JSONObject receiverObj = getUserFromUserService(receiverId);
            receiverEmail = (String) receiverObj.get("email");
            transactionStatusEnum = TransactionStatusEnum.SUCCESS;
        } else {
            transactionStatusEnum = TransactionStatusEnum.FAILED;
        }

        String senderMessage = "Hi, your transaction with id: "+ transactionId + "got "+ walletUpdatedStatus;

        JSONObject senderEmailObj = new JSONObject();
        senderEmailObj.put("email", senderEmail);
        senderEmailObj.put("message", senderMessage);

        kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC, objectMapper.writeValueAsString(senderEmailObj));

        if(walletUpdatedStatus.equalsIgnoreCase(CommonConstants.WALLET_UPDATED_TOPIC_SUCCESS)){
            String receiverMessage = "Hi, You have received an amount od Rs. "+ amount + " from "+ senderId+
                    "in your wallet linked with phone number "+ receiverId;

            JSONObject receiverObj = new JSONObject();
            receiverObj.put("email", receiverEmail);
            receiverObj.put("message", receiverMessage);
            kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC, objectMapper.writeValueAsString(receiverObj));
        }
    }
}
