/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_company_ritual_season database table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 **/
@Entity
@Table(name = "shc_company_ritual_season")
@NamedQuery(name = "JpaCompanyRitualSeasonBasic.findAll", query = "SELECT j FROM JpaCompanyRitualSeasonBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyRitualSeasonBasic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ritual_season_id", nullable = false)
    private JpaRitualSeasonBasic ritualSeason;

    @Column(name = "season_start", nullable = false)
    private long seasonStart;

    @Column(name = "season_end", nullable = false)
    private long seasonEnd;

    @Column(name = "total_quota")
    private int totalQuota;

    @Column(name = "air_quota")
    private int airQuota;

    @Column(name = "sea_quota")
    private int seaQuota;

    @Column(name = "land_quota")
    private int landQuota;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private JpaCompanyLite company;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "active")
    private boolean isActive;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
