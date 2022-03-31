/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_user_card_status_audit database table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_user_card_status_audit")
@NamedQuery(name = "JpaUserCardStatusAudit.findAll", query = "SELECT j FROM JpaUserCardStatusAudit j")
@Getter
@Setter
@NoArgsConstructor
public class JpaUserCardStatusAudit implements Serializable {

    private static final long serialVersionUID = -2573018111056761497L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "card_id")
    private long cardId;

    @Column(name = "uin")
    String uin;

    @Column(name = "status_code")
    String statusCode;

    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

}
