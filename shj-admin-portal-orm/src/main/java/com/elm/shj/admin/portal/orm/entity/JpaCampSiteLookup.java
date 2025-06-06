/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_camp_site_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_camp_site_lk")
@NamedQuery(name = "JpaCampSiteLookup.findAll", query = "SELECT j FROM JpaCampSiteLookup j")
public class JpaCampSiteLookup extends JpaLocalizedLookup {


    private static final long serialVersionUID = 14189580971450926L;
}
