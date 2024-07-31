package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.LoginData;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public LoginController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<String>  login(@RequestBody LoginData loginData){
        System.out.println(loginData.getUserId()+" "+loginData.getPwd());
        if(userInfoRepository.findByUserIdAndUserPwd(loginData.getUserId(),loginData.getPwd()).isPresent()){
            System.out.println("exist");
            return ResponseEntity.ok("Login complete");
        }
        System.out.println("Not exist");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login fail");
    }
}
