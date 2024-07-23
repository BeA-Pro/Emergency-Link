package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
}