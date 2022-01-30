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
import java.util.Set;

/**
 * The persistent class for the shc_applicant_ritual database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_ritual")
@NamedQuery(name = "JpaApplicantRitual.findAll", query = "SELECT j FROM JpaApplicantRitual j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantRitual implements Serializable {

    private static final long serialVersionUID = 8576548128327770418L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    private JpaApplicant applicant;

    @Column(name = "visa_number")
    private String visaNumber;

    @Column(name = "permit_number")
    private String permitNumber;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "border_number")
    private String borderNumber;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "data_request_record_id")
    private JpaDataRequestRecord dataRequestRecord;

    @OneToOne
    @JoinColumn(name = "applicant_package_id")
    private JpaApplicantPackage applicantPackage;

    @Column(name = "package_reference_number")
    private String packageReferenceNumber;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "applicantRitual")
    private Set<JpaApplicantRelative> relatives;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.REFRESH,  mappedBy = "applicantRitual")
    private Set<JpaApplicantContact> contacts;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE} ,  mappedBy = "applicantRitual")
    private Set<JpaApplicantHealth>  applicantHealths;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "applicantRitual")
    private Set<JpaApplicantIncident> applicantIncidents;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}
