/*
 * Copyright (c) 2022 ELM. All rights reserved.
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
 * @author rimaz imtiaz
 * @since 1.2.6
 */
@Entity
@Table(name = "shc_applicant_group")
@NamedQuery(name = "JpaApplicantGroupBasic.findAll", query = "SELECT j FROM JpaApplicantGroupBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantGroupBasic implements Serializable {

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

    @Column(name = "group_leader_id", nullable = false)
    private Long groupLeaderId;

    @Column(name = "company_ritual_season_id")
    private Long companyRitualSeasonId;

    @Column(name = "group_type_code")
    private String groupTypeCode;

    @Column(name = "entry_transportation_type_code")
    private String entryTransportationTypeCode;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
