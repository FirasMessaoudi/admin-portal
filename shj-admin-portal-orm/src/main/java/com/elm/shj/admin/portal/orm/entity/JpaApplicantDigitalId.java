/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_applicant_digital_id database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_digital_id")
@NamedQuery(name = "JpaApplicantDigitalId.findAll", query = "SELECT j FROM JpaApplicantDigitalId j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantDigitalId implements Serializable {

    private static final long serialVersionUID = -6110880958142822051L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String uin;

    @ManyToOne
    private JpaApplicant applicant;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
