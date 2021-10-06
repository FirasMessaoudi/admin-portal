/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_notification_template database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_template")
@NamedQuery(name = "JpaNotificationTemplate.findAll", query = "SELECT j FROM JpaNotificationTemplate j")
@Data
@NoArgsConstructor
public class JpaNotificationTemplate implements Serializable {

    private static final long serialVersionUID = 259111822250486205L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "name_code")
    private String nameCode;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "type_code")
    private String typeCode;

    private boolean important;

    @Column(name = "action_required")
    private boolean actionRequired;

    private boolean enabled;

    @Column(name = "user_specific")
    private boolean userSpecific;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
