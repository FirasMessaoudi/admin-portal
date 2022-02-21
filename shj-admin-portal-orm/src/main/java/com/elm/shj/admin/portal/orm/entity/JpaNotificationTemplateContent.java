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
 * The persistent class for the shc_notification_template_content database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_template_content")
@NamedQuery(name = "JpaNotificationTemplateContent.findAll", query = "SELECT j FROM JpaNotificationTemplateContent j")
@Getter
@Setter
@NoArgsConstructor
public class JpaNotificationTemplateContent implements Serializable {

    private static final long serialVersionUID = 7909007006002532222L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "notification_template_id")
    private JpaNotificationTemplate notificationTemplate;

    private String lang;

    private String title;

    private String body;

    @Column(name = "action_label")
    private String actionLabel;

    @Column(name = "creation_date", nullable = false, updatable = false)
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
