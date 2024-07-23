package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.model.HospitalInfo;
import com.emergency_link.emergency_link.service.HospitalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital-info")
public class HospitalInfoController {

    @Autowired
    private HospitalInfoService hospitalService;

    @PostMapping
    public void createHospitalInfo() {
        hospitalService.createHospitalInfo();
    }
}
