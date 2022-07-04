/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.group;

import com.elm.shj.admin.portal.orm.entity.JpaRitualGroup;
import com.elm.shj.admin.portal.orm.repository.RitualGroupRepository;
import com.elm.shj.admin.portal.services.dto.RitualGroupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling ritual group
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
public class RitualGroupService extends GenericService<JpaRitualGroup, RitualGroupDto, Long> {

    /**
     * Checks if a ritual group exists by its code
     *
     * @param groupCode the code of the group to look for
     * @return if the group is found
     */
    public boolean existsByCode(String groupCode) {
        log.info("RitualGroupService ::: Start existsByCode with groupCode: {}", groupCode);
        boolean ritualGroupExists = ((RitualGroupRepository) getRepository()).existsByCode(groupCode);
        log.info("RitualGroupService ::: Finish existsByCode with groupCode: {}", groupCode);
        return ritualGroupExists;
    }
}
