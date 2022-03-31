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
 * The persistent class for the shc_user_survey database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_user_survey")
@NamedQuery(name = "shc_user_survey.findAll", query = "SELECT j FROM JpaUserSurvey j")
@Getter
@Setter
@NoArgsConstructor
public class JpaUserSurvey implements Serializable {

    private static final long serialVersionUID = -3659129758207980595L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "digital_id",nullable = false,updatable = false)
    String digitalId;


    @Column(name = "type_code")
    private String surveyType;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }




}