/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurvey;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.Optional;

/**
 * Repository for User Survey table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface UserSurveyRepository extends JpaRepository<JpaUserSurvey, Long> {

    Optional<JpaUserSurvey> findByDigitalIdAndSurveyTypeAndCreationDate(String digitalId, String surveyType, Date today);
}
