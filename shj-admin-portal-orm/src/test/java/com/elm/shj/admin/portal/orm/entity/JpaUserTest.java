/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import com.elm.shj.admin.portal.orm.test.DtoTest;

/**
 * Testing class for {@link JpaUser}
 * 
 * @author Aymen Dhaoui
 * @since 1.0.0
 */
public class JpaUserTest extends DtoTest<JpaUser> {

	@Override
	protected JpaUser getInstance() {
		return new JpaUser();
	}

}
