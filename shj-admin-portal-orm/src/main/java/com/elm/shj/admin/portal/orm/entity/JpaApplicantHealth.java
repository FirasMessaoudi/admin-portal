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

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthDisease> diseases;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthSpecialNeeds> specialNeeds;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantHealth")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<JpaApplicantHealthImmunization> immunizations;
}
