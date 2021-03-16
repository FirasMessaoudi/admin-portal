/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.test.DtoTest;

/**
 * Testing class for {@link UserPasswordHistoryDto}
 *
 * @author Aymen Dhaoui
 * @since 1.8.0
 */
class UserPasswordHistoryDtoTest extends DtoTest<UserPasswordHistoryDto> {

    @Override
    protected UserPasswordHistoryDto getInstance() {
        return new UserPasswordHistoryDto();
    }

}
