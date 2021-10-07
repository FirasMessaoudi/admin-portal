/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_notification_template_name_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_template_name_lk")
@NamedQuery(name = "JpaNotificationTemplateNameLookup.findAll", query = "SELECT j FROM JpaNotificationTemplateNameLookup j")
public class JpaNotificationTemplateNameLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -884150718958037217L;
}
