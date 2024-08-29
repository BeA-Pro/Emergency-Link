package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.UserInfoDto;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import com.emergency_link.emergency_link.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/join")
public class JoinController {
    private final UserInfoRepository userInfoRepository;
    private final UserInfoService userInfoService;

    @PostMapping("/userInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> joinUserInfo(@RequestBody UserInfoDto userInfoDto) {
        boolean exist = userInfoRepository.existsByUserId(userInfoDto.getUserId());

        if (!exist) {
            UserInfoDto createdUserInfoDto = userInfoService.join(userInfoDto, false);
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

    @PostMapping("/hospitalUserInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> joinHospitalUserInfo(@RequestBody UserInfoDto userInfoDto) {
        boolean exist = userInfoRepository.existsByUserId(userInfoDto.getUserId());

        if (!exist) {
            UserInfoDto createdHospitalUserInfoDto = userInfoService.join(userInfoDto, true);
            try {
                if (createdHospitalUserInfoDto != null) {
                    System.out.println("병원 관계자 가입 성공!");
                    return ResponseEntity.ok(createdHospitalUserInfoDto);
                } else {
                    System.out.println("병원 관계자 가입 실패");
                    return ResponseEntity.badRequest().body("병원 관계자 가입 데이터가 유효하지 않음");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 가입된 병원 관계자 데이터");
        }
    }

}