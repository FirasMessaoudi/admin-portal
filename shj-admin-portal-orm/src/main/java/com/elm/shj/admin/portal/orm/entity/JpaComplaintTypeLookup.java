/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the shc_complaint_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_complaint_type_lk")
@NamedQuery(name = "JpaComplaintTypeLookup.findAll", query = "SELECT j FROM JpaComplaintTypeLookup j")
@Getter
public class JpaComplaintTypeLookup {

    private static final long serialVersionUID = 5051231818056187879L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "lang", nullable = false)
    private String lang;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;
}
