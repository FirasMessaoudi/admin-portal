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
 * The persistent class for the shc_company_staff_card database table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 **/
@Entity
@Table(name = "shc_company_staff_card")
@NamedQuery(name = "JpaCompanyStaffCard.findAll", query = "SELECT j FROM JpaCompanyStaffCard j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyStaffCard implements Serializable {

    private static final long serialVersionUID = 6145002801170303108L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "status_code", nullable = false)
    private String statusCode;

    @ManyToOne
    @JoinColumn(name = "company_staff_digital_id_id", nullable = false)
    private JpaCompanyStaffDigitalId companyStaffDigitalId;

    @ManyToOne
    @JoinColumn(name = "company_ritual_season_id", nullable = false)
    private JpaCompanyRitualSeason companyRitualSeason;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "batch_number")
    private Long batchNumber;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
