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
 * The persistent class for the shc_applicant_incident database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_incident")
@NamedQuery(name = "JpaApplicantIncident.findAll", query = "SELECT j FROM JpaApplicantIncident j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantIncident implements Serializable {

    private static final long serialVersionUID = 3754291597972237947L;

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
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantIncident")
    private List<JpaIncidentAttachment>  incidentAttachments;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;


    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
