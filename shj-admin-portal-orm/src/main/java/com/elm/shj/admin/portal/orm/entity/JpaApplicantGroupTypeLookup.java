/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_applicant_group_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_group_type_lk")
@NamedQuery(name = "JpaApplicantGroupTypeLookup.findAll", query = "SELECT j FROM JpaApplicantGroupTypeLookup j")
public class JpaApplicantGroupTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 3241131070777112264L;
}
