/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the shc_applicant_health database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_health")
@NamedQuery(name = "JpaApplicantHealth.findAll", query = "SELECT j FROM JpaApplicantHealth j")
@Setter
@Getter
@NoArgsConstructor
public class JpaApplicantHealth implements Serializable {

    private static final long serialVersionUID = -8120983359870499965L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @OneToOne
    private JpaApplicant applicant;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "has_special_needs")
    private Boolean hasSpecialNeeds;

    @Column(name = "insurance_policy_number")
    private String insurancePolicyNumber;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_ritual_id")
    private JpaApplicantRitual applicantRitual;

    @Column(name = "data_request_record_id")
    private Long dataRequestRecordId;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "applicantHealth", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthDisease> diseases;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "applicantHealth", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthSpecialNeeds> specialNeeds;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "applicantHealth", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthImmunization> immunizations;

    @Column(name = "package_reference_number")
    private String packageReferenceNumber;

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
        bloodType = StringUtils.upperCase(bloodType);
    }
}
