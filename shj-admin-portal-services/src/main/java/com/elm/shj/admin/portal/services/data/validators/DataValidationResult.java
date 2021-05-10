/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

/**
 * Holder of data validation result and used in data validators
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Data
@Builder
public class DataValidationResult {

    private boolean valid;
    private List<String> errorMessages;
    private Cell cell;
}
