package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.EmergencyHospitalCapacityDto;
import com.emergency_link.emergency_link.entity.EmergencyHospitalCapacity;
import com.emergency_link.emergency_link.repository.EmergencyHospitalCapacityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class PreprocessingController { // EmergencyHospitalCapacity의 null값 처리
    private final EmergencyHospitalCapacityRepository emergencyHospitalCapacityRepository;

    @GetMapping("/preprocessing")
    @Transactional
    public void preprocessingCapacity() {
        List<EmergencyHospitalCapacity> emergencyHospitalCapacities = emergencyHospitalCapacityRepository.findAll();

        for(EmergencyHospitalCapacity capacity: emergencyHospitalCapacities) {
            EmergencyHospitalCapacityDto capacityDto = new EmergencyHospitalCapacityDto(capacity);

            if (capacityDto.getHperyn() == null) {
                capacityDto.setHperyn(emergencyHospitalCapacityRepository.findAverageHperyn());
            }
            if (capacityDto.getHvec() == null) {
                capacityDto.setHvec(capacityDto.getHperyn() / 2);
            }

            if (capacityDto.getHpopyn() == null) {
                capacityDto.setHpopyn(emergencyHospitalCapacityRepository.findAverageHpopyn());
            }
            if (capacityDto.getHvoc() == null) {
                capacityDto.setHvoc(capacityDto.getHpopyn() / 2);
            }

            if (capacityDto.getHpcuyn() == null) {
                capacityDto.setHpcuyn(emergencyHospitalCapacityRepository.findAverageHpcuyn());
            }
            if (capacityDto.getHvcc() == null) {
                capacityDto.setHvcc(capacityDto.getHpcuyn() / 2);
            }

            if (capacityDto.getHpnicuyn() == null) {
                capacityDto.setHpnicuyn(emergencyHospitalCapacityRepository.findAverageHpnicuyn());
            }
            if (capacityDto.getHvncc() == null) {
                capacityDto.setHvncc(capacityDto.getHpnicuyn() / 2);
            }

            if (capacityDto.getHpccuyn() == null) {
                capacityDto.setHpccuyn(emergencyHospitalCapacityRepository.findAverageHpccuyn());
            }
            if (capacityDto.getHvccc() == null) {
                capacityDto.setHvccc(capacityDto.getHpccuyn() / 2);
            }

            if (capacityDto.getHpicuyn() == null) {
                capacityDto.setHpicuyn(emergencyHospitalCapacityRepository.findAverageHpicuyn());
            }
            if (capacityDto.getHvicc() == null) {
                capacityDto.setHvicc(capacityDto.getHpicuyn() / 2);
            }

            if (capacityDto.getHpgryn() == null) {
                capacityDto.setHpgryn(emergencyHospitalCapacityRepository.findAverageHpgryn());
            }
            if (capacityDto.getHvgc() == null) {
                capacityDto.setHvgc(capacityDto.getHpgryn() / 2);
            }

            if (capacityDto.getHpbdn() == null) {
                capacityDto.setHpbdn(emergencyHospitalCapacityRepository.findAverageHpbdn());
            }
            if (capacityDto.getDutyHano() == null) {
                capacityDto.setDutyHano(capacityDto.getHpbdn() / 2);
            }
            EmergencyHospitalCapacity newCapacity = new EmergencyHospitalCapacity();
            newCapacity.setDtoToObject(capacityDto);
        }
    }
}
