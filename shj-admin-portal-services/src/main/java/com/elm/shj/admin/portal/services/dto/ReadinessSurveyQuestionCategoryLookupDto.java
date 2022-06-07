/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the ReadinessSurveyQuestionCategoryLookup.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class ReadinessSurveyQuestionCategoryLookupDto implements Serializable {
    private static final long serialVersionUID = 461538246827770568L;
    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}
