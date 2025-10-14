package com.paymentWallet.paymentWallet.dto;


import java.math.BigDecimal;

public class FundTransferRequest {
    private Long fromUserId;
    private Long toUserId;
    private BigDecimal amount;

    // Getters and Setters
    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}



