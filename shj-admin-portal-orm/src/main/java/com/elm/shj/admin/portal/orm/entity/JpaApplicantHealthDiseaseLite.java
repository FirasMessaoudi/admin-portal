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
 * The persistent lite class for the shc_applicant_health_disease database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_health_disease")
@NamedQuery(name = "JpaApplicantHealthDiseaseLite.findAll", query = "SELECT j FROM JpaApplicantHealthDiseaseLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantHealthDiseaseLite implements Serializable {

    private static final long serialVersionUID = -3370781493008204778L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "label_ar")
    private String labelAr;

    @Column(name = "label_en")
    private String labelEn;
}
