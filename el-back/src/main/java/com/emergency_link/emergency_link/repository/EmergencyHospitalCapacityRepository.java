package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.EmergencyHospitalCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyHospitalCapacityRepository extends JpaRepository<EmergencyHospitalCapacity,Long> {
}
