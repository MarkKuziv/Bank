package com.example.bank.Model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String dateBirth;

    @Column
    private String city;

    @Column
    private int number;

    @Column
    private long identifierCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;

    public User(String firstName, String lastName, String dateBirth, String city, int number, long identifierCode, Bank bank, Card card) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.city = city;
        this.number = number;
        this.identifierCode = identifierCode;
        this.bank = bank;
        this.card = card;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdentifierCode() {
        return identifierCode;
    }

    public void setIdentifierCode(long identifierCode) {
        this.identifierCode = identifierCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", city='" + city + '\'' +
                ", number=" + number +
                ", identifierCode=" + identifierCode +
                ", bank=" + bank +
                ", card=" + card +
                '}';
    }
}
