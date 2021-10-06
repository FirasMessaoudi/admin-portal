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
 * The persistent class for the shc_notification_processing_status_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_processing_status_lk")
@NamedQuery(name = "JpaNotificationProcessingStatusLookup.findAll", query = "SELECT j FROM JpaNotificationProcessingStatusLookup j")
@Data
@NoArgsConstructor
public class JpaNotificationProcessingStatusLookup implements Serializable {

    private static final long serialVersionUID = 3465691361852655334L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String code;

    private String description;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
}
