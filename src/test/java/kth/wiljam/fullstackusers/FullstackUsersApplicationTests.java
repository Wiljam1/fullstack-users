package kth.wiljam.fullstackusers;

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

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;
    /*
    @Test
    void newUser() {
        User inputUser = new User();
        when(userService.create(any(User.class))).thenReturn(inputUser);

        User createdUser = userController.newUser(inputUser);

        assertNotNull(createdUser);
        verify(userService, times(1)).create(inputUser);
    }

    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(users);

        List<User> result = userController.getAllUsers();

        assertEquals(users, result);
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getAllPatients() {
        List<User> patients = Arrays.asList(new User(), new User());
        when(userService.getUsersWithPatientIdNotNull()).thenReturn(patients);

        List<User> result = userController.getAllPatients();

        assertEquals(patients, result);
        verify(userService, times(1)).getUsersWithPatientIdNotNull();
    }

    @Test
    void getUserById() {
        long userId = 1L;
        User user = new User();
        when(userService.getById(userId)).thenReturn(user);

        User result = userController.getUserById(userId);

        assertEquals(user, result);
        verify(userService, times(1)).getById(userId);
    }

    @Test
    void getUserByUsername() {
        String username = "testUser";
        User user = new User();
        when(userService.getByUsername(username)).thenReturn(user);

        User result = userController.getUserByUsername(username);

        assertEquals(user, result);
        verify(userService, times(1)).getByUsername(username);
    }

    @Test
    void checkLogin_ValidUser() {
        User inputUser = new User();
        when(userService.checkValidLogin(any(User.class))).thenReturn(inputUser);

        ResponseEntity<User> response = userController.checkLogin(inputUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(inputUser, response.getBody());
        verify(userService, times(1)).checkValidLogin(inputUser);
    }

    @Test
    void checkLogin_InvalidUser() {
        User inputUser = new User();
        when(userService.checkValidLogin(any(User.class))).thenThrow(new NoSuchElementException());

        ResponseEntity<User> response = userController.checkLogin(inputUser);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).checkValidLogin(inputUser);
    }
    */
}
