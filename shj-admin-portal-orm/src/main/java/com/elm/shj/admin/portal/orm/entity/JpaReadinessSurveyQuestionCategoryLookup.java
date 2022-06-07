/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_readiness_survey_question_category_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_readiness_survey_question_category_lk")
@NamedQuery(name = "JpaReadinessSurveyQuestionCategoryLookup.findAll", query = "SELECT j FROM JpaReadinessSurveyQuestionCategoryLookup j")
public class JpaReadinessSurveyQuestionCategoryLookup extends JpaLocalizedLookup {


    private static final long serialVersionUID = 4909247215845149994L;
}
