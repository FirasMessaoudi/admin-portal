/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the sha_data_segment_field_mapping database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_data_segment_field_mapping")
@NamedQuery(name = "JpaDataSegmentFieldMapping.findAll", query = "SELECT j FROM JpaDataSegmentFieldMapping j")
@Data
@NoArgsConstructor
public class JpaDataSegmentFieldMapping implements Serializable {

    private static final long serialVersionUID = 2532495233381225382L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "header_name_ar")
    private String headerNameAr;

    @Column(name = "header_name_en", nullable = false)
    private String headerNameEn;

    @Column(name = "field_name", nullable = false)
    private String fieldName;

    @Column(name = "field_type", nullable = false)
    private String fieldType;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;
}
