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
 * The persistent class for the shc_chat_message_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_chat_message_type_lk")
@NamedQuery(name = "JpaChatMessageTypeLookup.findAll", query = "SELECT j FROM JpaChatMessageTypeLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaChatMessageTypeLookup implements Serializable {

    private static final long serialVersionUID = -1096426330247199471L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String code;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}

