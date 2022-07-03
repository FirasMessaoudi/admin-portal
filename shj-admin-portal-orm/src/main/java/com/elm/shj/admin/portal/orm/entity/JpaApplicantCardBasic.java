/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the shc_applicant_card database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_card")
@NamedQuery(name = "JpaApplicantCardBasic.findAll", query = "SELECT j FROM JpaApplicantCardBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantCardBasic implements Serializable {

    private static final long serialVersionUID = 4881148207869302797L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "applicant_ritual_id", updatable = false)
    private JpaApplicantRitualBasic applicantRitual;

    @Column(name = "status_code")
    private String statusCode;

    private boolean deleted;
}
