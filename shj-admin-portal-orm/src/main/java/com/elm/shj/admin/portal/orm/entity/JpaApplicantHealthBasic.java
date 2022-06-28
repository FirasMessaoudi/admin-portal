/*
 *  Copyright (c) 2022 ELM. All rights reserved.
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
 * The persistent basic class for the shc_applicant_health database table.
 *
 * @author f.messaoudi
 * @since 1.3.1
 */
@Entity
@Table(name = "shc_applicant_health")
@NamedQuery(name = "JpaApplicantHealthBasic.findAll", query = "SELECT j FROM JpaApplicantHealthBasic j")
@Setter
@Getter
@NoArgsConstructor
public class JpaApplicantHealthBasic implements Serializable {

    private static final long serialVersionUID = -8120983359870499965L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @OneToOne
    private JpaApplicantBasic applicant;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "has_special_needs")
    private Boolean hasSpecialNeeds;

    @Column(name = "insurance_policy_number")
    private String insurancePolicyNumber;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthDiseaseBasic> diseases;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthSpecialNeedsBasic> specialNeeds;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthImmunizationBasic> immunizations;

    @Column(name = "package_reference_number")
    private String packageReferenceNumber;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

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
