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
 * The persistent class for the sha_applicant_health database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_applicant_health")
@NamedQuery(name = "JpaApplicantHealth.findAll", query = "SELECT j FROM JpaApplicantHealth j")
@Data
@NoArgsConstructor
public class JpaApplicantHealth implements Serializable {

    private static final long serialVersionUID = -8120983359870499965L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    private JpaApplicant applicant;

    @Column(name ="blood_type")
    private String bloodType;

    @Column(name ="has_special_needs")
    private Boolean hasSpecialNeeds;

    @Column(name ="insurance_policy_number")
    private String insurancePolicyNumber;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "data_request_record_id")
    private JpaDataRequestRecord dataRequestRecordId;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthDisease> diseases;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthSpecialNeeds> specialNeeds;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthImmunization> immunizations;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
