package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserIdAndUserPwd(String userId, String pwd);
    Optional<UserInfo> findByUserId(String userId);
}
