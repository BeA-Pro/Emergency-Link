package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.model.HospitalCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalCapacityRepository extends JpaRepository<HospitalCapacity,Integer> {
}
