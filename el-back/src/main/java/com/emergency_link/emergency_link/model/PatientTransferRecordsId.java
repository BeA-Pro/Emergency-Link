package com.emergency_link.emergency_link.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PatientTransferRecordsId implements Serializable {

    @EqualsAndHashCode.Include
    private Integer patient_id;

    @EqualsAndHashCode.Include
    private Integer user_id;

    @EqualsAndHashCode.Include
    private Integer hospital_id;

    // Default constructor
    public PatientTransferRecordsId() {}
}