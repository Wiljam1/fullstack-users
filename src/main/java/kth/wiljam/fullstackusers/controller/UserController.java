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

    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        System.out.println("Received JSON payload: " + user);
        return userService.create(user);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/patients")
    List<User> getAllPatients() {
        return userService.getUsersWithPatientIdNotNull();
    }

    @GetMapping("user/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("userInfo/{username}")
    User getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/login")
    public ResponseEntity<User> checkLogin(@RequestBody User user) {
        try {
            User validUser = userService.checkValidLogin(user);
            return ResponseEntity.ok(validUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
