package com.emergency_link.emergency_link.entity;

import com.emergency_link.emergency_link.dto.HospitalPosDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HospitalPos")
@Getter
@Setter
@NoArgsConstructor
public class HospitalPos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmergencyHospitalInfoId")
    private EmergencyHospitalInfo emergencyHospitalInfo;

    public void setDtoToObject(HospitalPosDto hospitalPosDto) {
        this.setLatitude(hospitalPosDto.getLatitude());
        this.setLongitude(hospitalPosDto.getLongitude());
    }

    // ===== 연관관계 메소드 =====
    public void setEmergencyHospitalInfo(EmergencyHospitalInfo emergencyHospitalInfo) {
        this.emergencyHospitalInfo = emergencyHospitalInfo;
        emergencyHospitalInfo.setHospitalPos(this);
    }

}
