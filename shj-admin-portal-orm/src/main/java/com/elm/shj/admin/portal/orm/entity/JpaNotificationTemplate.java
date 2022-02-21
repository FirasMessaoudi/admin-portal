/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the shc_notification_template database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_template")
@NamedQuery(name = "JpaNotificationTemplate.findAll", query = "SELECT j FROM JpaNotificationTemplate j")
@Getter
@Setter
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

    @Column(name = "description")
    private String description;

    private boolean important;

    @Column(name = "action_required")
    private boolean actionRequired;

    private boolean enabled;

    @Column(name = "user_specific")
    private boolean userSpecific;

    @Column(name = "force_sending")
    private boolean forceSending;

    @Column(name = "is_processed")
    private Boolean isProcessed;

    @Column(name = "expiration_period_in_minutes")
    private Integer expirationPeriodInMinutes;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "notificationTemplate")
    private Set<JpaUserNotification> userNotifications;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "notificationTemplate")
    private Set<JpaNotificationTemplateContent> notificationTemplateContents;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "notificationTemplate")
    private Set<JpaNotificationRequest> notificationRequests;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "notificationTemplate")
    private Set<JpaNotificationTemplateParameter> notificationTemplateParameters;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "notificationTemplate")
    private JpaNotificationTemplateCategorizing notificationTemplateCategorizing;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "sending_date")
    private Date sendingDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
