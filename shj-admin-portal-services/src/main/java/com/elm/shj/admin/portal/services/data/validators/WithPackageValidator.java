/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.RitualPackageService;
import com.elm.shj.admin.portal.services.data.huic.HuicApplicantMainData;
import com.elm.shj.admin.portal.services.data.huic.HuicApplicantRitual;
import com.elm.shj.admin.portal.services.dto.ERitualType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithPackage} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithPackageValidator implements ConstraintValidator<WithPackage, Object> {

    @Autowired
    private RitualPackageService ritualPackageService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || (!value.getClass().isAssignableFrom(HuicApplicantMainData.class) && !value.getClass().isAssignableFrom(HuicApplicantRitual.class))) {
            return false;
        }

        if (value.getClass().isAssignableFrom(HuicApplicantMainData.class)) {
            HuicApplicantMainData huicApplicantMainData = (HuicApplicantMainData) value;
            return huicApplicantMainData.getPackageRefNumber() == null || (ERitualType.fromId(huicApplicantMainData.getRitualTypeCode()) != null && ritualPackageService.findByCodeAndRitual(huicApplicantMainData.getPackageRefNumber(), ERitualType.fromId(huicApplicantMainData.getRitualTypeCode()).name(), huicApplicantMainData.getSeasonYear()) != null);
        } else {
            HuicApplicantRitual huicApplicantRitual = (HuicApplicantRitual) value;
            return huicApplicantRitual.getPackageRefNumber() != null && ERitualType.fromId(huicApplicantRitual.getRitualTypeCode()) != null && ritualPackageService.findByCodeAndRitual(huicApplicantRitual.getPackageRefNumber(), ERitualType.fromId(huicApplicantRitual.getRitualTypeCode()).name(), huicApplicantRitual.getSeasonYear()) != null;
        }


    }


}
