package com.emergency_link.emergency_link.entity;

import com.emergency_link.emergency_link.dto.UserInfoDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserInfo")
@Getter
@Setter
public class UserInfo extends Time {

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

    @Column
    private String hpid;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY)
    private List<PatientTransferRecord> patientTransferRecords= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmergencyHospitalInfoId")
    private EmergencyHospitalInfo emergencyHospitalInfo;

    public void setDtoToObject(UserInfoDto userInfoDto) {
        if(userInfoDto.getUserId() != null) this.setUserId(userInfoDto.getUserId());
        if(userInfoDto.getUserPwd() != null) this.setUserPwd(userInfoDto.getUserPwd());
        if(userInfoDto.getUserName() != null) this.setUserName(userInfoDto.getUserName());
        if(userInfoDto.getRole() != null) this.setRole(userInfoDto.getRole());
        if(userInfoDto.getHpid() != null) this.setHpid(userInfoDto.getHpid());
    }

    // ===== 연관관계 메소드 =====
    public void setEmergencyHospitalInfo(EmergencyHospitalInfo emergencyHospitalInfo) {
        if (this.emergencyHospitalInfo != null) {
            this.emergencyHospitalInfo.getUserInfos().remove(this);
        }

        this.emergencyHospitalInfo = emergencyHospitalInfo;
        emergencyHospitalInfo.getUserInfos().add(this);
    }
}