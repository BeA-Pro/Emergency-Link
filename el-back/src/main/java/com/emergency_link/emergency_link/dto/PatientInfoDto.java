package com.emergency_link.emergency_link.dto;

import com.emergency_link.emergency_link.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientInfoDto {
    private Long id;
    private String preKtas;
    private double longitude;
    private double latitude;

    private Long userInfoId;
    private String userInfoUserName;

    private Long patientTransferRecordId;

    public PatientInfoDto(PatientInfo patientInfo) {
        this.id = patientInfo.getId();
        this.preKtas = patientInfo.getPreKtas();
        this.longitude = patientInfo.getLongitude();
        this.latitude = patientInfo.getLatitude();

        this.userInfoId = patientInfo.getUserInfo().getId();
        this.userInfoUserName = patientInfo.getUserInfo().getUserName();

        this.patientTransferRecordId = patientInfo.getPatientTransferRecord().getId();
    }
}
