/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant group domain.
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupNameLookupDto implements Serializable {

    private static final long serialVersionUID = 7617423887048517970L;

    private String code;
    private String label;

}
