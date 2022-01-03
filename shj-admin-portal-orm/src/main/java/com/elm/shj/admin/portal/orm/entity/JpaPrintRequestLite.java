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
 * The light entity version for the shc_print_request database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_print_request")
@NamedQuery(name = "JpaPrintRequestLite.findAll", query = "SELECT j FROM JpaPrintRequestLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPrintRequestLite implements Serializable {

    private static final long serialVersionUID = 8639642227761248781L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "reference_number")
    private String referenceNumber;

    private String description;

    @Column(name = "status_code", nullable = false)
    private String statusCode;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "confirmation_date")
    private Date confirmationDate;
}
