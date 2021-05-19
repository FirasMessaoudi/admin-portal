/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * Validates whether an applicant exists using data provided in a row
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantExistsValidator {

    private final ApplicantService applicantService;


    public DataValidationResult validate(Row row) {
        DataValidationResult validationResult = DataValidationResult.builder().cell(row.getCell(0)).errorMessages(new ArrayList<>()).build();

        long idNumber = (long) row.getCell(row.getFirstCellNum()).getNumericCellValue();
        String passportNumber = row.getCell(row.getFirstCellNum() + 1).getStringCellValue();
        Date dateOfBirthGregorian = row.getCell(row.getFirstCellNum() + 2).getDateCellValue();
        int dateOfBirthHijri = (int) row.getCell(row.getFirstCellNum() + 3).getNumericCellValue();

    return validationResult;
    }

}
