package com.iamstevol.facebookCloneSpringMVC.service;

import com.iamstevol.facebookCloneSpringMVC.entity.User;
import com.iamstevol.facebookCloneSpringMVC.repository.UserRepository;
import com.iamstevol.facebookCloneSpringMVC.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;
    private User user;
    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstName("user");
        user.setLastName("Decagon");
        user.setEmail("user@gmail.com");
        user.setGender("Male");
        user.setPassword("1234");

    }

    @Test
    public void whenValidUser_addUser() {
        //mock the repository
        when(userRepository.save(any(User.class))).thenReturn(user);

        //call the method
        User addedUser = userServiceImpl.addUser(user).getData();

        //do your assertion to confirm if the save method of the repository has been called exactly once
        verify(userRepository, times(1)).save(any(User.class));

        //to confirm if the returned user object added is not null, this confirms if te method as saved a user
        assertNotNull(addedUser);

        //checks that the returned user object is equal to the object that was passed as argument
        assertEquals(user, addedUser);
    }

    @Test
    public void whenValidUser_thenLoginUser() {
        Mockito.when(userRepository.getUserByEmailAndPassword(anyString(), anyString())).thenReturn(user);
        User loginuser = userServiceImpl.logInUser(user).getData();
        verify(userRepository, times(1)).getUserByEmailAndPassword(anyString(),anyString());
        assertNotNull(loginuser);
        assertEquals(user, loginuser);
    }
}