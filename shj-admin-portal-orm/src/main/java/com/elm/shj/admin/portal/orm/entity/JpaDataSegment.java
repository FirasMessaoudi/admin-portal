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
 * The persistent class for the sha_data_segment database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_data_segment")
@NamedQuery(name = "JpaDataSegment.findAll", query = "SELECT j FROM JpaDataSegment j")
@Data
@NoArgsConstructor
public class JpaDataSegment implements Serializable {

    private static final long serialVersionUID = 2532495233381225382L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "label_ar")
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    @Column(name = "root_class", nullable = false)
    private String rootClass;

    @Column(name = "template_file_name", nullable = false)
    private String templateFileName;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;
}
