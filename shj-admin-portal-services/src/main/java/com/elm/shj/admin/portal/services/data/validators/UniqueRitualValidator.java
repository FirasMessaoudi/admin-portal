/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.data.huic.HuicRitualSeason;
import com.elm.shj.admin.portal.services.dto.ERitualType;
import com.elm.shj.admin.portal.services.ritual.RitualSeasonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link UniqueRitual} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class UniqueRitualValidator implements ConstraintValidator<UniqueRitual, Object> {

    @Autowired
    private RitualSeasonService ritualSeasonService;


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(HuicRitualSeason.class)) {
            return false;
        }
        HuicRitualSeason ritualSeason = (HuicRitualSeason) value;
        if (ritualSeason.getRitualTypeCode() == null || ERitualType.fromId(ritualSeason.getRitualTypeCode()) == null) {
            return false;
        }
        return !ritualSeasonService.existsByBasicInfo(ERitualType.fromId(ritualSeason.getRitualTypeCode()).name(), ritualSeason.getSeasonYear());
    }

}
