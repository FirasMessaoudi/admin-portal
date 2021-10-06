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
 * The persistent class for the shc_audit_log database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_audit_log")
@NamedQuery(name = "JpaAuditLog.findAll", query = "SELECT j FROM JpaAuditLog j")
@Getter
@Setter
@NoArgsConstructor
public class JpaAuditLog implements Serializable {

    private static final long serialVersionUID = 2532495233381225382L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "user_id_number", nullable = false)
    private long userIdNumber;

    @Column(nullable = false)
    private String handler;

    @Column(nullable = false)
    private String action;

    @Column
    private String params;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private String origin;

    @Column
    private String channel;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "processing_time", nullable = false)
    private long processingTime;

    @Column(name = "http_status", nullable = false)
    private int httpStatus;

    @Column(name = "error_details")
    private String errorDetails;
}
