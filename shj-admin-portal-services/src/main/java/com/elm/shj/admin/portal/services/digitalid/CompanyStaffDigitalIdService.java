/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalId;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffDigitalIdRepository;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDigitalIdDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * Service handling company staff digital id
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffDigitalIdService extends GenericService<JpaCompanyStaffDigitalId, CompanyStaffDigitalIdDto, Long> {

    private static final Map<String, List<Integer>> GENDER_DIGITS = IntStream.range(1, 10)
            .boxed()
            .collect(groupingBy(
                    i -> i % 2 == 0 ? "F" : "M",
                    mapping(Integer::intValue, toList())
            ));
    private static ThreadLocal<List<String>> threadLocalLatestSerialList = ThreadLocal.withInitial(() -> new ArrayList<>());

    private final CompanyStaffDigitalIdRepository  companyStaffDigitalIdRepository;



    /**
     * Generates smart id for specific company staff
     *  (1 Gender Random – 4 season hijri year – 6 Serial – check sum )
     * <pre>
     * ==================================================================================================================================
     * "Gender (Random)"         |	    season hijri year  |		"Serial(for same year and random gender)" |	Checksum
     * (odd male | even female)         Y   Y  	 Y    Y                S	S	S	S	S	S
     *     3                    |	   1	4    4    3    |	       0	0	0	0	5	2		          | 7
     * ==================================================================================================================================
     * </pre>
     *
     * @param staff the company staff   to generate smart id for
     * @return the generated smart id
     */
    public String generate(CompanyStaffDto staff) {
        // check inputs
        Assert.notNull(staff.getGender(), "Invalid Staff  Gender!");
        Assert.isTrue(Arrays.asList("M", "F").contains(staff.getGender().toUpperCase()), "Invalid Staff Gender!");
        Assert.notNull(staff.getSeason(), "Invalid Staff  Season!");
        // generate gender digit
        String genderDigit = String.valueOf(GENDER_DIGITS.get(staff.getGender().toUpperCase()).get(ThreadLocalRandom.current().nextInt(0, "F".equalsIgnoreCase(staff.getGender()) ? 4 : 5)));
        String suinPrefix = genderDigit +staff.getSeason() ;
       threadLocalLatestSerialList.get().addAll(0, companyStaffDigitalIdRepository.fetchSuinBySuinLike(suinPrefix));
        long nextSequence = CollectionUtils.isEmpty(threadLocalLatestSerialList.get()) ? 1 : Long.parseLong(threadLocalLatestSerialList.get().get(0)) + 1;
        String serialDigits = StringUtils.leftPad(String.valueOf(nextSequence), 5, "0");
        // generate checksum digit
        String partialSmartId = suinPrefix + serialDigits;
        String checkDigit = calculateCheckDigit(partialSmartId);
        // add newly generated suinPrefix to the list to avoid duplicate for the current processed file in addition to the database.
//        threadLocalLatestSerialList.get().add(0, serialDigits);
        // return staff smart id
        return partialSmartId + checkDigit;

    }



    /**
     * Calculates the last digits for the given smart id number
     *
     * @param suin the staff smart id to generate check digit for
     * @return the check digit
     */
    public static String calculateCheckDigit(String suin) {
        if (suin == null)
            return null;
        String digit;
        /* convert to array of int for simplicity */
        int[] digits = new int[suin.length()];
        for (int i = 0; i < suin.length(); i++) {
            digits[i] = Character.getNumericValue(suin.charAt(i));
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
    public CompanyStaffDigitalIdDto save(CompanyStaffDigitalIdDto dto) {
        return super.save(dto);
    }

    public Optional<JpaCompanyStaffDigitalId> findByBasicInfo(long staffId, int season) {
        return companyStaffDigitalIdRepository.findByBasicInfo(staffId,season);
    }

    /**
     * Find all staff without digital IDs
     *
     * @return the list of companyStaff
     */
    public List<CompanyStaffDigitalIdDto> findAllWithoutDigitalId() {
        return mapList(companyStaffDigitalIdRepository.findBySuinIsNull());
    }
}
