package com.major_project.ewallet.pocketbook.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
@Data
public class sendRequestDto {

    @NotBlank
    private Long userId;

    @NotBlank
    private Long senderId;

    @NotBlank
    private Double amount;
}
