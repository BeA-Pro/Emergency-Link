package com.emergency_link.emergency_link.dto;

import com.emergency_link.emergency_link.entity.PatientTransferRecord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PatientTransferRecordDto {
    private Long id;
    private String notes;
    private LocalDateTime transferTime;

    private Long patientInfoId;

    private Long emergencyHospitalInfoId;
    private String emergencyHospitalInfoHpid;
    private String emergencyHospitalInfoDutyName;

    public PatientTransferRecordDto(PatientTransferRecord patientTransferRecord) {
        this.id = patientTransferRecord.getId();
        this.notes = patientTransferRecord.getNotes();
        this.transferTime = patientTransferRecord.getTransferTime();

        this.patientInfoId = patientTransferRecord.getPatientInfo().getId();
        this.emergencyHospitalInfoId = patientTransferRecord.getEmergencyHospitalInfo().getId();
        this.emergencyHospitalInfoHpid = patientTransferRecord.getEmergencyHospitalInfo().getHpid();
        this.emergencyHospitalInfoDutyName = patientTransferRecord.getEmergencyHospitalInfo().getDutyName();

    }
}
