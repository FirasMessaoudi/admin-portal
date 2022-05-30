/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_company_type_lk database table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company_type_lk")
@NamedQuery(name = "JpaCompanyTypeLookup.findAll", query = "SELECT j FROM JpaCompanyTypeLookup j")
public class JpaCompanyTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -2392304678806716967L;
}
