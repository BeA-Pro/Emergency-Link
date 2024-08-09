package com.emergency_link.emergency_link.dto;

import com.emergency_link.emergency_link.entity.HospitalUserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HospitalUserInfoDto {
    private Long id;
    private String userId;
    private String userPwd;
    private String userName;
    private String role;

    private Long emergencyHospitalInfoId;
    private String emergencyHospitalInfoHpid;
    private String emergencyHospitalInfoDutyName;

    public HospitalUserInfoDto(HospitalUserInfo hospitalUserInfo) {
        this.id = hospitalUserInfo.getId();
        this.userId = hospitalUserInfo.getUserId();
        this.userPwd = hospitalUserInfo.getUserPwd();
        this.userName = hospitalUserInfo.getUserName();
        this.role = hospitalUserInfo.getRole();

        this.emergencyHospitalInfoId = hospitalUserInfo.getEmergencyHospitalInfo().getId();
        this.emergencyHospitalInfoHpid = hospitalUserInfo.getEmergencyHospitalInfo().getHpid();
        this.emergencyHospitalInfoDutyName = hospitalUserInfo.getEmergencyHospitalInfo().getDutyName();
    }
}
