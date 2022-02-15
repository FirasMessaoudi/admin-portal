/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_mobile_audit_log database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_mobile_audit_log")
@NamedQuery(name = "JpaMobileAuditLog.findAll", query = "SELECT j FROM JpaMobileAuditLog j")
@Getter
@Setter
@NoArgsConstructor
public class JpaMobileAuditLog implements Serializable {

    private static final long serialVersionUID = 282539593044131109L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "user_id_number")
    private String userIdNumber;

    private String event;

    @Column(name = "event_date")
    private Date eventDate;

    @PrePersist
    public void prePersist() {
        this.eventDate = new Date();
    }
}
