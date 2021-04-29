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
 * The persistent class for the sha_ritual_type_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_ritual_type_lk")
@NamedQuery(name = "JpaRitualTypeLookup.findAll", query = "SELECT j FROM JpaRitualTypeLookup j")
public class JpaRitualTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -8272748979397418405L;

}
