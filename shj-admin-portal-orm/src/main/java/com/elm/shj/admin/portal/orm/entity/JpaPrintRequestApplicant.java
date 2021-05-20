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
 * The persistent class for the sha_print_request_applicant database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_print_request_applicant")
@NamedQuery(name = "JpaPrintRequestApplicant.findAll", query = "SELECT j FROM JpaPrintRequestApplicant j")
@Data
@NoArgsConstructor
public class JpaPrintRequestApplicant implements Serializable {

    private static final long serialVersionUID = -3160791580855210723L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "print_request_id")
    private JpaPrintRequest printRequest;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private JpaApplicant applicant;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
}
