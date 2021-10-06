/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_group_applicant_list database table.
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_group_applicant_list")
@NamedQuery(name = "JpaGroupApplicantList.findAll", query = "SELECT j FROM JpaGroupApplicantList j")
@Getter
@Setter
@NoArgsConstructor
public class JpaGroupApplicantList implements Serializable {

    private static final long serialVersionUID = 7859379318931902943L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private JpaApplicantGroup applicantGroup;

    @Column(name = "applicant_uin", nullable = false)
    private String applicantUin;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
