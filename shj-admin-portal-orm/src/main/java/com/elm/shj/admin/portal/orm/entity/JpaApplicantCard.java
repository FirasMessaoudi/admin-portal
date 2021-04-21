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
 * The persistent class for the sha_applicant_card database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_applicant_card")
@NamedQuery(name = "JpaApplicantCard.findAll", query = "SELECT j FROM JpaApplicantCard j")
@Data
@NoArgsConstructor
public class JpaApplicantCard implements Serializable {

    private static final long serialVersionUID = 4881148207869302797L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    private JpaApplicantRitual applicantRitual;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "batch_id")
    private long batchId;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
}
