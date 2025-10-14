package com.paymentWallet.paymentWallet.repository;

import com.paymentWallet.paymentWallet.entity.User;
import com.paymentWallet.paymentWallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>  {
    Optional<Wallet> findByUserId(Long userId);
}

