package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.model.HospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Integer> {
}