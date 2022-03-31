/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the shs_batch_main_collection database table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shs_batch_main_collection")
@NamedQuery(name = "JpaBatchMainCollection.findAll", query = "SELECT j FROM JpaBatchMainCollection j")
@Getter
@Setter
@NoArgsConstructor
public class JpaBatchMainCollection implements Serializable {

    private static final long serialVersionUID = 3216859978489027502L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;


    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @Column(name = "status_code")
    private String statusCode;

    private String url;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
