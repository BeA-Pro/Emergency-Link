package com.emergency_link.emergency_link.dto;

import com.emergency_link.emergency_link.entity.EmergencyHospitalInfo;
import com.emergency_link.emergency_link.entity.HospitalUserInfo;
import com.emergency_link.emergency_link.entity.PatientTransferRecord;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class EmergencyHospitalInfoDto {
    private Long id;
    private String hpid;  // 기관ID
    private String dutyName;  // 기관명
    private String dutyEmcls;  // 응급의료기관분류
    private String dutyEmclsName;  // 응급의료기관분류명
    private Integer postCdn1;  // 우편번호1
    private Integer postCdn2;  // 우편번호2
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
    private Integer loginUser;

    private List<PatientTransferRecordDto> patientTransferRecordDtos;

    private double hospitalPosLatitude;
    private double hospitalPosLongitude;

    private List<HospitalUserInfoDto> hospitalUserInfoDtos;
    private Long emergencyHospitalCapacityId;

    public EmergencyHospitalInfoDto(EmergencyHospitalInfo emergencyHospitalInfo
    ) {
        this.id = emergencyHospitalInfo.getId();
        this.hpid = emergencyHospitalInfo.getHpid();
        this.dutyName = emergencyHospitalInfo.getDutyName();
        this.dutyEmcls = emergencyHospitalInfo.getDutyEmcls();
        this.dutyEmclsName = emergencyHospitalInfo.getDutyEmclsName();
        this.postCdn1 = emergencyHospitalInfo.getPostCdn1();
        this.postCdn2 = emergencyHospitalInfo.getPostCdn2();
        this.dutyAddr = emergencyHospitalInfo.getDutyAddr();
        this.dutyTel1 = emergencyHospitalInfo.getDutyTel1();
        this.dutyTel2 = emergencyHospitalInfo.getDutyTel2();
        this.dutyHayn = emergencyHospitalInfo.getDutyHayn();
        this.dutyEryn = emergencyHospitalInfo.getDutyEryn();
        this.traumaCenter = emergencyHospitalInfo.getTraumaCenter();
        this.MKioskTy1 = emergencyHospitalInfo.getMKioskTy1();
        this.MKioskTy2 = emergencyHospitalInfo.getMKioskTy2();
        this.MKioskTy3 = emergencyHospitalInfo.getMKioskTy3();
        this.MKioskTy4 = emergencyHospitalInfo.getMKioskTy4();
        this.MKioskTy5 = emergencyHospitalInfo.getMKioskTy5();
        this.MKioskTy6 = emergencyHospitalInfo.getMKioskTy6();
        this.MKioskTy7 = emergencyHospitalInfo.getMKioskTy7();
        this.MKioskTy8 = emergencyHospitalInfo.getMKioskTy8();
        this.MKioskTy9 = emergencyHospitalInfo.getMKioskTy9();
        this.MKioskTy10 = emergencyHospitalInfo.getMKioskTy10();
        this.MKioskTy11 = emergencyHospitalInfo.getMKioskTy11();
        this.loginUser = emergencyHospitalInfo.getLoginUser();

        this.patientTransferRecordDtos = emergencyHospitalInfo.getPatientTransferRecords().stream().map(PatientTransferRecordDto::new).collect(Collectors.toList());
        this.hospitalPosLatitude = emergencyHospitalInfo.getHospitalPos().getLatitude();
        this.hospitalPosLongitude = emergencyHospitalInfo.getHospitalPos().getLongitude();

        this.hospitalUserInfoDtos = emergencyHospitalInfo.getHospitalUserInfos().stream().map(HospitalUserInfoDto::new).collect(Collectors.toList());
        this.emergencyHospitalCapacityId = emergencyHospitalInfo.getEmergencyHospitalCapacity().getId();
    }
}
