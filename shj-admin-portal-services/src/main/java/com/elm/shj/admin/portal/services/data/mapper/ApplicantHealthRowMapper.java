/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.mapper;

import com.elm.shj.admin.portal.services.data.validators.AbstractDataValidator;
import com.elm.shj.admin.portal.services.data.validators.GregorianDateDataValidator;
import com.elm.shj.admin.portal.services.data.validators.HijriDateDataValidator;
import com.elm.shj.admin.portal.services.dto.ApplicantHealthDto;
import com.elm.shj.admin.portal.services.dto.EDataSegment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * Mapper that creates an applicant health data object out of the given excel row data
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Component
public class ApplicantHealthRowMapper extends AbstractRowMapper<ApplicantHealthDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicantHealthDto mapRow(Row row) {
        ApplicantHealthDto applicantHealth = new ApplicantHealthDto();
        // applicantHealth.setRowNumber(row.getRowNum());
        long idNumber = (long) row.getCell(row.getFirstCellNum()).getNumericCellValue();
        String passportNumber = row.getCell(row.getFirstCellNum() + 1).getStringCellValue();
        Date dateOfBirthGregorian = row.getCell(row.getFirstCellNum() + 2).getDateCellValue();
        int dateOfBirthHijri = (int) row.getCell(row.getFirstCellNum() + 3).getNumericCellValue();

        applicantHealth.setBloodType(row.getCell(row.getFirstCellNum() + 4).getStringCellValue());
        // applicantHealth.setInsurancePolicyNumber(row.getCell(row.getFirstCellNum() + 5).getStringCellValue());

        applicantHealth.setCreationDate(new Date());
        return applicantHealth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Cell, List<AbstractDataValidator>> mapValidators(Row row) {
        Map<Cell, List<AbstractDataValidator>> validatorMappings = new HashMap<>();

        StreamSupport.stream(Spliterators.spliteratorUnknownSize(row.cellIterator(), Spliterator.ORDERED), false)
                .forEach(cell -> validatorMappings.put(cell, new ArrayList<>()));

        validatorMappings.get(row.getCell(row.getFirstCellNum() + 2)).add(new GregorianDateDataValidator(false, row.getCell(row.getFirstCellNum() + 3)));
        validatorMappings.get(row.getCell(row.getFirstCellNum() + 3)).add(new HijriDateDataValidator(false, row.getCell(row.getFirstCellNum() + 2)));

        return validatorMappings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EDataSegment getDataSegmentId() {
        return EDataSegment.APPLICANT_HEALTH_DATA;
    }
}
