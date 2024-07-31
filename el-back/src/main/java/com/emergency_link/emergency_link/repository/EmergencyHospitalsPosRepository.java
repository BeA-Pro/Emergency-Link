package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.HospitalPos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyHospitalsPosRepository extends JpaRepository<HospitalPos, Integer> {
}
