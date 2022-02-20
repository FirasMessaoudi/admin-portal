/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_company_ritual_step database table.
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company_ritual_step")
@NamedQuery(name = "JpaCompanyRitualStep.findAll", query = "SELECT j FROM JpaCompanyRitualStep j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyRitualStep implements Serializable {

    private static final long serialVersionUID = 7808149444429290164L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "applicant_group_id", nullable = false)
    private JpaApplicantGroup applicantGroup;

    @Column(name = "transportation_type_code")
    private String transportationTypeCode;

    @Column(name = "step_index", nullable = false)
    private long stepIndex;

    @Column(name = "step_code", nullable = false)
    private String stepCode;

    @Column(name = "time", nullable = false)
    private Date time;

    @Column(name = "location_lat", nullable = false)
    private double locationLat;

    @Column(name = "location_lng", nullable = false)
    private double locationLng;

    @Column(name = "location_name_ar", nullable = false)
    private String locationNameAr;

    @Column(name = "location_name_en", nullable = false)
    private String locationNameEn;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
