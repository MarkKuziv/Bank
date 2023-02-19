package com.example.bank.Services;

import com.example.bank.Model.Bank;
import com.example.bank.Model.Card;
import com.example.bank.Model.User;
import com.example.bank.Repositories.BankRepository;
import com.example.bank.Repositories.CardRepository;
import com.example.bank.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;

    private final BankRepository bankRepository;

    private final CardRepository cardRepository;

    public UserService(UserRepository userRepository, BankRepository bankRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
        this.cardRepository = cardRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            LOGGER.info("User with " + id + " not found");
            return null;
        }
        return user;
    }

    public ResponseEntity<String> addUser(User user) {
        User userMaybe = userRepository.findUserByIdentifierCode(user.getIdentifierCode());
        Card card = cardRepository.findCardByCardNumber(user.getCard().getCardNumber());
        Bank bank = bankRepository.findBankByBankName(user.getBank().getBankName());

        if (userMaybe != null) {
            LOGGER.info("User found");
            return new ResponseEntity<>("User found", HttpStatus.OK);
        }

        if (card != null) {
            LOGGER.info("Card found");
            return new ResponseEntity<>("card found", HttpStatus.OK);
        }

        if (bank == null) {
            userRepository.save(user);
        } else {
            user.setBank(bank);
            userRepository.save(user);
        }
        LOGGER.info("Added user");
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    public void deleteUserById(Long id) {
        LOGGER.info("User deleted");
       userRepository.deleteById(id);
    }

    public void updateUser(User newUser) {
        User user = userRepository.findUserById(newUser.getId());
        update(user, newUser);
        LOGGER.info("Updated with: " + newUser.getId());
        userRepository.save(user);

    }

    public void update(User user, User newUser) {
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setDateBirth(newUser.getDateBirth());
        user.setCity(newUser.getCity());
        user.setNumber(user.getNumber());
        user.setIdentifierCode(user.getIdentifierCode());
    }

    public ResponseEntity<String> addMoney(long summa, Card card){
        if (summa <= 0){
            return new ResponseEntity<>("Such a balance cannot be added", HttpStatus.OK);
        }
        card.setBalance(card.getBalance() + summa);
        cardRepository.save(card);
        return new ResponseEntity<>("Added: " + summa, HttpStatus.OK);

    }

    public ResponseEntity<String> minusMoney(long summa, Card card){
        if (summa > card.getBalance()){
            return new ResponseEntity<>("Not enough money", HttpStatus.OK);
        }
        long balance = card.getBalance() - summa;
        card.setBalance(balance);
        cardRepository.save(card);
        return new ResponseEntity<>("Withdraw: " + summa, HttpStatus.OK);
    }

    public User findUserByIdentifier(long identifier) {
        User userByIdentifierCode = userRepository.findUserByIdentifierCode(identifier);
        if (userByIdentifierCode == null){
            LOGGER.info("user not found");
            return null;
        }
        return userByIdentifierCode;
    }

    public ResponseEntity<String> moneyTransfer(int fromCardNumber, int toCardNumber, int summa){
        Card cardFrom = cardRepository.findCardByCardNumber(fromCardNumber);
        Card cardTo = cardRepository.findCardByCardNumber(toCardNumber);

        long balanceFrom = cardFrom.getBalance();
        long balanceTo = cardTo.getBalance();
        if (cardFrom.getBalance() >= summa) {
            long resultFrom = balanceFrom - summa;
            cardFrom.setBalance(resultFrom);

            long resultTo = balanceTo + summa;
            cardTo.setBalance(resultTo);

            cardRepository.save(cardFrom);
            cardRepository.save(cardTo);
            return new ResponseEntity<>("Money transfer successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Money transfer not successful", HttpStatus.OK);
    }
}
