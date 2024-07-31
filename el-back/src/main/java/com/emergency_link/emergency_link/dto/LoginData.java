package com.emergency_link.emergency_link.dto;

import lombok.Getter;

@Getter
public class LoginData {
    private final String email;
    private final String pwd;

    public LoginData(String email, String pwd){
        this.email = email;
        this.pwd = pwd;
    }

}
