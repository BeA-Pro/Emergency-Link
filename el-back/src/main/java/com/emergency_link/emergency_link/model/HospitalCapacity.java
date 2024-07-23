package com.emergency_link.emergency_link.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hospital_capacity")
@Data
public class HospitalCapacity {

    @Id
    private Integer id;

    private Integer a_er_rooms;
    private Integer s_er_rooms;
    private Integer a_sur_rooms;
    private Integer s_sur_rooms;
    private Integer a_neu_icu;
    private Integer s_neu_icu;
    private Integer a_neo_icu;
    private Integer s_neo_icu;
    private Integer a_tho_icu;
    private Integer s_tho_icu;
    private Integer a_gen_icu;
    private Integer s_gen_icu;
    private Integer a_inpatient;
    private Integer s_inpatient;
    private Integer a_beds;
    private Integer s_beds;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private HospitalInfo hospitalInfo;
}