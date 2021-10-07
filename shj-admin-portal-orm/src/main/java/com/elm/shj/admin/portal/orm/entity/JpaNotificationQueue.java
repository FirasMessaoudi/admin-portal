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
 * The persistent class for the shc_notification_queue database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_queue")
@NamedQuery(name = "JpaNotificationQueue.findAll", query = "SELECT j FROM JpaNotificationQueue j")
@Getter
@Setter
@NoArgsConstructor
public class JpaNotificationQueue implements Serializable {

    private static final long serialVersionUID = 2666204322272391703L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "notification_template_id")
    private long notificationTemplateId;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne
    @JoinColumn(name = "processing_status_id")
    private JpaNotificationProcessingStatusLookup processingStatus;

    @Column(name = "sending_date")
    private Date sendingDate;

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
