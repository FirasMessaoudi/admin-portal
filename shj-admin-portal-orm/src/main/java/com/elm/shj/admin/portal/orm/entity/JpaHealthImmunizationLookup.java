/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sha_health_immunization_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_health_immunization_lk")
@NamedQuery(name = "JpaHealthImmunizationLookup.findAll", query = "SELECT j FROM JpaHealthImmunizationLookup j")
@Data
@NoArgsConstructor
public class JpaHealthImmunizationLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -1048551190373439763L;
}
