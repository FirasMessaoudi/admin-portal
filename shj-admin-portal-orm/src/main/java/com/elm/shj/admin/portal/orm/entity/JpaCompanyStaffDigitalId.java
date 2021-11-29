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

/**
 * The persistent class for the shc_company_staff_digital_id database table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company_staff_digital_id")
@NamedQuery(name = "JpaCompanyStaffDigitalId.findAll", query = "SELECT j FROM JpaCompanyStaffDigitalId j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyStaffDigitalId implements Serializable {

    private static final long serialVersionUID = 4640576836929026187L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(nullable = false)
    private String suin;

    @Column(name = "season_year", nullable = false)
    private int seasonYear;

    @ManyToOne
    @JoinColumn(name = "company_staff", nullable = false)
    private JpaCompanyStaff companyStaff;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
