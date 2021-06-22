/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_applicant_health_disease database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_health_disease")
@NamedQuery(name = "JpaApplicantHealthDisease.findAll", query = "SELECT j FROM JpaApplicantHealthDisease j")
@Data
@NoArgsConstructor
public class JpaApplicantHealthDisease implements Serializable {

    private static final long serialVersionUID = -3370781493008204778L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "applicant_health_id")
    private JpaApplicantHealth applicantHealth;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "data_request_record_id")
    private JpaDataRequestRecord dataRequestRecord;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
