package com.iamstevol.facebookCloneSpringMVC.service.impl;

import com.iamstevol.facebookCloneSpringMVC.dto.ResponseDto;
import com.iamstevol.facebookCloneSpringMVC.entity.User;
import com.iamstevol.facebookCloneSpringMVC.exception.CustomException;
import com.iamstevol.facebookCloneSpringMVC.repository.UserRepository;
import com.iamstevol.facebookCloneSpringMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseDto addUser(User user) {
        Optional<User> userDb = Optional.ofNullable(userRepository.getUserByEmail(user.getEmail()));
        ResponseDto response = new ResponseDto();

        try{
            if(userDb.isPresent()) {
                throw new CustomException("Email already exist!!!");
            }
            User savedUser = userRepository.save(user);
            response.setData(savedUser);
            response.setMessage("Registration Successful!!!");
            response.setStatus(true);
            return response;

        } catch(Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(false);
            return response;
        }
    }

    @Override
    public ResponseDto logInUser(User user) {
        Optional<User> userDb = Optional.ofNullable(userRepository.getUserByEmailAndPassword(user.getEmail(), user.getPassword()));
        ResponseDto response = new ResponseDto();

        try{
            if(userDb.isPresent()) {
                response.setStatus(true);
                response.setMessage("Login Successful!!!");
                response.setData(userDb.get());
                return  response;
            }
            response.setMessage("Invalid login details!!!");
            response.setStatus(false);
            return response;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
