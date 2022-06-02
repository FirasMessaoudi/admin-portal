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
 * The persistent class for the shc_inspector_readiness_survey_result domain.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_inspector_readiness_survey_result")
@NamedQuery(name = "shc_inspector_readiness_survey_result.findAll", query = "SELECT j FROM JpaInspectorReadinessSurveyResult j")
@Getter
@Setter
@NoArgsConstructor
public class JpaInspectorReadinessSurveyResult implements Serializable {


    private static final long serialVersionUID = 4520458744230437008L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "inspector_readiness_survey_id ")
    private int inspectorReadinessSurveyId ;

    @Column(name = "question_code")
    private String questionCode ;

    @Column(name = "rate ")
    private int rate;

    @Column(name = "creation_date", nullable = false, updatable = false)
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