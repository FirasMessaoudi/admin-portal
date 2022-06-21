/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_company_staff_digital_id database table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Entity
@Table(name = "shc_company_staff_digital_id")
@NamedQuery(name = "JpaCompanyStaffDigitalIdBasic.findAll", query = "SELECT j FROM JpaCompanyStaffDigitalIdBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyStaffDigitalIdBasic implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String suin;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "season_year", nullable = false)
    private int seasonYear;

    @Column(name = "company_staff_id")
    private long companyStaffId;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
