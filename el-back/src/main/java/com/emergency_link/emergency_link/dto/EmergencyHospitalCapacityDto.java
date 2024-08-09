package com.emergency_link.emergency_link.dto;

import com.emergency_link.emergency_link.entity.EmergencyHospitalCapacity;
import com.emergency_link.emergency_link.entity.EmergencyHospitalInfo;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmergencyHospitalCapacityDto {
    private Long id;
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
    private Integer hpbdn;

    private Long emergencyHospitalInfoId;
    private String emergencyHospitalInfoHpid;
    private String emergencyHospitalInfoDutyName;

    public EmergencyHospitalCapacityDto(EmergencyHospitalCapacity emergencyHospitalCapacity) {
        this.id = emergencyHospitalCapacity.getId();
        this.hvec = emergencyHospitalCapacity.getHvec();
        this.hperyn = emergencyHospitalCapacity.getHperyn();
        this.hvoc = emergencyHospitalCapacity.getHvoc();
        this.hpopyn = emergencyHospitalCapacity.getHpopyn();
        this.hvcc = emergencyHospitalCapacity.getHvcc();
        this.hpcuyn = emergencyHospitalCapacity.getHpcuyn();
        this.hvncc = emergencyHospitalCapacity.getHvncc();
        this.hpnicuyn = emergencyHospitalCapacity.getHpnicuyn();
        this.hvccc = emergencyHospitalCapacity.getHvccc();
        this.hpccuyn = emergencyHospitalCapacity.getHpccuyn();
        this.hvicc = emergencyHospitalCapacity.getHvicc();
        this.hpicuyn = emergencyHospitalCapacity.getHpicuyn();
        this.hvgc = emergencyHospitalCapacity.getHvgc();
        this.hpgryn = emergencyHospitalCapacity.getHpgryn();
        this.dutyHano = emergencyHospitalCapacity.getDutyHano();
        this.hpbdn = emergencyHospitalCapacity.getHpbdn();

        this.emergencyHospitalInfoId = emergencyHospitalCapacity.getEmergencyHospitalInfo().getId();
        this.emergencyHospitalInfoHpid = emergencyHospitalCapacity.getEmergencyHospitalInfo().getHpid();
        this.emergencyHospitalInfoDutyName = emergencyHospitalCapacity.getEmergencyHospitalInfo().getDutyName();
    }
}
