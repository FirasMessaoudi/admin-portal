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
 * The persistent class for the shc_survey_type_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_survey_type_lk")
@NamedQuery(name = "shc_survey_type_lk.findAll", query = "SELECT j FROM JpaSurveyTypeLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaSurveyTypeLookup implements Serializable {

    private static final long serialVersionUID = -7926786767048583374L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String code;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }


}
