/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

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
public class JpaCompanyRitualStepLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -913161328642286375L;
}
