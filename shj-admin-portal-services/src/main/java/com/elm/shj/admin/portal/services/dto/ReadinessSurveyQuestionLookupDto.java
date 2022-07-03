/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaLocalizedLookup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the Readiness Survey Question Lookup.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class ReadinessSurveyQuestionLookupDto implements Serializable {


    private static final long serialVersionUID = -1855045769157727945L;
    private long id;
    private String code;
    private String lang;
    private String label;
    private String questionCategoryCode;
    private Date creationDate;
}
