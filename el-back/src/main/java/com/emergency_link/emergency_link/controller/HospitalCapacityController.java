package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.model.HospitalCapacity;
import com.emergency_link.emergency_link.service.HospitalCapacityService;
import com.emergency_link.emergency_link.service.HospitalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capacity")
public class HospitalCapacityController {
    @Autowired
    private HospitalCapacityService hospitalCapacityService;

    @PostMapping
    public void createHospitalInfo() {
        hospitalCapacityService.createHospitalCapacity(1);
    }
}
