package com.paymentWallet.paymentWallet.dto;


import java.math.BigDecimal;

public class FundTransferResponse {
    private String message;
    private BigDecimal remainingBalance;

    public FundTransferResponse(String message, double remainingBalance) {
        this.message = message;
        this.remainingBalance = BigDecimal.valueOf(remainingBalance);
    }

    public FundTransferResponse() {

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
}

