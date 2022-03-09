/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent lite class for the shc_applicant_health_immunization database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_health_immunization")
@NamedQuery(name = "JpaApplicantHealthImmunizationLite.findAll", query = "SELECT j FROM JpaApplicantHealthImmunizationLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantHealthImmunizationLite implements Serializable {

    private static final long serialVersionUID = 2977241512403108935L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "immunization_code", nullable = false)
    private String immunizationCode;

    @Column(name = "immunization_date", nullable = false)
    private Date immunizationDate;

    private boolean mandatory;
}
