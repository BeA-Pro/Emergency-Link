package com.emergency_link.emergency_link.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "HospitalsPos")
@Data
public class HospitalPos {

    @Id
    private Integer id;

    private Double latitude;
    private Double longitude;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private EmergencyHospitalsInfo emergencyHospitalsInfo;
}
