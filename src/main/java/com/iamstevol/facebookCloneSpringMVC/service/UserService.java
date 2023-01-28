package com.iamstevol.facebookCloneSpringMVC.service;

import com.iamstevol.facebookCloneSpringMVC.dto.ResponseDto;
import com.iamstevol.facebookCloneSpringMVC.entity.User;

public interface UserService {

    ResponseDto addUser(User user);
    ResponseDto logInUser(User user);
}
