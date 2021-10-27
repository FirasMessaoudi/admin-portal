/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_religious_occasions_day_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_religious_occasions_day_lk")
@NamedQuery(name = "JpaReligiousOccasionsDayLookup.findAll", query = "SELECT j FROM JpaReligiousOccasionsDayLookup j")

public class JpaReligiousOccasionsDayLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 7656682953308478943L;
}
