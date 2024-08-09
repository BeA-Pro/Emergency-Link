package com.emergency_link.emergency_link.entity;

import com.emergency_link.emergency_link.dto.PatientInfoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "PatientInfo")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class PatientInfo extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String preKtas;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private LocalDateTime registeredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserInfoId")
    private UserInfo userInfo;

    @OneToOne(mappedBy = "patientInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PatientTransferRecord patientTransferRecord;

    public void setDtoToObject(PatientInfoDto patientInfoDto) {
        this.setPreKtas(patientInfoDto.getPreKtas());
        this.setLongitude(patientInfoDto.getLongitude());
        this.setLatitude(patientInfoDto.getLatitude());
    }

    // ====== 연관관계 메소드 =====
    public void setUserInfo(UserInfo userInfo) {
        if (this.userInfo != null) {
            this.userInfo.getPatientInfos().remove(this);
        }
        this.userInfo = userInfo;
        userInfo.getPatientInfos().add(this);
    }


}
