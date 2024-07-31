package com.emergency_link.emergency_link.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "UserInfo")
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;
    private String userPwd;
    private String userName;
    private String userInfo;
    private Integer category;
}