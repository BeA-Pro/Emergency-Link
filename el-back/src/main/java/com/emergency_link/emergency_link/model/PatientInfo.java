package com.emergency_link.emergency_link.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;


@Entity
@Table(name = "patient_info")
@Data
public class PatientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // private Timestamp registered_at;
    private String pre_ktas;
    private Double latitude;
    private Double longitude;
}