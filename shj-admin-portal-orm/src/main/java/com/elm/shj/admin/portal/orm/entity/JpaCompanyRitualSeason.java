package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author salzoubi
 * @version 1.0.0
 * **/
@Entity
@Table(name = "shc_company_ritual_season")
@NamedQuery(name = "JpaCompanyRitualSeason.findAll", query = "SELECT j FROM JpaCompanyRitualSeason j")
@Data
@NoArgsConstructor
public class JpaCompanyRitualSeason implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private JpaCompany companyId;

    @ManyToOne
    @JoinColumn(name = "ritual_season_id",nullable = false)
    private JpaRitualSeason ritualSeasonId;


    @Column(name = "season_start", nullable = false)
    private Date seasonStart;

    @Column(name = "season_end", nullable = false)
    private Date seasonEnd;

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

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
