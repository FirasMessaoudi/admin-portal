/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the sha_print_batch_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_print_batch_type_lk")
@NamedQuery(name = "JpaPrintBatchTypeLookup.findAll", query = "SELECT j FROM JpaPrintBatchTypeLookup j")
@Data
@NoArgsConstructor
public class JpaPrintBatchTypeLookup {

    private static final long serialVersionUID = -3686342256387455834L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    private String code;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
}
