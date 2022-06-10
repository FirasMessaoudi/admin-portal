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
 * The persistent class for the shc_applicant database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant")
@NamedQuery(name = "JpaApplicantBasic.findAll", query = "SELECT j FROM JpaApplicantBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantBasic implements Serializable {

    private static final long serialVersionUID = -6527928280666512305L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String gender;

    @Column(name = "nationality_code")
    private String nationalityCode;

    @Column(name = "date_of_birth_gregorian")
    private Date dateOfBirthGregorian;

    @Column(name = "date_of_birth_hijri")
    private Long dateOfBirthHijri;

    @Column(name = "first_package_reference_number")
    private String packageReferenceNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id", updatable = false)
    private List<JpaApplicantDigitalId> digitalIds;
}
