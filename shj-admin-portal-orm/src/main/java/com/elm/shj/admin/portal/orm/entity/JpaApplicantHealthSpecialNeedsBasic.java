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
 * The persistent Basic class for the shc_applicant_health_special_needs database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_health_special_needs")
@NamedQuery(name = "JpaApplicantHealthSpecialNeedsBasic.findAll", query = "SELECT j FROM JpaApplicantHealthSpecialNeedsBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantHealthSpecialNeedsBasic implements Serializable {

    private static final long serialVersionUID = 8963168397661232916L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "special_need_type_code", nullable = false)
    private String specialNeedTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_health_id")
    private JpaApplicantHealthBasic applicantHealth;
}
