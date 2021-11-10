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
 * The persistent class for the shc_notification_category_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_category_lk")
@NamedQuery(name = "JpaNotificationCategoryLookup.findAll", query = "SELECT j FROM JpaNotificationCategoryLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaNotificationCategoryLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -296706128414877088L;
    private String sample;
}
