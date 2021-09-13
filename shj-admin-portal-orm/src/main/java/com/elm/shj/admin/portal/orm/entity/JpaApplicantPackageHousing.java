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
 * the persistent class for the shc_applicant_package_housing table
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_package_housing")
@NamedQuery(name = "JpaApplicantPackageHousing.findAll", query = "SELECT j FROM JpaApplicantPackageHousing j")
@Data
@NoArgsConstructor
public class JpaApplicantPackageHousing implements Serializable {

    private static final long serialVersionUID = 4952018589817964421L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "bed_number")
    private String bedNumber;


    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "application_package_id")
    private JpaApplicantPackage applicantPackage;

    @ManyToOne
    @JoinColumn(name = "package_housing_id")
    private JpaPackageHousing packageHousing;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}
