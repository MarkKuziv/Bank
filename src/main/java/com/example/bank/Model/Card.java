package com.example.bank.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long id;
    private int cardNumber;
    private int cvvCode;
    private String duration;
    private long balance;

    public Card(int cardNumber, int cvvCode, String duration, long balance) {
        this.cardNumber = cardNumber;
        this.cvvCode = cvvCode;
        this.duration = duration;
        this.balance = balance;
    }

    public Card() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(int cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

}
