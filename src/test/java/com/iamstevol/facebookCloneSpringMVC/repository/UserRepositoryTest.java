package com.iamstevol.facebookCloneSpringMVC.repository;

import com.iamstevol.facebookCloneSpringMVC.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @MockBean
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        User user = User.builder()
                .email("stevol2015@gmail.com")
                .datOfBirth("21")
                .firstName("ol")
                .gender("m")
                .lastName("sd")
                .password("ddfs")
                .build();
        Mockito.when(userRepository.getUserByEmail("stevol2015@gmail.com")).thenReturn(user);
    }

    @Test
    void whenValidDeptName_thenDeptShouldFound() {
        String email = "stevol2015@gmail.com";
        User found = userRepository.getUserByEmail(email);
        assertEquals(email, found.getEmail());
    }
}