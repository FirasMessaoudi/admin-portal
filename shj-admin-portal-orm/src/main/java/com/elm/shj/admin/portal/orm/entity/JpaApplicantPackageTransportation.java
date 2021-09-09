/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
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

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "application_package_id")
    private JpaApplicantPackage jpaApplicantPackage;

    @ManyToOne
    @JoinColumn(name = "ritual_package_transportation_id")
    private JpaPackageTransportation jpaPackageTransportation;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
