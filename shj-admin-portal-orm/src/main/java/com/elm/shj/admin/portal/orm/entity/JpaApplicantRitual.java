/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the shc_applicant_ritual database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_ritual")
@NamedQuery(name = "JpaApplicantRitual.findAll", query = "SELECT j FROM JpaApplicantRitual j")
@Data
@NoArgsConstructor
public class JpaApplicantRitual implements Serializable {

    private static final long serialVersionUID = 8576548128327770418L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    private JpaApplicant applicant;

    @Column(name = "hamlah_package_code")
    private String hamlahPackageCode;

    @Column(name = "tafweej_code")
    private String tafweejCode;

    @Column(name = "zone_code")
    private String zoneCode;

    @Column(name = "group_code")
    private String groupCode;

    @Column(name = "unit_code")
    private String unitCode;

    @Column(name = "hijri_season")
    private int hijriSeason;

    @Column(name = "date_start_gregorian")
    private Date dateStartGregorian;

    @Column(name = "date_end_gregorian")
    private Date dateEndGregorian;

    @Column(name = "date_start_hijri")
    private Long dateStartHijri;

    @Column(name = "date_end_hijri")
    private Long dateEndHijri;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "visa_number")
    private String visaNumber;

    @Column(name = "permit_number")
    private String permitNumber;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "border_number")
    private String borderNumber;
    @Column(name = "bus_number")
    private String busNumber;
    @Column(name = "seat_number")
    private String seatNumber;
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "data_request_record_id")
    private JpaDataRequestRecord dataRequestRecord;

    @ManyToOne
    @JoinColumn(name = "applicant_package_id")
    private JpaApplicantPackage  applicantPackage;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantRitual")
    private List<JpaApplicantRelative> relatives;


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "applicantRitual")
    private List<JpaApplicantContact> contacts;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
        upperCase();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
        upperCase();
    }

    private void upperCase() {
        typeCode = StringUtils.upperCase(typeCode);
    }
}
