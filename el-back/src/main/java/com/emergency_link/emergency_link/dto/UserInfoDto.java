package com.emergency_link.emergency_link.dto;

import com.emergency_link.emergency_link.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {
    private Long id;
    private String userId;
    private String userPwd;
    private String userName;
    private String role;

    private List<PatientTransferRecordDto> patientTransferRecordDtos;

    public UserInfoDto(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.userId = userInfo.getUserId();
        this.userPwd = userInfo.getUserPwd();
        this.userName = userInfo.getUserName();
        this.role = userInfo.getRole();

        this.patientTransferRecordDtos = userInfo.getPatientTransferRecords().stream().map(PatientTransferRecordDto::new).collect(Collectors.toList());
    }
}
