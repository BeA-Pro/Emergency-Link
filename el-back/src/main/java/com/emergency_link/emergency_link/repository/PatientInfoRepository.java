package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.model.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInfoRepository extends JpaRepository<PatientInfo, Integer> {
}