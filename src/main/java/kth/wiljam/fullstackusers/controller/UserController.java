package kth.wiljam.fullstackusers.controller;

import kth.wiljam.fullstackusers.model.User;
import kth.wiljam.fullstackusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    public User newUser(@RequestBody User user) {
        System.out.println("Received JSON payload: " + user);

        try {
            User createdUser = userService.create(user);
            System.out.println("User created: " + createdUser);
            return createdUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/users", produces = "application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/patients", produces = "application/json")
    public List<User> getAllPatients() {
        return userService.getUsersWithPatientIdNotNull();
    }

    @GetMapping(value = "user/{id}", produces = "application/json")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping(value = "userInfo/{username}", produces = "application/json")
    public User getUserByUsername(@PathVariable String username) {
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
