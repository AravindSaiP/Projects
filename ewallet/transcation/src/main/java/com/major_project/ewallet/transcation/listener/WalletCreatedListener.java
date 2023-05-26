package com.major_project.ewallet.transcation.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.major_project.ewallet.transcation.entity.Transaction;
import com.major_project.ewallet.transcation.model.NewWalletRequest;
import com.major_project.ewallet.transcation.service.TransactionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WalletCreatedListener {
    private static final String NEW_WALLET_CREATED = "NEW_WALLET_CREATED";

    private static final String TOPUP_WALLET = "TOPUP_WALLET";

    @Autowired
    TransactionService transactionService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    @SneakyThrows
    @KafkaListener(topics = {NEW_WALLET_CREATED}, groupId = "transaction_group")
    public void processTransaction(@Payload String message){
        log.info(" *************** NEW WALLET CREATED LISTENER :: start");
        /**
         * create a transaction in pending
         *          --- topup wallet
         *                      -- mark transaction success
         */
        NewWalletRequest newWalletRequest = objectMapper.readValue(message, NewWalletRequest.class);
        Transaction newPendingSystemTransaction = transactionService.createNewPendingSystemTransaction(newWalletRequest);
        String messageOutbox = objectMapper.writeValueAsString(newPendingSystemTransaction);
        kafkaTemplate.send(TOPUP_WALLET, messageOutbox);
        log.info(" *************** NEW WALLET CREATED LISTENER :: end");
    }




}
