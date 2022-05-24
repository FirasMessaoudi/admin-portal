/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for User Survey Question table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface UserSurveyQuestionRepository extends JpaRepository<JpaUserSurveyQuestion, Long> {
    @Query("select s.rate from JpaUserSurveyQuestion s where :userSurveyId=s.userSurvey.id ")
    List<Integer> findRateByUserSurveyId(@Param("userSurveyId") long userSurveyId);
}
