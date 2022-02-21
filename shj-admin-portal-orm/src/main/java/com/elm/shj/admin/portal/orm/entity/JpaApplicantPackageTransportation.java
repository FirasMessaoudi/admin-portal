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
 * the persistent class for the shc_applicant_package_transportation table
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_package_transportation")
@NamedQuery(name = "JpaApplicantPackageTransportation.findAll", query = "SELECT j FROM JpaApplicantPackageTransportation j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantPackageTransportation implements Serializable {

    private static final long serialVersionUID = 840517959765875057L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "wagon_number")
    private String wagonNumber;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_package_id")
    private JpaApplicantPackage applicantPackage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ritual_package_transportation_id")
    private JpaPackageTransportation packageTransportation;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
