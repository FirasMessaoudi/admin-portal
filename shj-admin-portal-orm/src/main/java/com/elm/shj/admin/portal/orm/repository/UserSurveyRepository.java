/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;


import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Optional;

/**
 * Repository for User Survey table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface UserSurveyRepository extends JpaRepository<JpaUserSurvey, Long> {

    Optional<JpaUserSurvey> findByDigitalIdAndSurveyTypeAndSurveyDate(String digitalId, String surveyType, Date today);
    Optional<JpaUserSurvey> findByDigitalIdAndSurveyType(String digitalId, String surveyType);

}
