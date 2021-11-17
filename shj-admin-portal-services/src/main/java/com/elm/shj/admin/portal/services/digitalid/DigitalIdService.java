/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantDigitalId;
import com.elm.shj.admin.portal.orm.repository.ApplicantDigitalIdRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantDigitalIdDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.CountryLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.lookup.CountryLookupService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * Service handling Applicant Digital ID operations
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DigitalIdService extends GenericService<JpaApplicantDigitalId, ApplicantDigitalIdDto, Long> {

    private static final DateFormat YEAR_FORMATTER = new SimpleDateFormat("yy");
    private static final Map<String, List<Integer>> GENDER_DIGITS = IntStream.range(1, 10)
            .boxed()
            .collect(groupingBy(
                    i -> i % 2 == 0 ? "F" : "M",
                    mapping(Integer::intValue, toList())
            ));
    private static ThreadLocal<List<String>> threadLocalLatestSerialList = ThreadLocal.withInitial(() -> new ArrayList<>());

    private final ApplicantDigitalIdRepository applicantDigitalIdRepository;
    private final CountryLookupService countryLookupService;

    /**
     * Generates smart id for specific applicant
     * <pre>
     * ==================================================================================================================================
     * "Gender (Random)"        |	Country	   |	    Birth  |		"Serial(for same year and country and random gender)" |	Checksum
     * (odd male | even female)     C	C	C           Y  Y	           S	S	S	S	S	S	S
     *     3                    |   9   6   6  |	   7	5  |	       0	0	0	0	5	2	6	                      | 7
     * ==================================================================================================================================
     * </pre>
     *
     * @param applicant the applicant to generate smart id for
     * @return the generated smart id
     */
    public String generate(ApplicantDto applicant) {
        // check inputs
        Assert.isTrue(Arrays.asList("M", "F").contains(applicant.getGender().toUpperCase()), "Invalid Applicant Gender!");
        Assert.notNull(applicant.getDateOfBirthGregorian(), "Invalid Applicant Date of Birth!");
        Assert.hasText(applicant.getNationalityCode(), "Invalid Applicant Nationality!");
        // generate gender digit
        String genderDigit = String.valueOf(GENDER_DIGITS.get(applicant.getGender().toUpperCase()).get(ThreadLocalRandom.current().nextInt(0, "F".equalsIgnoreCase(applicant.getGender()) ? 4 : 5)));
        // generate country digits
        // retrieve country
        CountryLookupDto countryLookupDto = countryLookupService.findByCode(applicant.getNationalityCode());
        String countryDigits = StringUtils.leftPad(StringUtils.right(countryLookupDto.getCountryPhonePrefix().replaceAll("-", "").replaceAll(",", ""), 3), 3, "0");
        // generate date of birth digits
        Date dobGregorian = applicant.getDateOfBirthGregorian() != null ? applicant.getDateOfBirthGregorian() : DateUtils.toGregorian(applicant.getDateOfBirthHijri());
        String dobDigits = YEAR_FORMATTER.format(dobGregorian);
        // generate serial digits
        String uinPrefix = genderDigit + countryDigits + dobDigits;
        threadLocalLatestSerialList.get().addAll(0, applicantDigitalIdRepository.fetchUinByUinLike(uinPrefix));
        long nextSequence = CollectionUtils.isEmpty(threadLocalLatestSerialList.get()) ? 1 : Long.parseLong(threadLocalLatestSerialList.get().get(0)) + 1;
        String serialDigits = StringUtils.leftPad(String.valueOf(nextSequence), 7, "0");
        // generate checksum digit
        String partialSmartId = uinPrefix + serialDigits;
        String checkDigit = calculateCheckDigit(partialSmartId);
        // add newly generated uinPrefix to the list to avoid duplicate for the current processed file in addition to the database.
        threadLocalLatestSerialList.get().add(0, serialDigits);
        // return smart id
        return partialSmartId + checkDigit;
    }

    /**
     * Validates the given smart id
     *
     * @param smartId the smart id to verify
     * @return result of verification
     */
    public boolean validate(String smartId) {
        if (smartId == null)
            return false;
        char checkDigit = smartId.charAt(smartId.length() - 1);
        String digit = calculateCheckDigit(smartId.substring(0, smartId.length() - 1));
        System.out.println("checksum digit: " + digit);
        return checkDigit == digit.charAt(0);
    }

    /**
     * Calculates the last digits for the given smart id number
     *
     * @param smartId the smart id to generate check digit for
     * @return the check digit
     */
    public static String calculateCheckDigit(String smartId) {
        if (smartId == null)
            return null;
        String digit;
        /* convert to array of int for simplicity */
        int[] digits = new int[smartId.length()];
        for (int i = 0; i < smartId.length(); i++) {
            digits[i] = Character.getNumericValue(smartId.charAt(i));
        }
        /* double every other starting from right - jumping from 2 in 2 */
        for (int i = digits.length - 1; i >= 0; i -= 2) {
            digits[i] += digits[i];
            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }
        int sum = 0;
        for (int j : digits) {
            sum += j;
        }
        /* multiply by 9 step */
        sum = sum * 9;

        /* convert to string to be easier to take the last digit */
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }

    @Transactional
    @Override
    public ApplicantDigitalIdDto save(ApplicantDigitalIdDto dto) {
        return super.save(dto);
    }
}
