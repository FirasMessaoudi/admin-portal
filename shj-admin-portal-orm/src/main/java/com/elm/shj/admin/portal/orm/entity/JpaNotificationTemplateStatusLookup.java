/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_notification_template_status_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_template_status_lk")
@NamedQuery(name = "JpaNotificationTemplateStatusLookup.findAll", query = "SELECT j FROM JpaNotificationTemplateStatusLookup j")
public class JpaNotificationTemplateStatusLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -8539704267357586716L;
}
