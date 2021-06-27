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
 * The persistent class for the shc_data_request_status_lk database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_data_request_status_lk")
@NamedQuery(name = "JpaDataRequestStatusLookup.findAll", query = "SELECT j FROM JpaDataRequestStatusLookup j")
@Data
@NoArgsConstructor
public class JpaDataRequestStatusLookup implements Serializable {

    private static final long serialVersionUID = 2123456553652227103L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

}
