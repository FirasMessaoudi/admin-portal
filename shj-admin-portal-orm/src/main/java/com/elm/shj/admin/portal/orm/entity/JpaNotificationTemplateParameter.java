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
 * The persistent class for the shc_notification_template_parameter database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_template_parameter")
@NamedQuery(name = "JpaNotificationTemplateParameter.findAll", query = "SELECT j FROM JpaNotificationTemplateParameter j")
@Getter
@Setter
@NoArgsConstructor
public class JpaNotificationTemplateParameter implements Serializable {

    private static final long serialVersionUID = 5713476250528328161L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "notification_template_id")
    private long notificationTemplateId;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

}
