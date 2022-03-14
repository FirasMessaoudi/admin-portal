/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User Survey Question table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface UserSurveyQuestionRepository extends JpaRepository<JpaUserSurveyQuestion, Long> {

}
