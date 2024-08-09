package com.emergency_link.emergency_link.dto;

import com.emergency_link.emergency_link.entity.HospitalPos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HospitalPosDto {
    private Long id;
    private double latitude;
    private double longitude;

    private Long emergencyHospitalInfoId;
    private String emergencyHospitalInfoHpid;
    private String emergencyHospitalInfoDutyName;

    public HospitalPosDto(HospitalPos hospitalPos) {
        this.id = hospitalPos.getId();
        this.latitude = hospitalPos.getLatitude();
        this.longitude = hospitalPos.getLongitude();

        this.emergencyHospitalInfoId = hospitalPos.getEmergencyHospitalInfo().getId();
        this.emergencyHospitalInfoHpid = hospitalPos.getEmergencyHospitalInfo().getHpid();
        this.emergencyHospitalInfoDutyName = hospitalPos.getEmergencyHospitalInfo().getDutyName();
    }
}
