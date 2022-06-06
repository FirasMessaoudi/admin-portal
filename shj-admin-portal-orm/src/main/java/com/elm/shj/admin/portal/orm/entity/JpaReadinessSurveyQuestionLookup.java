/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_readiness_survey_question_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_readiness_survey_question_lk")
@NamedQuery(name = "JpaReadinessSurveyQuestionLookup.findAll", query = "SELECT j FROM JpaReadinessSurveyQuestionLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaReadinessSurveyQuestionLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 7776451199526886498L;

    @Column(name = "question_category_code")
    String questionCategoryCode;
}
