/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaLocalizedLookup;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the Readiness Survey Question Lookup database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */

public class ReadinessSurveyQuestionLookupDto implements Serializable {

    private static final long serialVersionUID = -1855045769157727945L;
    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}
