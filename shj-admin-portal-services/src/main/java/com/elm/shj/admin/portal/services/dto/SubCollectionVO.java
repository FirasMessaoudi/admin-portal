/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Dto class for the SubCollection  .
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCollectionVO implements Serializable {


    private static final long serialVersionUID = 7575765144322740810L;

    private long id;
    private String referenceNumber;
    private List<String> digitalIds;

}
