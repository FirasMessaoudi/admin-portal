/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Update Group MVC Command
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
public class UpdateGroupCmd implements Serializable {
    private static final long serialVersionUID = -8533899205211869167L;

    @NotNull
    private String uin;
    @NotNull
    private String oldGroup;
    @NotNull
    private String newGroup;
}
