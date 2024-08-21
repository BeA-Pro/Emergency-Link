package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.LoginData;
import com.emergency_link.emergency_link.entity.UserInfo;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import com.emergency_link.emergency_link.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

//    private final UserInfoRepository userInfoRepository;
//    private final JwtUtil jwtUtil;
//
//    @Autowired
//    public LoginController(UserInfoRepository userInfoRepository, JwtUtil jwtUtil) {
//        this.userInfoRepository = userInfoRepository;
//        this.jwtUtil = jwtUtil;
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginData loginData){
//        System.out.println(loginData.getUserId()+" "+loginData.getPwd());
//        Optional<UserInfo> userInfo = userInfoRepository.findByUserIdAndUserPwd(loginData.getUserId(), loginData.getPwd());
//        Map<String, String> responseBody = new HashMap<>();
//        if(userInfo.isPresent()){
//            System.out.println("exist");
//            String token = jwtUtil.createToken(userInfo.get().getUserId(), 1);
//
//            responseBody.put("status", "success");
//            responseBody.put("token", token);
//
//            return ResponseEntity.ok().body(responseBody);
//        }
//        System.out.println("Not exist");
//
//        responseBody.put("status", "fail");
//        responseBody.put("message", "Login fail");
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
//    }
}
