/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_chatbot_item database table.
 *
 * @author rameez imtiaz
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_chatbot_item")
@NamedQuery(name = "JpaChatBotItem.findAll", query = "SELECT j FROM JpaChatBotItem j")
@Getter
@Setter
@NoArgsConstructor
public class JpaChatBotItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "lang", nullable = false)
    private String lang;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "parent_code")
    private String parentCode;
}
