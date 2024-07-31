package com.emergency_link.emergency_link.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "EmergencyHospitalsInfo")
@Data
public class EmergencyHospitalsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10, nullable = false)
    private String hpid;  // 기관ID

    @Column(length = 100, nullable = false)
    private String dutyName;  // 기관명

    @Column(length = 50)
    private String dutyEmcls;  // 응급의료기관분류

    @Column(length = 50)
    private String dutyEmclsName;  // 응급의료기관분류명

    private Integer postCdn1;  // 우편번호1
    private Integer postCdn2;  // 우편번호2

    @Column(length = 200)
    private String dutyAddr;  // 주소

    private String dutyTel1;  // 대표전화1
    private String dutyTel2;  // 응급실전화

    private Boolean dutyHayn;  // 입원실가용여부
    private Boolean dutyEryn;  // 응급실가용여부
    private Boolean traumaCenter;  // 외상센터 여부

    private Boolean MKioskTy1;  // 뇌출혈 가능 여부
    private Boolean MKioskTy2;  // 뇌경색의재관류 가능 여부
    private Boolean MKioskTy3;  // 심근경색의재관류 가능 여부
    private Boolean MKioskTy4;  // 복부손상의수술 가능 여부
    private Boolean MKioskTy5;  // 사지접합의수술 가능 여부
    private Boolean MKioskTy6;  // 응급내시경 가능 여부
    private Boolean MKioskTy7;  // 응급투석 가능 여부
    private Boolean MKioskTy8;  // 조산산모 가능 여부
    private Boolean MKioskTy9;  // 정신질환자 가능 여부
    private Boolean MKioskTy10;  // 신생아 가능 여부
    private Boolean MKioskTy11;  // 중증환자 가능 여부

    private Integer loginUser;  // 로그인 된 사용자 수


    @OneToOne(mappedBy = "emergencyHospitalsInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EmergencyHospitalsCapacity emergencyHospitalsCapacity;

    @OneToOne(mappedBy = "emergencyHospitalsInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HospitalPos hospitalPos;
}