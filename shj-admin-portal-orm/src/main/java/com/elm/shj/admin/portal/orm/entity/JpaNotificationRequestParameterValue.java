/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_notification_request_parameter_value database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_request_parameter_value")
@NamedQuery(name = "JpaNotificationRequestParameterValue.findAll", query = "SELECT j FROM JpaNotificationRequestParameterValue j")
@Getter
@Setter
@NoArgsConstructor
public class JpaNotificationRequestParameterValue implements Serializable {

    private static final long serialVersionUID = -4338656047195314305L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "notification_request_id")
    private long notificationRequestId;

    @Column(name = "notification_template_parameter_id")
    private long notificationTemplateParameterId;

    @Column(name = "notification_template_parameter_value")
    private String notificationTemplateParameterValue;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
