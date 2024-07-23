package com.emergency_link.emergency_link.service;

import com.emergency_link.emergency_link.model.HospitalInfo;
import com.emergency_link.emergency_link.repository.HospitalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospitalInfoService {

    @Autowired
    private HospitalInfoRepository hospitalInfoRepository;

    @Transactional
    public void createHospitalInfo() {
        HospitalInfo hospitalInfo = new HospitalInfo();
        hospitalInfo.setHpid("H323212333");
        hospitalInfo.setDuty_name("Central Hospital");
        hospitalInfo.setPost_cdn1(12345);
        hospitalInfo.setPost_cdn2(6789);
        hospitalInfo.setDuty_addr("123 Main St");
        hospitalInfo.setMain_phone("1234567890");
        hospitalInfo.setEr_phone("98765433312210");
        hospitalInfo.setInpa_avail(true);
        hospitalInfo.setEr_avail(true);

        System.out.println(hospitalInfo);
        hospitalInfoRepository.save(hospitalInfo);
    }
}