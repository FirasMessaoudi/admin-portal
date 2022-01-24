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
 * The persistent class for the shc_applicant_emergency_data_upload database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_emergency_data_upload")
@NamedQuery(name = "JpaApplicantEmergencyDataUpload.findAll", query = "SELECT j FROM JpaApplicantEmergencyDataUpload j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantEmergencyDataUpload implements Serializable {

    private static final long serialVersionUID = 7563881534714397623L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "date_of_birth_hijri")
    private Long dateOfBirthHijri;

    @Column(name = "date_of_birth_gregorian")
    private Date dateOfBirthGregorian;

    @Column(name = "package_reference_number")
    private String packageReferenceNumber;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
