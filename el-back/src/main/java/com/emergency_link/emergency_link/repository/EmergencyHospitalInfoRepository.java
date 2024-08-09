package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.EmergencyHospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmergencyHospitalInfoRepository extends JpaRepository<EmergencyHospitalInfo, Long> {
    Optional<EmergencyHospitalInfo> findByHpid(String hpid);
}