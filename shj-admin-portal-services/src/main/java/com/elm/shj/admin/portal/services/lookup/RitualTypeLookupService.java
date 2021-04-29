/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaRitualTypeLookup;
import com.elm.shj.admin.portal.services.dto.RitualTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service handling ritual type lookup
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
public class RitualTypeLookupService extends GenericService<JpaRitualTypeLookup, RitualTypeLookupDto, Long> {

    /**
     * Find all ritual types with localized data.
     * @return list of localized ritual types.
     */
    public List<LocalizedLookupVo> findAllLocalized() {
        List<LocalizedLookupVo> localizedRitualTypes = new ArrayList<>();
        List<RitualTypeLookupDto> ritualTypes = mapList(getRepository().findAll());
        ritualTypes.stream().collect(Collectors.groupingBy(RitualTypeLookupDto::getCode)).forEach((s, ritualTypeList) -> {
            localizedRitualTypes.add(LocalizedLookupVo.builder().code(s).localizedLabels(ritualTypeList.stream().collect(Collectors.toMap(RitualTypeLookupDto::getLang, RitualTypeLookupDto::getLabel))).build());
        });
        return localizedRitualTypes;
    }
}
