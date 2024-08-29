package com.emergency_link.emergency_link.entity;

import com.emergency_link.emergency_link.dto.EmergencyHospitalInfoDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EmergencyHospitalInfo")
@Getter
@Setter
@NoArgsConstructor
public class EmergencyHospitalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String hpid;  // 기관ID

    @Column(length = 100)
    private String dutyName;  // 기관명

    @Column(length = 50)
    private String dutyEmcls;  // 응급의료기관분류

    @Column(length = 50)
    private String dutyEmclsName;  // 응급의료기관분류명

//    @Column(nullable = false)
    private Integer postCdn1;  // 우편번호1

//    @Column(nullable = false)
    private Integer postCdn2;  // 우편번호2

    @Column(length = 200)
    private String dutyAddr;  // 주소

//    @Column(nullable = false)
    private String dutyTel1;  // 대표전화1

//    @Column(nullable = false)
    private String dutyTel2;  // 응급실전화

//    @Column(nullable = false)
    private Boolean dutyHayn;  // 입원실가용여부

//    @Column(nullable = false)
    private Boolean dutyEryn;  // 응급실가용여부

//    @Column(nullable = false)
    private Boolean traumaCenter;  // 외상센터 여부

//    @Column(nullable = false)
    private Boolean MKioskTy1;  // 뇌출혈 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy2;  // 뇌경색의재관류 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy3;  // 심근경색의재관류 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy4;  // 복부손상의수술 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy5;  // 사지접합의수술 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy6;  // 응급내시경 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy7;  // 응급투석 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy8;  // 조산산모 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy9;  // 정신질환자 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy10;  // 신생아 가능 여부

//    @Column(nullable = false)
    private Boolean MKioskTy11;  // 중증환자 가능 여부

    @Column(columnDefinition = "integer default 0")
    private Integer loginUser;  // 로그인 된 사용자 수

    @OneToMany(mappedBy = "emergencyHospitalInfo")
    private List<PatientTransferRecord> patientTransferRecords = new ArrayList<>();

    @OneToOne(mappedBy = "emergencyHospitalInfo", cascade = CascadeType.ALL)
    private HospitalPos hospitalPos;

//    @OneToMany(mappedBy = "emergencyHospitalInfo", cascade = CascadeType.ALL)
//    private List<HospitalUserInfo> hospitalUserInfos = new ArrayList<>();

    @OneToMany(mappedBy = "emergencyHospitalInfo", cascade = CascadeType.ALL)
    private List<UserInfo> userInfos = new ArrayList<>();

    @OneToOne(mappedBy = "emergencyHospitalInfo", cascade = CascadeType.ALL)
    private EmergencyHospitalCapacity emergencyHospitalCapacity;

    public void setDtoToObject(EmergencyHospitalInfoDto emergencyHospitalInfoDto) {
        this.setHpid(emergencyHospitalInfoDto.getHpid());
        this.setDutyName(emergencyHospitalInfoDto.getDutyName());
        this.setDutyEmcls(emergencyHospitalInfoDto.getDutyEmcls());
        this.setDutyEmclsName(emergencyHospitalInfoDto.getDutyEmclsName());
        this.setPostCdn1(emergencyHospitalInfoDto.getPostCdn1());
        this.setPostCdn2(emergencyHospitalInfoDto.getPostCdn2());
        this.setDutyAddr(emergencyHospitalInfoDto.getDutyAddr());
        this.setDutyTel1(emergencyHospitalInfoDto.getDutyTel1());
        this.setDutyTel2(emergencyHospitalInfoDto.getDutyTel2());
        this.setDutyHayn(emergencyHospitalInfoDto.getDutyHayn());
        this.setDutyEryn(emergencyHospitalInfoDto.getDutyEryn());
        this.setTraumaCenter(emergencyHospitalInfoDto.getTraumaCenter());

        this.setMKioskTy1(emergencyHospitalInfoDto.getMKioskTy1());
        this.setMKioskTy2(emergencyHospitalInfoDto.getMKioskTy2());
        this.setMKioskTy3(emergencyHospitalInfoDto.getMKioskTy3());

        this.setMKioskTy4(emergencyHospitalInfoDto.getMKioskTy4());
        this.setMKioskTy5(emergencyHospitalInfoDto.getMKioskTy5());
        this.setMKioskTy6(emergencyHospitalInfoDto.getMKioskTy6());

        this.setMKioskTy7(emergencyHospitalInfoDto.getMKioskTy7());
        this.setMKioskTy8(emergencyHospitalInfoDto.getMKioskTy8());
        this.setMKioskTy9(emergencyHospitalInfoDto.getMKioskTy9());

        this.setMKioskTy10(emergencyHospitalInfoDto.getMKioskTy10());
        this.setMKioskTy11(emergencyHospitalInfoDto.getMKioskTy11());

        this.setLoginUser(emergencyHospitalInfoDto.getLoginUser());
    }

}