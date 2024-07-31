package com.emergency_link.emergency_link.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "EmergencyHospitalsCapacity")
@Data
public class EmergencyHospitalsCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer hvec;  // 가용 응급실 수
    private Integer hperyn;  // 기준 응급실 수
    private Integer hvoc;  // 가용 수술실 수
    private Integer hpopyn;  // 기준 수술실 수

    private Integer hvcc;  // 가용 신경중환자 수
    private Integer hpcuyn;  // 기준 신경중환자 수

    private Integer hvncc;  // 가용 신생중환자 수
    private Integer hpnicuyn;  // 기준 신생중환자 수

    private Integer hvccc;  // 가용 흉부중환자 수
    private Integer hpccuyn;  // 기준 흉부중환자 수

    private Integer hvicc;  // 가용 일반중환자 수
    private Integer hpicuyn;  // 기준 일반중환자 수

    private Integer hvgc;  // 가용 입원실 수
    private Integer hpgryn;  // 기준 입원실 수

    private Integer dutyHano;  // 가용 병상 수
    private Integer hpbdn;  // 기준 병상 수

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private EmergencyHospitalsInfo emergencyHospitalsInfo;
}



