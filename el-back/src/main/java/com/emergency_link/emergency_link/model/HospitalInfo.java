package com.emergency_link.emergency_link.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hospital_info")
@Data
public class HospitalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hpid;
    private String duty_name;
    private Integer post_cdn1;
    private Integer post_cdn2;
    private String duty_addr;
    private String main_phone;
    private String er_phone;
    private Boolean inpa_avail;
    private Boolean er_avail;

    @OneToOne(mappedBy = "hospitalInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HospitalCapacity hospitalCapacity;

    @OneToOne(mappedBy = "hospitalInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HospitalPos hospitalPos;
}