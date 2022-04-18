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
 * The persistent class for the shc_supplication_user_counter database table.
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_supplication_user_counter")
@NamedQuery(name = "shc_supplication_user_counter", query = "SELECT j FROM JpaSupplicationUserCounter  j")
@Getter
@Setter
@NoArgsConstructor
public class JpaSupplicationUserCounter implements Serializable {
    private static final long serialVersionUID = -8498574394313486947L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String code;

    @Column(name = "digital_id",nullable = false,updatable = false)
    private String digitalId;

    private boolean suggested;

    @Column(name = "supplication_total_count")
    private int supplicationTotalCount;

    @Column(name = "supplication_last_count")
    private int supplicationLastCount;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false, updatable = false)
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
