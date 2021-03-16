/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import com.elm.shj.admin.portal.orm.test.DtoTest;

/**
 * Testing class for {@link JpaConfig}
 *
 * @author Aymen Dhaoui
 * @since 1.0.0
 */
public class JpaConfigTest extends DtoTest<JpaConfig> {

    @Override
    protected JpaConfig getInstance() {
        return new JpaConfig();
    }

}
