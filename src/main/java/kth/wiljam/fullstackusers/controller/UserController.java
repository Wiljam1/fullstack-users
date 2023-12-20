package kth.wiljam.fullstackusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kth.wiljam.fullstackusers.model.*;
import kth.wiljam.fullstackusers.services.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    User newUser(@RequestBody User user) {
        System.out.println("Received JSON payload: " + user);
        return userService.create(user);
    }

    @GetMapping(value = "/users", produces = "application/json")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/patients", produces = "application/json")
    List<User> getAllPatients() {
        return userService.getUsersWithPatientIdNotNull();
    }

    @GetMapping(value = "user/{id}", produces = "application/json")
    User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping(value = "userInfo/{username}", produces = "application/json")
    User getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> checkLogin(@RequestBody User user) {
        try {
            User validUser = userService.checkValidLogin(user);
            return ResponseEntity.ok(validUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
