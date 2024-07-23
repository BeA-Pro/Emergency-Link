package com.emergency_link.emergency_link.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_transfer_records")
@Data
@IdClass(PatientTransferRecordsId.class)
public class PatientTransferRecords {

    @Id
    private Integer patient_id;

    @Id
    private Integer user_id;

    @Id
    private Integer hospital_id;

    // private Timestamp transfer_time;
    private String notes;
}