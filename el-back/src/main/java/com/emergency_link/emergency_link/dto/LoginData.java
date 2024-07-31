package com.emergency_link.emergency_link.dto;

import lombok.Getter;

@Getter
public class LoginData {
    private final String userId;
    private final String pwd;

    public LoginData(String userId, String pwd){
        this.userId = userId;
        this.pwd = pwd;
    }

}
