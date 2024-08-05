package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.JoinData;
import com.emergency_link.emergency_link.entity.UserInfo;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class JoinController {
    private final UserInfoRepository userInfoRepository;

    public JoinController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<Map<String, String>> join(@RequestBody JoinData joinData) {
        Map<String, String> responseBody = new HashMap<>();
        if (userInfoRepository.findByUserId(joinData.getUserId()).isPresent()) {
            responseBody.put("status", "fail");
            responseBody.put("message", "User ID already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(joinData.getUserId());
        userInfo.setUserPwd(joinData.getPwd());
        userInfo.setCategory(joinData.getCategory());
        userInfo.setUserInfo(null);
        userInfo.setUserName(null);
        userInfoRepository.save(userInfo);

        responseBody.put("status", "success");
        return ResponseEntity.ok().body(responseBody);
    }
}