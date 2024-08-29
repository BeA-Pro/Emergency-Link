package com.emergency_link.emergency_link.service;

import com.emergency_link.emergency_link.dto.UserInfoDto;
import com.emergency_link.emergency_link.entity.EmergencyHospitalInfo;
import com.emergency_link.emergency_link.entity.UserInfo;
import com.emergency_link.emergency_link.repository.EmergencyHospitalInfoRepository;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmergencyHospitalInfoRepository emergencyHospitalInfoRepository;

    @Transactional
    public UserInfoDto join(UserInfoDto userInfoDto, boolean isHospitalUser) {
        String encodedPassword = passwordEncoder.encode(userInfoDto.getUserPwd());

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userInfoDto.getUserId());
        userInfo.setUserPwd(encodedPassword);
        userInfo.setUserName(userInfoDto.getUserName());
        if (!isHospitalUser) {
            userInfo.setRole("ROLE_USER");
        }
        else {
            userInfo.setRole("ROLE_HOSPITAL");

            // 요청 받은 병원 정보가 유효한지 확인
            Optional<EmergencyHospitalInfo> emergencyHospitalInfoOptional = emergencyHospitalInfoRepository.findByHpid(userInfoDto.getHpid());
            if (!emergencyHospitalInfoOptional.isPresent()) return null;

            EmergencyHospitalInfo emergencyHospitalInfo = emergencyHospitalInfoOptional.get();
            userInfo.setHpid(userInfoDto.getHpid());

            // userInfo, emergencyHospitalInfo 양방향 설정
            userInfo.setEmergencyHospitalInfo(emergencyHospitalInfo);
        }

        userInfoRepository.save(userInfo);
        UserInfoDto createdUserDto = new UserInfoDto(userInfo);
        return createdUserDto;
    }
}
