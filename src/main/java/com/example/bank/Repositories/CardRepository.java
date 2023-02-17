package com.example.bank.Repositories;

import com.example.bank.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findCardByCardNumber(int cardNumber);
}
