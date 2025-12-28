package com.gfg;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.gfg.CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT;
import static com.gfg.CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER;
import static com.gfg.CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER;
import static com.gfg.CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTION_ID;
import static com.gfg.CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_KEY;
import static com.gfg.CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_VALUE;
import static com.gfg.CommonConstants.USER_CREATED_TOPIC_PHONE_NUMBER;
import static com.gfg.CommonConstants.USER_CREATED_TOPIC_USER_ID;
import static com.gfg.CommonConstants.WALLET_UPDATED_TOPIC_FALIED;
import static com.gfg.CommonConstants.WALLET_UPDATED_TOPIC_STATUS;
import static com.gfg.CommonConstants.WALLET_UPDATED_TOPIC_SUCCESS;

@Slf4j
@Service
public class WalletService {

    @Autowired
    WalletRepositoryInterf walletRepositoryInterf;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = CommonConstants.USER_CREATED_TOPIC, groupId = "ewallet_group")
    public void createWallet(String message) throws ParseException {
        log.debug("In WalletService.createWallet with message: {}", message);

        JSONObject data = (JSONObject) new JSONParser().parse(message);

        Long userId = (Long) data.get(USER_CREATED_TOPIC_USER_ID);
        String phoneNumber = (String) data.get(USER_CREATED_TOPIC_PHONE_NUMBER);
        String identifierKey = (String) data.get(USER_CREATED_TOPIC_IDENTIFIER_KEY);
        String identifierValue = (String) data.get(USER_CREATED_TOPIC_IDENTIFIER_VALUE);

        Wallet wallet = Wallet.builder()
                .userId(userId).phoneNumber(phoneNumber)
                .userIdentifier(UserIdentifier.valueOf(identifierKey)).identifierValue(identifierValue)
                .balance(20.0)
                .build();

        walletRepositoryInterf.save(wallet);
        log.debug("Out WalletService.createWallet");
    }

    @KafkaListener(topics = CommonConstants.TRANSACTION_CREATED_TOPIC, groupId = "ewallet_group")
    public void updateWalletForTransaction(String message) throws ParseException {
        log.debug("In WalletService.updateWalletForTransaction with message: {}", message);

        JSONObject data = (JSONObject) new JSONParser().parse(message);
        String senderId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER);
        String receiverId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER);
        Double amount = (Double) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT);
        String transactionId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTION_ID);

        // Validate if sender and receiver wallet's are in active state
        Wallet senderWallet = walletRepositoryInterf.findByPhoneNumber(senderId);
        Wallet receiverWallet = walletRepositoryInterf.findByPhoneNumber(receiverId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TRANSACTION_CREATED_TOPIC_SENDER, senderId);
        jsonObject.put(TRANSACTION_CREATED_TOPIC_RECEIVER, receiverId);
        jsonObject.put(TRANSACTION_CREATED_TOPIC_AMOUNT, amount);
        jsonObject.put(TRANSACTION_CREATED_TOPIC_TRANSACTION_ID, transactionId);

        if (senderWallet == null || receiverWallet == null || senderWallet.getBalance() < amount) {
            jsonObject.put(WALLET_UPDATED_TOPIC_STATUS, WALLET_UPDATED_TOPIC_FALIED);
        } else{
            // debiting the amount from sender's wallet
            walletRepositoryInterf.updateWallet(senderId, 0-amount);

            // crediting the amount to receiver's wallet
            walletRepositoryInterf.updateWallet(receiverId, amount);
            jsonObject.put(WALLET_UPDATED_TOPIC_STATUS, WALLET_UPDATED_TOPIC_SUCCESS);

        }

        kafkaTemplate.send(CommonConstants.WALLET_UPDATED_TOPIC, objectMapper.writeValueAsString(jsonObject));
        log.debug("Out WalletService.updateWalletForTransaction");
    }
}
