package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.HospitalUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalUserInfoRepository extends JpaRepository<HospitalUserInfo, Long> {
}
