package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.HospitalPos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalPosRepository extends JpaRepository<HospitalPos, Long> {
}
