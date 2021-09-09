/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_company_staff_title_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company_staff_title_lk")
@NamedQuery(name = "JpaCompanyStaffTitleLookup.findAll", query = "SELECT j FROM JpaCompanyStaffTitleLookup j")
public class JpaCompanyStaffTitleLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -1194192641130288720L;
}
