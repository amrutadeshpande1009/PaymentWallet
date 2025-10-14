package com.paymentWallet.paymentWallet.service;

import com.paymentWallet.paymentWallet.dto.AddAmountDTO;
import com.paymentWallet.paymentWallet.dto.FundTransferRequest;
import com.paymentWallet.paymentWallet.dto.FundTransferResponse;
import com.paymentWallet.paymentWallet.entity.Wallet;
import com.paymentWallet.paymentWallet.exception.InsufficientBalanceException;
import com.paymentWallet.paymentWallet.exception.ResourceNotFoundException;
import com.paymentWallet.paymentWallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    public void addAmountToWallet(AddAmountDTO dto) {
        Wallet wallet = walletRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.setBalance(wallet.getBalance() + dto.getAmount());
        walletRepository.save(wallet);
    }

    @Transactional
    public FundTransferResponse transferFunds(FundTransferRequest request) {
        Wallet fromWallet = walletRepository.findByUserId(request.getFromUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Sender wallet not found"));

        Wallet toWallet = walletRepository.findByUserId(request.getToUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Receiver wallet not found"));

        BigDecimal amount = request.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        if (fromWallet.getBalance() < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromWallet.setBalance(fromWallet.getBalance());
        toWallet.setBalance(toWallet.getBalance());

        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);

        return new FundTransferResponse("Transfer successful", fromWallet.getBalance());
    }
}

