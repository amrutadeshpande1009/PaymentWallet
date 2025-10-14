package com.paymentWallet.paymentWallet.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;
    private double amount;
    private LocalDate date;
    private LocalTime time;


    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;


}
