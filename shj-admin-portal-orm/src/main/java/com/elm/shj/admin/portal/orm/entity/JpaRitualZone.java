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
 * The persistent class for the shc_ritual_zone database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_ritual_zone")
@NamedQuery(name = "JpaRitualZone.findAll", query = "SELECT j FROM JpaRitualZone j")
@Getter
@Setter
@NoArgsConstructor
public class JpaRitualZone implements Serializable {

    private static final long serialVersionUID = -4198943722949330755L;

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

    @Column(name = "season_id")
    private long seasonId;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
