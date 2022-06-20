/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_company_ritual_step_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company_ritual_step_lk")
@NamedQuery(name = "JpaCompanyRitualStepLookup.findAll", query = "SELECT j FROM JpaCompanyRitualStepLookup j")
@NoArgsConstructor
@Getter
@Setter
public class JpaCompanyRitualStepLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -913161328642286375L;

    private String description;

    private String summary;
    @Column(name = "step_index")
    private long stepIndex;
    @Column(name = "edit_mode")
    private String editMode;
    @Column(name = "location_lat")
    private double locationLat;
    @Column(name = "location_lng")
    private double locationLng;

}
