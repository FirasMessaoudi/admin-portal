/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaConfig;
import com.elm.shj.admin.portal.services.test.MapperTest;

/**
 * Testing class for {@link ConfigDtoMapper}
 *
 * @author Aymen Dhaoui
 * @since 1.8.0
 */
class ConfigDtoMapperTest extends MapperTest<ConfigDto, JpaConfig, ConfigDtoMapper> {

    @Override
    protected Class<ConfigDtoMapper> getMapperClass() {
        return ConfigDtoMapper.class;
    }

    @Override
    protected ConfigDto getDtoInstance() {
        return new ConfigDto();
    }

    @Override
    protected JpaConfig getEntityInstance() {
        return new JpaConfig();
    }
}

