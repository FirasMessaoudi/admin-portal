package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the shc_applicant_package database table.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company_season_package")
@NamedQuery(name = "JpaCompanySeasonPackage.findAll", query = "SELECT j FROM JpaCompanySeasonPackage j")
@Data
@NoArgsConstructor
public class JpaCompanySeasonPackage {

    private static final long serialVersionUID = -7509421286587785270L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "company_season_ritual_id")
    private JpaCompanyRitualSeason companyRitualSeason;

    @ManyToOne
    @JoinColumn(name = "basic_package_id")
    private JpaRitualPackage ritualPackage;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
