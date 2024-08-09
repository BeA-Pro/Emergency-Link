package com.emergency_link.emergency_link.entity;

import com.emergency_link.emergency_link.dto.HospitalUserInfoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "HospitalUserInfo")
@Entity
public class HospitalUserInfo extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmergencyHospitalInfoId")
    private EmergencyHospitalInfo emergencyHospitalInfo;


    public void setDtoToObject(HospitalUserInfoDto hospitalUserInfoDto) {
        if (hospitalUserInfoDto.getUserId() != null) this.setUserId(hospitalUserInfoDto.getUserId());
        if (hospitalUserInfoDto.getUserPwd() != null) this.setUserPwd(hospitalUserInfoDto.getUserPwd());
        if (hospitalUserInfoDto.getUserName() != null) this.setUserName(hospitalUserInfoDto.getUserName());
        if (hospitalUserInfoDto.getRole() != null) this.setRole(hospitalUserInfoDto.getRole());
    }

    // ===== 연관관계 메소드 =====
    public void setEmergencyHospitalInfo(EmergencyHospitalInfo emergencyHospitalInfo) {
        if (this.emergencyHospitalInfo != null) {
            this.emergencyHospitalInfo.getHospitalUserInfos().remove(this);
        }

        this.emergencyHospitalInfo = emergencyHospitalInfo;
        emergencyHospitalInfo.getHospitalUserInfos().add(this);
    }
}
