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
 * The persistent class for the sha_applicant_relative database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_applicant_relative")
@NamedQuery(name = "JpaApplicantRelative.findAll", query = "SELECT j FROM JpaApplicantRelative j")
@Data
@NoArgsConstructor
public class JpaApplicantRelative implements Serializable {

    private static final long serialVersionUID = 6481783740067590182L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "relationship_code", nullable = false)
    private String relationshipCode;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private JpaApplicant applicant;

    @ManyToOne
    @JoinColumn(name = "relative_applicant_id")
    private JpaApplicant relativeApplicant;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

}
