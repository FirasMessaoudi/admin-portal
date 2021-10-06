/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.processor;

import com.elm.shj.admin.portal.services.data.validators.DataValidationResult;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

import java.util.AbstractMap;
import java.util.List;

/**
 * Class holder of the result of the data request processing
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Getter
@Setter
public class DataProcessorResult<T> {
    boolean withErrors;
    List<AbstractMap.SimpleEntry<Row, T>> parsedItems;
    List<DataValidationResult> dataValidationResults;
}
