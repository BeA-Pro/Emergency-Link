package com.emergency_link.emergency_link.security.auth;

import com.emergency_link.emergency_link.entity.UserInfo;
import com.emergency_link.emergency_link.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;

    public CustomUserDetailsService(UserInfoRepository userInfoRepository) { this.userInfoRepository = userInfoRepository; }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserId(userId);
        if (!userInfo.isPresent()) {
            return null;
        }
        return new CustomUserDetails(userInfo.get());
    }
}

