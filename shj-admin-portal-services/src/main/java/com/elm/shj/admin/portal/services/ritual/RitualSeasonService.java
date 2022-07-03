/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaRitualSeason;
import com.elm.shj.admin.portal.orm.repository.RitualSeasonRepository;
import com.elm.shj.admin.portal.services.dto.RitualSeasonDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service handling  ritual seasons
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RitualSeasonService extends GenericService<JpaRitualSeason, RitualSeasonDto, Long> {

    private final RitualSeasonRepository ritualSeasonRepository;

    public List<Integer> listRitualSeasonYears() {
        return ritualSeasonRepository.listRitualSeasonYears();
    }

    public boolean existsByBasicInfo(String ritualTypeCode, int seasonYear) {
        return ritualSeasonRepository.existsByRitualTypeCodeAndSeasonYear(ritualTypeCode, seasonYear);
    }

    public RitualSeasonDto findByRitualTypeAndSeason(String ritualType, int seasonYear) {
        Optional<JpaRitualSeason> ritualSeason = ritualSeasonRepository.findByRitualTypeCodeAndSeasonYear(ritualType, seasonYear);
        if (ritualSeason.isPresent()) {
            return getMapper().fromEntity(ritualSeason.get(), mappingContext);
        }
        return null;
    }
}
