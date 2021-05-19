/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.mapper;

import com.elm.shj.admin.portal.services.data.validators.AbstractDataValidator;
import com.elm.shj.admin.portal.services.data.validators.GregorianDateDataValidator;
import com.elm.shj.admin.portal.services.data.validators.HijriDateDataValidator;
import com.elm.shj.admin.portal.services.dto.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * Mapper that creates an applicant immunization data object out of the given excel row data
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Component
public class ApplicantHealthImmunizationRowMapper extends AbstractRowMapper<ApplicantHealthImmunizationDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicantHealthImmunizationDto mapRow(Row row) {
        ApplicantHealthImmunizationDto applicantHealthImmunization = new ApplicantHealthImmunizationDto();
        // applicantHealthImmunization.setRowNumber(row.getRowNum());
        long idNumber = (long) row.getCell(row.getFirstCellNum()).getNumericCellValue();
        String passportNumber = row.getCell(row.getFirstCellNum() + 1).getStringCellValue();
        Date dateOfBirthGregorian = row.getCell(row.getFirstCellNum() + 2).getDateCellValue();
        int dateOfBirthHijri = (int) row.getCell(row.getFirstCellNum() + 3).getNumericCellValue();

        applicantHealthImmunization.setImmunizationCode(row.getCell(row.getFirstCellNum() + 4).getStringCellValue());
        applicantHealthImmunization.setImmunizationDate(row.getCell(row.getFirstCellNum() + 5).getDateCellValue());
        applicantHealthImmunization.setMandatory(row.getCell(row.getFirstCellNum() + 6).getBooleanCellValue());

        applicantHealthImmunization.setCreationDate(new Date());
        return applicantHealthImmunization;
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
        validatorMappings.get(row.getCell(row.getFirstCellNum() + 5)).add(new GregorianDateDataValidator(false, null));

        return validatorMappings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EDataSegment getDataSegmentId() {
        return EDataSegment.APPLICANT_IMMUNIZATION_DATA;
    }
}
