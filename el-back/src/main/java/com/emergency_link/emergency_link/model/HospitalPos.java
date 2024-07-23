package com.emergency_link.emergency_link.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hospital_pos")
@Data
public class HospitalPos {

    @Id
    private Integer id;

    private Double latitude;
    private Double longitude;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private HospitalInfo hospitalInfo;
}
