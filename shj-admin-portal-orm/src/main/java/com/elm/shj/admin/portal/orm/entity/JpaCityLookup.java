/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the shc_complaint_status_lk database table.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Entity
@Table(name = "shc_city_lk")
@NamedQuery(name = "JpaCityLookup.findAll", query = "SELECT j FROM JpaCityLookup j")
@Getter
public class JpaCityLookup {

    private static final long serialVersionUID = 8192101637382934590L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "lang", nullable = false)
    private String lang;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;
}
