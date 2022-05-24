/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Value object class for given batch main collection
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Setter
@Getter
public class BatchCollectionVO {
    private String batchReferenceNumber;
    private String target;
    private String locale;
    private List<BatchMainCollectionDto> batchMainCollections;

}
