package kth.wiljam.fullstackusers.controller;

import kth.wiljam.fullstackusers.model.User;
import kth.wiljam.fullstackusers.services.UserService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Map;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    //Change these to staff/doctor/patient/default-roles-patient-keycloak
    //depending on what authorization is required
    //@PreAuthorize("hasRole('default-roles-patient-keycloak')")
    //unit testerna failar med denna tror jag

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

    // Checks if user has correct role before returning all users
    // Tror man behöver auth token för allt man gör mot API ändå som det är nu, behöver nog inte göra extra-checkar
    @GetMapping(value = "/users", produces = "application/json")
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof Jwt jwt) {

            // Debugging
            System.out.println("Token Claims: " + jwt.getClaims());
            System.out.println("Issuer: " + jwt.getIssuer());
            System.out.println("Subject: " + jwt.getSubject());
            System.out.println("Issued At: " + jwt.getIssuedAt());
            System.out.println("Expiration: " + jwt.getExpiresAt());

            Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
            List<String> roles = (List<String>) realmAccess.get("roles");

            if (roles.contains("default-roles-patient-keycloak")) {
                return userService.getAllUsers();
            }
        }
        throw new AccessDeniedException("Not authorized");
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
