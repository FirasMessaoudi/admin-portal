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
@NamedQuery(name = "JpaApplicantComplaintLite.findAll", query = "SELECT j FROM JpaApplicantComplaintLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantComplaintLite implements Serializable {

    private static final long serialVersionUID = 3754291597972237947L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "applicant_ritual_id")
    private long applicantRitualId;

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

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "city")
    private String city;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantComplaint")
    private JpaComplaintAttachmentLite  complaintAttachment;

    @Column(name = "camp_number")
    private String campNumber;

    @Column(name = "crm_ticket_number")
    private String crmTicketNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
