/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.mapper;

import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper that creates an applicant data object out of the given excel row data
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Component
public class ApplicantMapper implements IRowMapper<ApplicantDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, String> mapRow(Row row) {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(0, "idNumber");
        mapping.put(1, "passportNumber");
        mapping.put(2, "dateOfBirthGregorian");
        mapping.put(3, "dateOfBirthHijri");
        // mapping.put(4, "borderNumber");
        mapping.put(5, "fullNameEn");
        mapping.put(6, "fullNameAr");
        mapping.put(7, "fullNameOrigin");
        mapping.put(8, "gender");
        mapping.put(9, "nationalityCode");
        mapping.put(10, "idNumberOriginal");
        mapping.put(11, "photo");
        // mapping.put(12, "biometricDataFinger");
        // mapping.put(13, "biometricDataFace");
        mapping.put(14, "maritalStatusCode");
        // mapping.put(15, "educationLevel");

        //mapping.put(16, "contacts.languageList");
        mapping.put(17, "contacts.email");
        mapping.put(18, "contacts.localMobileNumber");
        mapping.put(19, "contacts.intlMobileNumber");
        mapping.put(20, "contacts.countryCode");
        mapping.put(21, "contacts.cityName");
        mapping.put(22, "contacts.districtName");
        mapping.put(23, "contacts.streetName");
        mapping.put(24, "contacts.buildingNumber");
        mapping.put(25, "contacts.postalCode");

        return mapping;
    }
}
