package com.emergency_link.emergency_link.dto;

import lombok.Getter;

@Getter
public class JoinData {
    private final String email;
    private final String pwd;
    private final Integer category;

    public JoinData(String email, String pwd, Integer category){
        this.email = email;
        this.pwd = pwd;
        this.category = category;
    }
}
