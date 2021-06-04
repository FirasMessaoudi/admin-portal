/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the sha_print_request database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_print_request")
@NamedQuery(name = "JpaPrintRequest.findAll", query = "SELECT j FROM JpaPrintRequest j")
@Data
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

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "printRequest")
    private List<JpaPrintRequestCard> printRequestCards;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, orphanRemoval = true, mappedBy = "printRequest")
    private List<JpaPrintRequestBatch> printRequestBatches;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
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
