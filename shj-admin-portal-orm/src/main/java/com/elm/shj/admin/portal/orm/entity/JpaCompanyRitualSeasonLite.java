/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the shc_company_staff database table.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 **/
@Entity
@Table(name = "shc_company_ritual_season")
@NamedQuery(name = "JpaCompanyRitualSeasonLite.findAll", query = "SELECT j FROM JpaCompanyRitualSeasonLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyRitualSeasonLite implements Serializable {

    private static final long serialVersionUID = 3136379205324295632L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ritual_season_id", nullable = false)
    private JpaRitualSeason ritualSeason;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "companyRitualSeason")
    private List<JpaRitualPackage> ritualPackages;

    @Column(name = "season_start", nullable = false)
    private long seasonStart;

    @Column(name = "season_end", nullable = false)
    private long seasonEnd;

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
