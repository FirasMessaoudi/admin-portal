/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * The persistent class for the shc_survey_question_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_survey_question_lk")
@NamedQuery(name = "shc_survey_question_lk.findAll", query = "SELECT j FROM JpaSurveyQuestionLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaSurveyQuestionLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 475930058595116447L;

    @Column(name = "survey_type_code",nullable = false)
    private String surveyTypeCode;

    @Column(name= "question_index", nullable = false)
    private int questionIndex;

}
