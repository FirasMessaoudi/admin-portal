/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.survey;

import com.elm.shj.admin.portal.orm.entity.JpaReadinessSiteCampQuestion;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSurveyQuestionCategoryLookup;
import com.elm.shj.admin.portal.orm.entity.ReadinessSiteCampQuestionVO;
import com.elm.shj.admin.portal.orm.repository.ReadinessSiteCampQuestionRepository;
import com.elm.shj.admin.portal.services.dto.ReadinessSiteCampQuestionDto;
import com.elm.shj.admin.portal.services.dto.ReadinessSurveyQuestionCategoryLookupDto;
import com.elm.shj.admin.portal.services.dto.ReadinessSurveyQuestionLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling Readiness site camp Question
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReadinessSiteCampQuestionService extends GenericService<JpaReadinessSiteCampQuestion, ReadinessSiteCampQuestionDto, Long> {

    private final ReadinessSiteCampQuestionRepository readinessSiteCampQuestionDto;
    public List<ReadinessSiteCampQuestionVO> findAllBySiteCodeAndCampCategoryCode(String campSiteCode, String campCategoryCode, String lang ){
        lang = lang == null || lang.trim().isEmpty() ? "ar":lang;
       return readinessSiteCampQuestionDto.findAllBySiteCodeAndCampCategoryCode(campSiteCode,campCategoryCode,lang);
    }

}
