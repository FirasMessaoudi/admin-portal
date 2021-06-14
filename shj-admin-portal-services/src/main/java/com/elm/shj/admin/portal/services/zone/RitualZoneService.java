/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.zone;

import com.elm.shj.admin.portal.orm.entity.JpaRitualZone;
import com.elm.shj.admin.portal.orm.repository.RitualZoneRepository;
import com.elm.shj.admin.portal.services.dto.RitualZoneDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling ritual zone
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
public class RitualZoneService extends GenericService<JpaRitualZone, RitualZoneDto, Long> {

    /**
     * Checks if a ritual zone exists by its code
     *
     * @param zoneCode the code of the zone to look for
     * @return if the zone is found
     */
    public boolean existsByCode(String zoneCode) {
        return ((RitualZoneRepository) getRepository()).existsByCode(zoneCode);
    }
}
