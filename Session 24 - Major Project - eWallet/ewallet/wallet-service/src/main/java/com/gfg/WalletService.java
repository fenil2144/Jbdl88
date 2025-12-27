package com.gfg;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.gfg.CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_KEY;
import static com.gfg.CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_VALUE;
import static com.gfg.CommonConstants.USER_CREATED_TOPIC_PHONE_NUMBER;
import static com.gfg.CommonConstants.USER_CREATED_TOPIC_USER_ID;

@Slf4j
@Service
public class WalletService {

    @Autowired
    WalletRepositoryInterf walletRepositoryInterf;

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
}
