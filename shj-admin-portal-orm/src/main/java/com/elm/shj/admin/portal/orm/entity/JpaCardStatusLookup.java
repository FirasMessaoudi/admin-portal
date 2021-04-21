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
 * The persistent class for the sha_card_status_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_card_status_lk")
@NamedQuery(name = "JpaCardStatusLookup.findAll", query = "SELECT j FROM JpaCardStatusLookup j")
@Data
@NoArgsConstructor
public class JpaCardStatusLookup  extends JpaLocalizedLookup {

    private static final long serialVersionUID = 408721068627566736L;
}
