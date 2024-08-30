package com.emergency_link.emergency_link.service;

import com.emergency_link.emergency_link.dto.EmergencyHospitalInfoDto;
import com.emergency_link.emergency_link.entity.EmergencyHospitalInfo;
import com.emergency_link.emergency_link.entity.UserInfo;
import com.emergency_link.emergency_link.repository.EmergencyHospitalInfoRepository;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmergencyHospitalInfoService {
    private final EmergencyHospitalInfoRepository emergencyHospitalInfoRepository;
    private final UserInfoRepository userInfoRepository;

    public EmergencyHospitalInfoDto readOne(String userInfoUserId, String hpid) {
        Optional<EmergencyHospitalInfo> emergencyHospitalInfoOptional = emergencyHospitalInfoRepository.findByHpid(hpid);
        if (!emergencyHospitalInfoOptional.isPresent()) {
            return null;
        }
        boolean contain = emergencyHospitalInfoOptional.get().getUserInfos().stream().anyMatch(userInfo -> userInfo.getUserId().equals(userInfoUserId));
        if (!contain) return null;
        EmergencyHospitalInfoDto emergencyHospitalInfoDto = new EmergencyHospitalInfoDto(emergencyHospitalInfoOptional.get());
        return emergencyHospitalInfoDto;
    }
}
