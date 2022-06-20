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
 * The persistent class for the shc_package_transportation database table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Entity
@Table(name = "shc_package_transportation")
@NamedQuery(name = "JpaPackageTransportationBasic.findAll", query = "SELECT j FROM JpaPackageTransportationBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPackageTransportationBasic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "validity_start")
    private Date validityStart;

    @Column(name = "validity_end")
    private Date validityEnd;

    @Column(name = "location_from_name_ar")
    private String locationFromNameAr;

    @Column(name = "location_from_name_en")
    private String locationFromNameEn;

    @Column(name = "location_to_name_ar")
    private String locationToNameAr;

    @Column(name = "location_to_name_en")
    private String locationToNameEn;

    @Column(name = "ritual_step_code")
    private String ritualStepCode;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "route_details")
    private String routeDetails;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private JpaRitualPackageBasic ritualPackage;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}