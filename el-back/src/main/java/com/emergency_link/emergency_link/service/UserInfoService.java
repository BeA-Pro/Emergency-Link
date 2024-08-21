package com.emergency_link.emergency_link.service;

import com.emergency_link.emergency_link.dto.UserInfoDto;
import com.emergency_link.emergency_link.entity.UserInfo;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserInfoDto join(UserInfoDto userInfoDto) {
        String encodedPassword = passwordEncoder.encode(userInfoDto.getUserPwd());

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userInfoDto.getUserId());
        userInfo.setUserPwd(encodedPassword);
        userInfo.setUserName(userInfoDto.getUserName());
        userInfo.setRole("ROLE_USER");

        userInfoRepository.save(userInfo);
        UserInfoDto createdUserDto = new UserInfoDto(userInfo);
        return createdUserDto;
    }
}
