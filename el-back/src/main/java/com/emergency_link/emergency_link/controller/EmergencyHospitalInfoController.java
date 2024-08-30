package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.EmergencyHospitalInfoDto;
import com.emergency_link.emergency_link.repository.EmergencyHospitalInfoRepository;
import com.emergency_link.emergency_link.security.auth.CustomUserDetails;
import com.emergency_link.emergency_link.service.EmergencyHospitalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/emergencyHospitalInfo", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmergencyHospitalInfoController {
    private final EmergencyHospitalInfoService emergencyHospitalInfoService;

    @GetMapping("/{hpid}")
    public ResponseEntity<?> readOne(@PathVariable String hpid,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try {
            EmergencyHospitalInfoDto emergencyHospitalInfoDto = emergencyHospitalInfoService.readOne(customUserDetails.getUserInfo().getUserId(), hpid);
            if (emergencyHospitalInfoDto == null) {
                System.out.println("일치하는 병원 정보가 없음");
                return ResponseEntity.badRequest().body("일치하는 병원 정보가 없음");
            }
            return ResponseEntity.ok(emergencyHospitalInfoDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
