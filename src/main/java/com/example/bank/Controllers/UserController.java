package com.example.bank.Controllers;

import com.example.bank.Enum.CardEnum;
import com.example.bank.Model.User;
import com.example.bank.Services.UserService;
import com.example.bank.dto.CardDto;
import com.example.bank.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/users/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/users/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/card")
    public ResponseEntity<String> cardOperation(@RequestBody UserDto userDto) {
        User userByIdentifier = userService.findUserByIdentifier(userDto.getIdentificationCode());
        if (userDto.getCardEnum().name().equalsIgnoreCase(CardEnum.ADD.name())) {
            return userService.addMoney(userDto.getSumma(), userByIdentifier.getCard());
        }
        if (userDto.getCardEnum().name().equalsIgnoreCase(CardEnum.MINUS.name())) {
            return userService.minusMoney(userDto.getSumma(), userByIdentifier.getCard());
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping(value = "/card/transfer", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> transferMoney(@RequestBody CardDto cardDto) {
        return userService.moneyTransfer(cardDto.getFromCard(), cardDto.getToCard(), cardDto.getSumma());

    }
}
