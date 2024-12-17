package com.major_project.ewallet.pocketbook.model;

import lombok.Data;

@Data
public class PendingTransaction {

    Long senderId;
    Long receiverId;
    Double amount;
    Long id;
}
