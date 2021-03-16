/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import com.elm.shj.admin.portal.orm.test.DtoTest;

/**
 * Testing class for {@link JpaAuthorityLookup}
 *
 * @author Aymen Dhaoui
 * @since 1.0.0
 */
public class JpaAuthorityLookupTest extends DtoTest<JpaAuthorityLookup> {

    @Override
    protected JpaAuthorityLookup getInstance() {
        return new JpaAuthorityLookup();
    }

}
