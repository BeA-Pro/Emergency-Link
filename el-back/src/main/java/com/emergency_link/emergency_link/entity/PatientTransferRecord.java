package com.emergency_link.emergency_link.entity;

import com.emergency_link.emergency_link.dto.PatientTransferRecordDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "PatientTransferRecord")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class PatientTransferRecord extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime transferTime;

    @Column(nullable = false)
    private String notes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PatientInfoId")
    private PatientInfo patientInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EmergencyHospitalInfoId")
    private EmergencyHospitalInfo emergencyHospitalInfo;

    public void setDtoToObject(PatientTransferRecordDto patientTransferRecordDto) {
        this.setNotes(patientTransferRecordDto.getNotes());
    }

    // ===== 연관 관계 설정 =====
    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
        patientInfo.setPatientTransferRecord(this);
    }

    public void setEmergencyHospitalInfo(EmergencyHospitalInfo emergencyHospitalInfo) {
        if(this.emergencyHospitalInfo != null) {
            this.emergencyHospitalInfo.getPatientTransferRecords().remove(this);
        }

        this.emergencyHospitalInfo = emergencyHospitalInfo;
        emergencyHospitalInfo.getPatientTransferRecords().add(this);
    }

}