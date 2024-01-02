package kth.wiljam.fullstackusers;

import com.nimbusds.jose.proc.SecurityContext;
import kth.wiljam.fullstackusers.controller.UserController;
import kth.wiljam.fullstackusers.model.User;
import kth.wiljam.fullstackusers.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    void newUser() {
        User inputUser = new User();
        when(userService.create(any(User.class))).thenReturn(inputUser);

        User createdUser = userController.newUser(inputUser);

        assertNotNull(createdUser);
        verify(userService, times(1)).create(inputUser);
    }

    @Test
    void getAllUsers() {
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(expectedUsers);

        Map<String, Object> claims = Map.of(
                JwtClaimNames.SUB, "user",
                "realm_access", Map.of("roles", List.of("default-roles-patient-keycloak"))
        );
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claims(claimMap -> claimMap.putAll(claims))
                .build();

        JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt, Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<User> result = userController.getAllUsers();

        assertEquals(expectedUsers, result);
        verify(userService, times(1)).getAllUsers();

        SecurityContextHolder.clearContext();
    }

    @Test
    @WithMockUser
    void getAllPatients() {
        List<User> patients = Arrays.asList(new User(), new User());
        when(userService.getUsersWithPatientIdNotNull()).thenReturn(patients);

        List<User> result = userController.getAllPatients();

        assertEquals(patients, result);
        verify(userService, times(1)).getUsersWithPatientIdNotNull();
    }

    @Test
    @WithMockUser
    void getUserById() {
        long userId = 1L;
        User user = new User();
        when(userService.getById(userId)).thenReturn(user);

        User result = userController.getUserById(userId);

        assertEquals(user, result);
        verify(userService, times(1)).getById(userId);
    }

    @Test
    @WithMockUser
    void getUserByUsername() {
        String username = "testUser";
        User user = new User();
        when(userService.getByUsername(username)).thenReturn(user);

        User result = userController.getUserByUsername(username);

        assertEquals(user, result);
        verify(userService, times(1)).getByUsername(username);
    }

    @Test
    @WithMockUser
    void checkLogin_ValidUser() {
        User inputUser = new User();
        when(userService.checkValidLogin(any(User.class))).thenReturn(inputUser);

        ResponseEntity<User> response = userController.checkLogin(inputUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(inputUser, response.getBody());
        verify(userService, times(1)).checkValidLogin(inputUser);
    }

    @Test
    @WithMockUser
    void checkLogin_InvalidUser() {
        User inputUser = new User();
        when(userService.checkValidLogin(any(User.class))).thenThrow(new NoSuchElementException());

        ResponseEntity<User> response = userController.checkLogin(inputUser);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).checkValidLogin(inputUser);
    }
}
