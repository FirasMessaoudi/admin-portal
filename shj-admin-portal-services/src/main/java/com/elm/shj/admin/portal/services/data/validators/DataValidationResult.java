/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

/**
 * Holder of data validation result and used in data validators
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Getter
@Setter
@Builder
public class DataValidationResult {

    private boolean valid;
    private List<String> errorMessages;
    private Cell cell;
    private String attributeName;
}
