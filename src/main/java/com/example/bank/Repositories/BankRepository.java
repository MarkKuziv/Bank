package com.example.bank.Repositories;

import com.example.bank.Model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    @Override
    Optional<Bank> findById(Long aLong);

    Bank findBankByBankName(String bankName);
}
