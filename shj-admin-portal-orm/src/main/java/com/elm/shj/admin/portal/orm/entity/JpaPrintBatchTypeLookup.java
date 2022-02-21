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
 * The persistent class for the shc_print_batch_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_print_batch_type_lk")
@NamedQuery(name = "JpaPrintBatchTypeLookup.findAll", query = "SELECT j FROM JpaPrintBatchTypeLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPrintBatchTypeLookup implements Serializable {

    private static final long serialVersionUID = -4002009572207762908L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    private String code;

    private String target;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;
}
