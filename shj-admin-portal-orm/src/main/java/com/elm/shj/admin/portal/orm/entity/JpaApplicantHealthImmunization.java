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
 * The persistent class for the sha_applicant_health_immunization database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_applicant_health_immunization")
@NamedQuery(name = "JpaApplicantHealthImmunization.findAll", query = "SELECT j FROM JpaApplicantHealthImmunization j")
@Data
@NoArgsConstructor
public class JpaApplicantHealthImmunization implements Serializable {

    private static final long serialVersionUID = 2977241512403108935L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "applicant_health_id")
    private JpaApplicantHealth applicantHealth;

    @Column(name = "immunization_code", nullable = false)
    private String immunizationCode;

    @Column(name = "immunization_date", nullable = false)
    private Date immunizationDate;

    private boolean mandatory;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "data_request_record_id")
    private JpaDataRequestRecord dataRequestRecordId;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
