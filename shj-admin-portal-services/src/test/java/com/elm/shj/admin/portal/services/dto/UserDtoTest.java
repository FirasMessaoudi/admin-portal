/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.test.DtoTest;

/**
 * Testing class for {@link UserDto}
 *
 * @author Aymen Dhaoui
 * @since 1.8.0
 */
class UserDtoTest extends DtoTest<UserDto> {

    @Override
    protected UserDto getInstance() {
        return new UserDto();
    }

}
