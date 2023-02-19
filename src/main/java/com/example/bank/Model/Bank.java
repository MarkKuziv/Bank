package com.example.bank.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String bankName;

    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<User> user;

    public Bank(List<User> user, String bankName) {
        this.bankName = bankName;
        this.user = user;
    }

    public Bank() {

    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

}
