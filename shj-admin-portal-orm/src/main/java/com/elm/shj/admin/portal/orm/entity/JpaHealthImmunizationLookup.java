/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_health_immunization_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_health_immunization_lk")
@NamedQuery(name = "JpaHealthImmunizationLookup.findAll", query = "SELECT j FROM JpaHealthImmunizationLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaHealthImmunizationLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -1048551190373439763L;
}
