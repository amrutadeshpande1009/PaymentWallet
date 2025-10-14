package com.paymentWallet.paymentWallet.dto;

import lombok.Data;

@Data
public class AddAmountDTO {
    private Long userId;
    private double amount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}


