/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
/**
 * Dto class for the print request card domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrintRequestCardDto implements Serializable {

    private static final long serialVersionUID = -5235991770935109142L;
    private long id;
    @JsonBackReference
    private PrintRequestDto printRequest;
    private CardVO card;
    private long cardId;
    private Date creationDate;
}