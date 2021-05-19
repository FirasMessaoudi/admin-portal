/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.mapper;

import com.elm.shj.admin.portal.services.data.validators.AbstractDataValidator;
import com.elm.shj.admin.portal.services.data.validators.GregorianDateDataValidator;
import com.elm.shj.admin.portal.services.data.validators.HijriDateDataValidator;
import com.elm.shj.admin.portal.services.dto.ApplicantContactDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.EDataSegment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * Mapper that creates an applicant data object out of the given excel row data
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Component
public class ApplicantRowMapper extends AbstractRowMapper<ApplicantDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicantDto mapRow(Row row) {
        ApplicantDto applicant = new ApplicantDto();
        // applicant.setRowNumber(row.getRowNum());
        applicant.setIdNumber((long) row.getCell(row.getFirstCellNum()).getNumericCellValue());
        applicant.setPassportNumber(row.getCell(row.getFirstCellNum() + 1).getStringCellValue());
        applicant.setDateOfBirthGregorian(row.getCell(row.getFirstCellNum() + 2).getDateCellValue());
        applicant.setDateOfBirthHijri((int) row.getCell(row.getFirstCellNum() + 3).getNumericCellValue());
        // applicant.setBorderNumber(row.getCell(row.getFirstCellNum() + 4).getStringCellValue());
        applicant.setFullNameEn(row.getCell(row.getFirstCellNum() + 5).getStringCellValue());
        applicant.setFullNameAr(row.getCell(row.getFirstCellNum() + 6).getStringCellValue());
        applicant.setFullNameOrigin(row.getCell(row.getFirstCellNum() + 7).getStringCellValue());
        applicant.setGender(row.getCell(row.getFirstCellNum() + 8).getStringCellValue());
        applicant.setNationalityCode(row.getCell(row.getFirstCellNum() + 9).getStringCellValue());
        applicant.setIdNumberOriginal(row.getCell(row.getFirstCellNum() + 10).getStringCellValue());
        applicant.setPhoto(row.getCell(row.getFirstCellNum() + 11).getStringCellValue());
        // applicant.setBiometricDataFinger(row.getCell(row.getFirstCellNum() + 12).getStringCellValue());
        // applicant.setBiometricDataFace(row.getCell(row.getFirstCellNum() + 13).getStringCellValue());
        applicant.setMaritalStatusCode(row.getCell(row.getFirstCellNum() + 14).getStringCellValue());
        // applicant.setEducationLevel(row.getCell(row.getFirstCellNum() + 15).getStringCellValue());
        applicant.setCreationDate(new Date());


        ApplicantContactDto applicantContact = new ApplicantContactDto();

        applicantContact.setLanguageList(row.getCell(row.getFirstCellNum() + 16).getStringCellValue());
        applicantContact.setEmail(row.getCell(row.getFirstCellNum() + 17).getStringCellValue());
        applicantContact.setLocalMobileNumber((int) row.getCell(row.getFirstCellNum() + 18).getNumericCellValue());
        applicantContact.setIntlMobileNumber((long) row.getCell(row.getFirstCellNum() + 19).getNumericCellValue());
        applicantContact.setCountryCode(row.getCell(row.getFirstCellNum() + 20).getStringCellValue());
        applicantContact.setCityName(row.getCell(row.getFirstCellNum() + 21).getStringCellValue());
        applicantContact.setDistrictName(row.getCell(row.getFirstCellNum() + 22).getStringCellValue());
        applicantContact.setStreetName(row.getCell(row.getFirstCellNum() + 23).getStringCellValue());
        applicantContact.setBuildingNumber((int) row.getCell(row.getFirstCellNum() + 24).getNumericCellValue());
        applicantContact.setPostalCode((int) row.getCell(row.getFirstCellNum() + 25).getNumericCellValue());
        applicantContact.setCreationDate(new Date());

        applicant.setContacts(Collections.singletonList(applicantContact));

        return applicant;
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
        return EDataSegment.APPLICANT_DATA;
    }
}
