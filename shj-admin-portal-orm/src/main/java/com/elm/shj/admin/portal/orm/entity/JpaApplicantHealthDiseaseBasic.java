/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent Basic class for the shc_applicant_health_disease database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_health_disease")
@NamedQuery(name = "JpaApplicantHealthDiseaseBasic.findAll", query = "SELECT j FROM JpaApplicantHealthDiseaseBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantHealthDiseaseBasic implements Serializable {

    private static final long serialVersionUID = -3370781493008204778L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_health_id")
    private JpaApplicantHealthBasic applicantHealth;


}
