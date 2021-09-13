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
import java.util.Set;

/**
 * The persistent class for the shc_company database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company")
@NamedQuery(name = "JpaCompany.findAll", query = "SELECT j FROM JpaCompany j")
@Data
@NoArgsConstructor
public class JpaCompany implements Serializable {

    private static final long serialVersionUID = -2301454504762626185L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    @Column(name = "mission_id", nullable = false)
    private int missionId;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "website")
    private String website;

    @Column(name = "accreditation_organization", nullable = false)
    private String accreditationOrganization;

    @Column(name = "accreditation_number", nullable = false)
    private String accreditationNumber;

    @Column(name = "accreditation_date", nullable = false)
    private Date accreditationDate;

    @Column(name = "accreditation_expiry", nullable = false)
    private Date accreditationExpiry;

    @Column(name = "email")
    private String email;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "company")
    private Set<JpaCompanyStaff> companyStaff;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "company")
    private Set<JpaCompanyRitualSeason> companyRitualSeasons;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}
