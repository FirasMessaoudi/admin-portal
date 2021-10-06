/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_notification_template_type_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_template_type_lk")
@NamedQuery(name = "JpaNotificationTemplateTypeLookup.findAll", query = "SELECT j FROM JpaNotificationTemplateTypeLookup j")
public class JpaNotificationTemplateTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 5674410307344443525L;
}
