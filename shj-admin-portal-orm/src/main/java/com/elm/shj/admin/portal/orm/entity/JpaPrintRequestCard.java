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
 * The persistent class for the shc_print_request_card database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_print_request_card")
@NamedQuery(name = "JpaPrintRequestCard.findAll", query = "SELECT j FROM JpaPrintRequestCard j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPrintRequestCard implements Serializable {

    private static final long serialVersionUID = -3160791580855210723L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "print_request_id")
    private JpaPrintRequest printRequest;

    @Column(name = "card_id")
    private long cardId;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }
}
