package com.emergency_link.emergency_link.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "user_info")
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_info;
    // private Timestamp created_at;
}