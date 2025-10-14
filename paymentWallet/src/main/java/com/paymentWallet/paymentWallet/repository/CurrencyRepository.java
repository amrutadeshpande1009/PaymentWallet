package com.paymentWallet.paymentWallet.repository;

import com.paymentWallet.paymentWallet.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {}
