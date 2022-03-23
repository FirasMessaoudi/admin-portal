/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Badge content value object.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
public class BadgeContentVO {

    private String fullNameAr;
    private String fullNameEn;
    private String photo;
    private String uin;
    private String nationalityAr;
    private String nationalityEn;
    private String ritualType;
    private String ritualSeasonYear;
    private String unit;
    private String group;
    private String camp;
    private String seat;
    private String bus;
    private String groupLeaderName;
    private String groupLeaderMobile;
}
