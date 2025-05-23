/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_applicant_health_immunization database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_health_immunization")
@NamedQuery(name = "JpaApplicantHealthImmunization.findAll", query = "SELECT j FROM JpaApplicantHealthImmunization j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantHealthImmunization implements Serializable {

    private static final long serialVersionUID = 2977241512403108935L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_health_id")
    private JpaApplicantHealth applicantHealth;

    @Column(name = "immunization_code", nullable = false)
    private String immunizationCode;

    @Column(name = "immunization_date", nullable = false)
    private Date immunizationDate;

    private boolean mandatory;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @Column(name = "data_request_record_id")
    private Long dataRequestRecordId;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
        upperCase();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
        upperCase();
    }

    private void upperCase() {
        immunizationCode = StringUtils.upperCase(immunizationCode);
    }
}
