package com.emergency_link.emergency_link.entity;

import com.emergency_link.emergency_link.dto.EmergencyHospitalCapacityDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EmergencyHospitalCapacity")
@Getter
@Setter
@NoArgsConstructor
public class EmergencyHospitalCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private Integer hvec;  // 가용 응급실 수

//    @Column(nullable = false)
    private Integer hperyn;  // 기준 응급실 수


//    @Column(nullable = false)
    private Integer hvoc;  // 가용 수술실 수

//    @Column(nullable = false)
    private Integer hpopyn;  // 기준 수술실 수


//    @Column(nullable = false)
    private Integer hvcc;  // 가용 신경중환자 수

//    @Column(nullable = false)
    private Integer hpcuyn;  // 기준 신경중환자 수


//    @Column(nullable = false)
    private Integer hvncc;  // 가용 신생중환자 수

//    @Column(nullable = false)
    private Integer hpnicuyn;  // 기준 신생중환자 수


//    @Column(nullable = false)
    private Integer hvccc;  // 가용 흉부중환자 수

//    @Column(nullable = false)
    private Integer hpccuyn;  // 기준 흉부중환자 수


//    @Column(nullable = false)
    private Integer hvicc;  // 가용 일반중환자 수

//    @Column(nullable = false)
    private Integer hpicuyn;  // 기준 일반중환자 수


//    @Column(nullable = false)
    private Integer hvgc;  // 가용 입원실 수

//    @Column(nullable = false)
    private Integer hpgryn;  // 기준 입원실 수


//    @Column(nullable = false)
    private Integer dutyHano;  // 가용 병상 수

//    @Column(nullable = false)
    private Integer hpbdn;  // 기준 병상 수


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmergencyHospitalInfoId")
    private EmergencyHospitalInfo emergencyHospitalInfo;

    public void setDtoToObject(EmergencyHospitalCapacityDto emergencyHospitalCapacityDto) {
        this.setHvec(emergencyHospitalCapacityDto.getHvec());
        this.setHperyn(emergencyHospitalCapacityDto.getHperyn());
        this.setHvoc(emergencyHospitalCapacityDto.getHvoc());
        this.setHpopyn(emergencyHospitalCapacityDto.getHpopyn());
        this.setHvcc(emergencyHospitalCapacityDto.getHvcc());
        this.setHpcuyn(emergencyHospitalCapacityDto.getHpcuyn());
        this.setHvncc(emergencyHospitalCapacityDto.getHvncc());
        this.setHpnicuyn(emergencyHospitalCapacityDto.getHpnicuyn());
        this.setHvccc(emergencyHospitalCapacityDto.getHvccc());
        this.setHpccuyn(emergencyHospitalCapacityDto.getHpccuyn());
        this.setHvicc(emergencyHospitalCapacityDto.getHvicc());
        this.setHpicuyn(emergencyHospitalCapacityDto.getHpicuyn());
        this.setHvgc(emergencyHospitalCapacityDto.getHvgc());
        this.setHpgryn(emergencyHospitalCapacityDto.getHpgryn());
        this.setDutyHano(emergencyHospitalCapacityDto.getDutyHano());
        this.setHpbdn(emergencyHospitalCapacityDto.getHpbdn());
    }

    // ===== 연관관계 메소드 =====
    public void setEmergencyHospitalInfo(EmergencyHospitalInfo emergencyHospitalInfo) {
        this.emergencyHospitalInfo = emergencyHospitalInfo;
        emergencyHospitalInfo.setEmergencyHospitalCapacity(this);
    }
}



