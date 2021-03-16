/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.test.DtoTest;

/**
 * Testing class for {@link AuthorityLookupDto}
 *
 * @author Aymen Dhaoui
 * @since 1.8.0
 */
class AuthorityLookupDtoTest extends DtoTest<AuthorityLookupDto> {

    @Override
    protected AuthorityLookupDto getInstance() {
        return new AuthorityLookupDto();
    }

}
