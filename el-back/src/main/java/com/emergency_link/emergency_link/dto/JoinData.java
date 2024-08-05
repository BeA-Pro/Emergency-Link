package com.emergency_link.emergency_link.dto;

import lombok.Getter;

@Getter
public class JoinData {
    private final String userId;
    private final String pwd;
    private final Integer category;

    public JoinData(String userId, String pwd, Integer category){
        this.userId = userId;
        this.pwd = pwd;
        this.category = category;
    }
}
