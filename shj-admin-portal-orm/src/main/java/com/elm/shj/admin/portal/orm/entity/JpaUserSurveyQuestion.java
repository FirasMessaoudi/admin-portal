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
 * The persistent class for the shc_user_survey_question database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_user_survey_question")
@NamedQuery(name = "shc_user_survey_question.findAll", query = "SELECT j FROM JpaUserSurveyQuestion j")
@Getter
@Setter
@NoArgsConstructor
public class JpaUserSurveyQuestion implements Serializable {

    private static final long serialVersionUID = -7100959917722621060L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_survey_id")
    private JpaUserSurvey userSurvey;


    @Column(name = "question_code")
    private String surveyQuestion;

    private int rate;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }


}