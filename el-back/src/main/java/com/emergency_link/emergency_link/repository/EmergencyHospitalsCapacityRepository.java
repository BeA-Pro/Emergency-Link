package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.EmergencyHospitalsCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyHospitalsCapacityRepository extends JpaRepository<EmergencyHospitalsCapacity,Integer> {
}
