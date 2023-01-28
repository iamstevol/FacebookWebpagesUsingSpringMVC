package com.iamstevol.facebookCloneSpringMVC.dto;

import com.iamstevol.facebookCloneSpringMVC.entity.User;
import lombok.Data;

@Data
public class ResponseDto {

    private String message;
    private User data;
    private boolean status;
}
