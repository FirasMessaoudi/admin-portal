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
 * The persistent class for the shc_user_notification database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_user_notification")
@NamedQuery(name = "JpaUserNotification.findAll", query = "SELECT j FROM JpaUserNotification j")
@Setter
@Getter
@NoArgsConstructor
public class JpaUserNotification implements Serializable {

    private static final long serialVersionUID = 5278838751694591343L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "notification_template_id")
    private JpaNotificationTemplate notificationTemplate;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "resolved_body")
    private String resolvedBody;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "user_lang")
    private String userLang;

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
