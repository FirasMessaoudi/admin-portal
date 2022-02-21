/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the shc_applicant_group database table.
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_group")
@NamedQuery(name = "JpaApplicantGroup.findAll", query = "SELECT j FROM JpaApplicantGroup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantGroup implements Serializable {

    private static final long serialVersionUID = 2753741851607169463L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "local_office_id")
    private long localOfficeId;

    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "departure_date")
    private Date departureDate;

    @ManyToOne
    @JoinColumn(name = "group_leader_id", nullable = false)
    private JpaCompanyStaff groupLeader;

    @ManyToOne
    @JoinColumn(name = "company_ritual_season_id", nullable = false)
    private JpaCompanyRitualSeason companyRitualSeason;

    @Column(name = "group_type_code")
    private String groupTypeCode;

    @Column(name = "entry_transportation_type_code")
    private String entryTransportationTypeCode;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantGroup")
    private List<JpaGroupApplicantList> groupApplicantLists;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantGroup")
    private List<JpaCompanyRitualStep> companyRitualSteps;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
