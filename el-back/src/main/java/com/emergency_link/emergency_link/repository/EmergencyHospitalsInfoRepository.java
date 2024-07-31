package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.EmergencyHospitalsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmergencyHospitalsInfoRepository extends JpaRepository<EmergencyHospitalsInfo, Integer> {
    Optional<EmergencyHospitalsInfo> findByHpid(String hpid);
}