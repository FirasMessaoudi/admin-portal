/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the shc_applicant_card database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_card")
@NamedQuery(name = "JpaApplicantCard.findAll", query = "SELECT j FROM JpaApplicantCard j")
@Data
@NoArgsConstructor
public class JpaApplicantCard implements Serializable {

    private static final long serialVersionUID = 4881148207869302797L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "applicant_ritual_id")
    private JpaApplicantRitual applicantRitual;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "batch_id")
    private Long batchId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "card")
    private List<JpaPrintRequestCard> printRequestCards;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
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
