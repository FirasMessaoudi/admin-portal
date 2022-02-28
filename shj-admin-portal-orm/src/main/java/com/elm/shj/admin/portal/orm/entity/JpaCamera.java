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
 * The persistent class for the shc_camera database table.
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_camera")
@NamedQuery(name = "JpaCamera.findAll", query = "SELECT j FROM JpaCamera j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCamera implements Serializable {

    private static final long serialVersionUID = -4152985135548776915L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String status;

    private String url;

    @Column(name = "creation_hijri_year")
    private int creationHijriYear;

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
