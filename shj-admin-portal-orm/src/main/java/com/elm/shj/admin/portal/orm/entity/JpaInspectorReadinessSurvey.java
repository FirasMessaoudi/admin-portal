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
 * The persistent class for the shc_inspector_readiness_survey database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_inspector_readiness_survey")
@NamedQuery(name = "shc_inspector_readiness_survey.findAll", query = "SELECT j FROM JpaInspectorReadinessSurvey j")
@Getter
@Setter
@NoArgsConstructor
public class JpaInspectorReadinessSurvey implements Serializable {


    private static final long serialVersionUID = 5668366773556309625L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "suin", nullable = false, updatable = false)
    String suin;


    @Column(name = "camp_number")
    private String campNumber;

    @Column(name = "camp_site_code")
    private String campSiteCode;

    @Column(name = "camp_category_code")
    private String campCategoryCode;

    @Column(name = "internal_company_code")
    private String internalCompanyCode;

    @Column(name = "establishment_company_code")
    private String establishmentCompanyCode;

    @Column(name = "service_group_company_code")
    private String serviceGroupCompanyCode;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;



    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }




}