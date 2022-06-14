/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.RitualPackageService;
import com.elm.shj.admin.portal.services.data.huic.HuicPlannedPackage;
import com.elm.shj.admin.portal.services.dto.ERitualType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link UniquePackage} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class UniquePackageValidator implements ConstraintValidator<UniquePackage, Object> {

    @Autowired
    private RitualPackageService ritualPackageService;


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(HuicPlannedPackage.class)) {
            return false;
        }
        HuicPlannedPackage plannedPackage = (HuicPlannedPackage) value;
        if (plannedPackage.getPackageRefNumber() == null || plannedPackage.getRitualTypeCode() == null || ERitualType.fromId(plannedPackage.getRitualTypeCode()) == null) {
            return false;
        }
        return !ritualPackageService.existRitualPackageByReferenceNumber(plannedPackage.getPackageRefNumber() + "_" + ERitualType.fromId(plannedPackage.getRitualTypeCode()).name());
    }

}
