/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.survey;

import com.elm.shj.admin.portal.orm.entity.JpaReadinessSiteCampQuestion;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSiteCampQuestionDescription;
import com.elm.shj.admin.portal.services.dto.ReadinessSiteCampQuestionDescriptionDto;
import com.elm.shj.admin.portal.services.dto.ReadinessSiteCampQuestionDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling Readiness site camp Question Description
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReadinessSiteCampQuestionDescriptionService extends GenericService<JpaReadinessSiteCampQuestionDescription, ReadinessSiteCampQuestionDescriptionDto, Long> {


}
