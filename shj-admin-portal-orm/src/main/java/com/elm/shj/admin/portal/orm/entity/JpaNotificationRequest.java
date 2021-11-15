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
 * The persistent class for the shc_notification_request database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_request")
@NamedQuery(name = "JpaNotificationRequest.findAll", query = "SELECT j FROM JpaNotificationRequest j")
@Getter
@Setter
@NoArgsConstructor
public class JpaNotificationRequest implements Serializable {

    private static final long serialVersionUID = 2666204322272391703L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "notification_template_id")
    private JpaNotificationTemplate notificationTemplate;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "processing_status_id")
    private JpaNotificationProcessingStatusLookup processingStatus;

    @Column(name = "user_lang")
    private String userLang;

    @Column(name = "sending_date")
    private Date sendingDate;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "notificationRequest")
    private Set<JpaNotificationRequestParameterValue> notificationRequestParameterValues;

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
