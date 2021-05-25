/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.mapper;

import org.apache.poi.ss.usermodel.Row;

import java.util.Map;

/**
 * Row Mapper Abstraction to map bean fields to cell index in a row
 *
 * @param <T> the type to map the row to
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface IRowMapper<T> {

    /**
     * Maps the cell index to the fields in the type subject of mapping
     *
     * @param row the row in the excel file
     * @return a mapping of [cell_index, field] in the type subject of mapping
     */
    Map<Integer, String> mapRow(Row row);
}
