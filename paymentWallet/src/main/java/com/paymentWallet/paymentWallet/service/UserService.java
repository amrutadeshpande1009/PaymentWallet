package com.paymentWallet.paymentWallet.service;

import com.paymentWallet.paymentWallet.dto.UserRegistrationDTO;
import com.paymentWallet.paymentWallet.entity.User;
import com.paymentWallet.paymentWallet.entity.Wallet;
import com.paymentWallet.paymentWallet.exception.ResourceNotFoundException;
import com.paymentWallet.paymentWallet.repository.UserRepository;
import com.paymentWallet.paymentWallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired private UserRepository userRepository;
    @Autowired private WalletRepository walletRepository;

    public User registerUser(UserRegistrationDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmailId(userDto.getEmailId());
        return userRepository.save(user);
    }
    public double getWalletBalance(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(()
                -> new ResourceNotFoundException("Wallet not found for userId: " + userId));

        return wallet.getBalance();




    }
}


