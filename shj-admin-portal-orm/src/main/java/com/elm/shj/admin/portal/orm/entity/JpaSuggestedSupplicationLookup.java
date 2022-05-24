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
 * The persistent class for the shc_suggested_supplication_lk database table.
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_suggested_supplication_lk")
@NamedQuery(name = "shc_suggested_supplication_lk", query = "SELECT j FROM JpaSuggestedSupplicationLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaSuggestedSupplicationLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -4197810842271133694L;
}
