package com.cards.cardDtails.modal;


import com.cards.cardDtails.config.AesEncryptor;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class CardDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = AesEncryptor.class)
    private String cardHolderName;

    @Convert(converter = AesEncryptor.class)
    private Integer cvv;

    @Convert(converter = AesEncryptor.class)
    private Double amount;

    @Convert(converter = AesEncryptor.class)
    private Boolean isActive;
}