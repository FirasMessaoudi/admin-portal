/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sha_print_batch_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_print_batch_type_lk")
@NamedQuery(name = "JpaPrintBatchTypeLookup.findAll", query = "SELECT j FROM JpaPrintBatchTypeLookup j")
public class JpaPrintBatchTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -3686342256387455834L;
}
