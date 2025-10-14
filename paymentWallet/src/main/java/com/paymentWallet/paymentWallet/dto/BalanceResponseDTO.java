package com.paymentWallet.paymentWallet.dto;

import lombok.Data;

@Data
public class BalanceResponseDTO {
    private Long userId;
    private double balance;

    public BalanceResponseDTO(Long userId, double balance) {
    }
}
