/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import com.elm.shj.admin.portal.orm.test.DtoTest;

/**
 * Testing class for {@link JpaRole}
 * 
 * @author Aymen Dhaoui
 * @since 1.0.0
 */
public class JpaRoleTest extends DtoTest<JpaRole> {

	@Override
	protected JpaRole getInstance() {
		return new JpaRole();
	}

}
