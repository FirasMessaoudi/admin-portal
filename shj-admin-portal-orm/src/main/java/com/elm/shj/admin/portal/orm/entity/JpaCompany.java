/*
 *  Copyright (c) 2021 ELM. All rights reserved.
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
 * The persistent class for the shc_company database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company")
@NamedQuery(name = "JpaCompany.findAll", query = "SELECT j FROM JpaCompany j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompany implements Serializable {

    private static final long serialVersionUID = -2301454504762626185L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "company_uid", nullable = false)
    private String uid;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en")
    private String labelEn;

    @Column(name = "mission_ref_code")
    private Long missionRefCode;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "website")
    private String website;

    @Column(name = "accreditation_organization")
    private String accreditationOrganization;

    @Column(name = "accreditation_number")
    private String accreditationNumber;

    @Column(name = "accreditation_date")
    private Date accreditationDate;

    @Column(name = "accreditation_expiry")
    private Date accreditationExpiry;

    @Column(name = "email")
    private String email;

    @Column(name = "moi_number")
    private String moiNumber;

    @Column(name = "cr_number")
    private String crNumber;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "establishment_ref_code")
    private Integer establishmentRefCode;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "company")
    private List<JpaCompanyRitualSeason> companyRitualSeasons;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}
