package com.emergency_link.emergency_link.service;

import com.emergency_link.emergency_link.model.HospitalCapacity;
import com.emergency_link.emergency_link.model.HospitalInfo;
import com.emergency_link.emergency_link.repository.HospitalCapacityRepository;
import com.emergency_link.emergency_link.repository.HospitalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospitalCapacityService {

    @Autowired
    private HospitalCapacityRepository hospitalCapacityRepository;

    @Autowired
    private HospitalInfoRepository hospitalInfoRepository;

    @Transactional
    public void createHospitalCapacity(Integer hospitalInfoId) {
        // 먼저 HospitalInfo 엔티티를 로드합니다.
        HospitalInfo hospitalInfo = hospitalInfoRepository.findById(hospitalInfoId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hospital info ID: " + hospitalInfoId));

        // HospitalCapacity 엔티티를 생성하고 HospitalInfo를 설정합니다.
        HospitalCapacity hospitalCapacity = new HospitalCapacity();
        hospitalCapacity.setA_er_rooms(5);
        hospitalCapacity.setS_er_rooms(10);
        hospitalCapacity.setA_sur_rooms(2);
        hospitalCapacity.setS_sur_rooms(4);
        hospitalCapacity.setA_neu_icu(1);
        hospitalCapacity.setS_neu_icu(2);
        hospitalCapacity.setA_neo_icu(1);
        hospitalCapacity.setS_neo_icu(2);
        hospitalCapacity.setA_tho_icu(1);
        hospitalCapacity.setS_tho_icu(2);
        hospitalCapacity.setA_gen_icu(3);
        hospitalCapacity.setS_gen_icu(5);
        hospitalCapacity.setA_inpatient(10);
        hospitalCapacity.setS_inpatient(20);
        hospitalCapacity.setA_beds(50);
        hospitalCapacity.setS_beds(100);

        // HospitalInfo 설정
        hospitalCapacity.setHospitalInfo(hospitalInfo);

        System.out.println(hospitalCapacity);
        hospitalCapacityRepository.save(hospitalCapacity);
    }
}
