package com.emergency_link.emergency_link.repository;

import com.emergency_link.emergency_link.entity.EmergencyHospitalCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyHospitalCapacityRepository extends JpaRepository<EmergencyHospitalCapacity,Long> {
    @Query("SELECT AVG(e.hvec) FROM EmergencyHospitalCapacity e WHERE e.hvec IS NOT NULL")
    int findAverageHvec();

    @Query("SELECT AVG(e.hperyn) FROM EmergencyHospitalCapacity e WHERE e.hperyn IS NOT NULL")
    int findAverageHperyn();

    @Query("SELECT AVG(e.hvoc) FROM EmergencyHospitalCapacity e WHERE e.hvoc IS NOT NULL")
    int findAverageHvoc();

    @Query("SELECT AVG(e.hpopyn) FROM EmergencyHospitalCapacity e WHERE e.hpopyn IS NOT NULL")
    int findAverageHpopyn();

    @Query("SELECT AVG(e.hvcc) FROM EmergencyHospitalCapacity e WHERE e.hvcc IS NOT NULL")
    int findAverageHvcc();

    @Query("SELECT AVG(e.hpcuyn) FROM EmergencyHospitalCapacity e WHERE e.hpcuyn IS NOT NULL")
    int findAverageHpcuyn();

    @Query("SELECT AVG(e.hvncc) FROM EmergencyHospitalCapacity e WHERE e.hvncc IS NOT NULL")
    int findAverageHvncc();

    @Query("SELECT AVG(e.hpnicuyn) FROM EmergencyHospitalCapacity e WHERE e.hpnicuyn IS NOT NULL")
    int findAverageHpnicuyn();

    @Query("SELECT AVG(e.hvccc) FROM EmergencyHospitalCapacity e WHERE e.hvccc IS NOT NULL")
    int findAverageHvccc();

    @Query("SELECT AVG(e.hpccuyn) FROM EmergencyHospitalCapacity e WHERE e.hpccuyn IS NOT NULL")
    int findAverageHpccuyn();

    @Query("SELECT AVG(e.hvicc) FROM EmergencyHospitalCapacity e WHERE e.hvicc IS NOT NULL")
    int findAverageHvicc();

    @Query("SELECT AVG(e.hpicuyn) FROM EmergencyHospitalCapacity e WHERE e.hpicuyn IS NOT NULL")
    int findAverageHpicuyn();

    @Query("SELECT AVG(e.hvgc) FROM EmergencyHospitalCapacity e WHERE e.hvgc IS NOT NULL")
    int findAverageHvgc();

    @Query("SELECT AVG(e.hpgryn) FROM EmergencyHospitalCapacity e WHERE e.hpgryn IS NOT NULL")
    int findAverageHpgryn();

    @Query("SELECT AVG(e.dutyHano) FROM EmergencyHospitalCapacity e WHERE e.dutyHano IS NOT NULL")
    int findAverageDutyHano();

    @Query("SELECT AVG(e.hpbdn) FROM EmergencyHospitalCapacity e WHERE e.hpbdn IS NOT NULL")
    int findAverageHpbdn();
}
