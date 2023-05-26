package com.major_project.ewallet.transcation.model;

import com.major_project.ewallet.transcation.entity.Transaction;
import lombok.Data;

@Data
public class NewWalletRequest {

    Long id;
    Long userId;

    public Transaction toTransaction(){
        return Transaction.builder()
                .receiverId(userId)
                .build();
    }
}
