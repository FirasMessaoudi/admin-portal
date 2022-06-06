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
 * The persistent class for the CampCategoryLookup.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class CampCategoryLookupDto implements Serializable {

    private static final long serialVersionUID = 8974868562674729793L;
    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}
