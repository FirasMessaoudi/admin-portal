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
 * The persistent class for the shc_user_notification_category_preference database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_user_notification_category_preference")
@NamedQuery(name = "JpaUserNotificationCategoryPreference.findAll", query = "SELECT j FROM JpaUserNotificationCategoryPreference j")
@Getter
@Setter
@NoArgsConstructor
public class JpaUserNotificationCategoryPreference implements Serializable {

    private static final long serialVersionUID = 5680524697330927757L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "category_code")
    private String categoryCode;

    private boolean enabled;

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
