package com.paymentWallet.paymentWallet.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String abbreviation;

    @OneToMany(mappedBy = "currency")
    private List<Wallet> wallets;

}
