package com.emergency_link.emergency_link.entity;

import com.emergency_link.emergency_link.dto.PatientTransferRecordDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

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

    @Column
    private LocalDateTime transferTime;

    @Column
    private String notes;

    @Column(nullable = false)
    private String preKtas;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EmergencyHospitalInfoId")
    private EmergencyHospitalInfo emergencyHospitalInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserInfoId")
    private UserInfo userInfo;


    public void setDtoToObject(PatientTransferRecordDto patientTransferRecordDto) {
        this.setNotes(patientTransferRecordDto.getNotes());
    }

    // ===== 연관 관계 설정 =====

    public void setEmergencyHospitalInfo(EmergencyHospitalInfo emergencyHospitalInfo) {
        if(this.emergencyHospitalInfo != null) {
            this.emergencyHospitalInfo.getPatientTransferRecords().remove(this);
        }

        this.emergencyHospitalInfo = emergencyHospitalInfo;
        emergencyHospitalInfo.getPatientTransferRecords().add(this);
    }

    public void setUserInfo(UserInfo userInfo){
        if(this.userInfo != null){
            this.userInfo.getPatientTransferRecords().remove(this);
        }
        this.userInfo = userInfo;
        userInfo.getPatientTransferRecords().add(this);
    }
}