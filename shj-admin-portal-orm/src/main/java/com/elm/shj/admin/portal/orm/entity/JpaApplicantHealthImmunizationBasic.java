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
 * The persistent Basic class for the shc_applicant_health_immunization database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_health_immunization")
@NamedQuery(name = "JpaApplicantHealthImmunizationBasic.findAll", query = "SELECT j FROM JpaApplicantHealthImmunizationBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantHealthImmunizationBasic implements Serializable {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_health_id")
    private JpaApplicantHealthBasic applicantHealth;

}
