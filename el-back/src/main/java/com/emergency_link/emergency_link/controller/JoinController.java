package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.JoinData;
import com.emergency_link.emergency_link.dto.UserInfoDto;
import com.emergency_link.emergency_link.entity.UserInfo;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import com.emergency_link.emergency_link.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/join")
public class JoinController {
    private final UserInfoRepository userInfoRepository;
    private final UserInfoService userInfoService;

    @PostMapping("/userInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> join(@RequestBody UserInfoDto userInfoDto) {
        boolean exist = userInfoRepository.existsByUserId(userInfoDto.getUserId());

        if (!exist) {
            UserInfoDto createdUserInfoDto = userInfoService.join(userInfoDto);
            try {
                if (createdUserInfoDto != null) {
                    System.out.println("가입 성공!");
                    return ResponseEntity.ok(createdUserInfoDto);
                }
                else {
                    System.out.println("가입 실패");
                    return ResponseEntity.badRequest().body("회원 가입 데이터가 유효하지 않음");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
        }
    }
}