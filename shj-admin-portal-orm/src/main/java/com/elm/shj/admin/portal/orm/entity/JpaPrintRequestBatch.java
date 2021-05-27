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
 * The persistent class for the sha_print_request_batch database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_print_request_batch")
@NamedQuery(name = "JpaPrintRequestBatch.findAll", query = "SELECT j FROM JpaPrintRequestBatch j")
@Data
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

    @Column(name = "batch_types")
    private String batchTypes;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "printRequestBatch")
    private List<JpaPrintRequestBatchApplicant> printRequestBatchApplicants;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
}
