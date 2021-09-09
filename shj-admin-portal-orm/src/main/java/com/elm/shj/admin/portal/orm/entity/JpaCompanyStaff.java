package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (c) 2021 ELM. All rights reserved.
 * @author salzoubi
 * @since 1.0.0
 * **/
@Entity
@Table(name = "shc_company_staff")
@NamedQuery(name = "JpaCompanyStaff.findAll", query = "SELECT staff FROM JpaCompanyStaff staff")
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
