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
 * The persistent class for the shc_print_request database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_print_request")
@NamedQuery(name = "JpaPrintRequest.findAll", query = "SELECT j FROM JpaPrintRequest j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPrintRequest implements Serializable {

    private static final long serialVersionUID = 4611926369688617485L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "status_code", nullable = false)
    private String statusCode;

    private String description;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, mappedBy = "printRequest")
    private Set<JpaPrintRequestCard> printRequestCards;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, mappedBy = "printRequest")
    private Set<JpaPrintRequestBatch> printRequestBatches;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "confirmation_date")
    private Date confirmationDate;

    @Column(name = "target", nullable = false)
    private String target;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
