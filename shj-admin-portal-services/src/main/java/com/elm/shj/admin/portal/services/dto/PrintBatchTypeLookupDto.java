/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the print batch type domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrintBatchTypeLookupDto implements Serializable {

    private static final long serialVersionUID = -3472461540347274275L;

    private long id;
    private String labelAr;
    private String labelEn;
    private String code;
    private String target;
    private Date creationDate;
}
