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
import java.util.List;

/**
 * The persistent class for the shc_company_staff database table.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
@Entity
@Table(name = "shc_company_ritual_season")
@NamedQuery(name = "JpaCompanyRitualSeason.findAll", query = "SELECT j FROM JpaCompanyRitualSeason j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyRitualSeason implements Serializable {

    private static final long serialVersionUID = -973537367560574699L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private JpaCompany company;

    @ManyToOne
    @JoinColumn(name = "ritual_season_id", nullable = false)
    private JpaRitualSeason ritualSeason;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "companyRitualSeason")
    private List<JpaRitualPackage> ritualPackages;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "companyRitualSeason")
    private List<JpaApplicantGroup> applicantGroups;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "companyRitualSeason")
    private List<JpaCompanyStaffCard> companyStaffCards;

    @Column(name = "season_start", nullable = false)
    private int seasonStart;

    @Column(name = "season_end", nullable = false)
    private int seasonEnd;

    @Column(name = "total_quota")
    private int totalQuota;

    @Column(name = "air_quota")
    private int airQuota;

    @Column(name = "sea_quota")
    private int seaQuota;

    @Column(name = "land_quota")
    private int landQuota;

    @Column(name = "creation_date")
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
