/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

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
@NamedQuery(name = "shc_readiness_survey_question_lk.findAll", query = "SELECT j FROM JpaReadinessSurveyQuestionLookup j")
public class JpaReadinessSurveyQuestionLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 7776451199526886498L;
}
