package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUserIdAndUserPwd(String userId, String pwd);
}
