/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the user role domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@NoArgsConstructor
@Getter
@Setter
public class UserRoleDto implements Serializable {

    private static final long serialVersionUID = 4704806887405160826L;

    private long id;
    @JsonBackReference
    private UserDto user;
    private RoleDto role;
    private boolean mainRole;
    private Date creationDate;
}
