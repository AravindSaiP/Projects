package com.major_project.ewallet.transcation.model;

import lombok.Data;

@Data
public class TransientTransaction {
    Long senderId;
    Long receiverId;
    Double amount;
    Long id;
}
