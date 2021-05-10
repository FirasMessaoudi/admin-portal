/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.mapper;

import com.elm.shj.admin.portal.services.data.validators.AbstractDataValidator;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.util.List;
import java.util.Map;

/**
 * Abstract Row Mapper
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Data
public abstract class AbstractRowMapper<T> {

    /**
     * Maps the row content to the POJO
     *
     * @param row the current row
     * @return the result of the mapping
     */
    public abstract T mapRow(Row row);

    /**
     * Maps the row content with suitable cell validators as per expected pojo attributes and business rules
     *
     * @param row the current row
     * @return the row validators mapping
     */
    public abstract Map<Cell, List<AbstractDataValidator>> mapValidators(Row row);

}
