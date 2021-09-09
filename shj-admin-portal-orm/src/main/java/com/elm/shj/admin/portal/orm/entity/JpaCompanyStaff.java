/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_company_staff database table.
 * @author salzoubi
 * @since 1.0.0
 * **/
@Entity
@Table(name = "shc_company_staff")
@NamedQuery(name = "JpaCompanyStaff.findAll", query = "SELECT j FROM JpaCompanyStaff j")
@Data
@NoArgsConstructor
public class JpaCompanyStaff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "full_name_ar")
    private String fullNameAr;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "id_number", nullable = false)
    private int idNumber;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private JpaCompany company;

    @Column(name = "title_code")
    private String titleCode;

    //TODO update the script mobile number should be varchar
    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "email")
    private String email;

    //TODO it should be not null
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
