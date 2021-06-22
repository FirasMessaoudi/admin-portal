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
import java.util.List;

/**
 * The persistent class for the shc_print_request_batch database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_print_request_batch")
@NamedQuery(name = "JpaPrintRequestBatch.findAll", query = "SELECT j FROM JpaPrintRequestBatch j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPrintRequestBatch implements Serializable {

    private static final long serialVersionUID = -3916461534422001645L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "print_request_id")
    private JpaPrintRequest printRequest;

    @Column(name = "sequence_number", nullable = false)
    private int sequenceNumber;

    @Column(name = "batch_type_codes")
    private String batchTypeCodes;

    @Column(name = "batch_type_values")
    private String batchTypeValues;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "printRequestBatch")
    private List<JpaPrintRequestBatchCard> printRequestBatchCards;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
