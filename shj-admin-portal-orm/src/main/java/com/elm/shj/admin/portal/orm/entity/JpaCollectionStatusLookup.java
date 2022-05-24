/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_batch_main_collection_status_lk database table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_batch_main_collection_status_lk")
@NamedQuery(name = "JpaBatchMainCollectionStatusLookup.findAll", query = "SELECT j FROM JpaCollectionStatusLookup j")
public class JpaCollectionStatusLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -8390783034766649764L;
}
