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
 * The persistent class for the shc_applicant_complaint database table.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Entity
@Table(name = "shc_applicant_complaint")
@NamedQuery(name = "JpaApplicantComplaint.findAll", query = "SELECT j FROM JpaApplicantComplaint j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantComplaint implements Serializable {

    private static final long serialVersionUID = 893621013109878978L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "reference_number")
    private String referenceNumber;

    @ManyToOne
    @JoinColumn(name = "applicant_ritual_id")
    private JpaApplicantRitual applicantRitual;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "type_code")
    private String typeCode;

    private String description;

    @Column(name = "location_lat")
    private Double locationLat;

    @Column(name = "location_lng")
    private Double locationLng;

    @Column(name = "resolution_comment")
    private String resolutionComment;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantComplaint")
    private JpaComplaintAttachment complaintAttachment;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "city")
    private String city;

    @Column(name = "camp_number")
    private String campNumber;

    @Column(name = "crm_ticket_number")
    private String crmTicketNumber;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
