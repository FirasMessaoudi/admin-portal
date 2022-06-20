/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * The persistent class for the shc_company_ritual_step_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class GroupRitualStepVo implements Serializable {

    private static final long serialVersionUID = -913161328642286375L;

    private long id;
    private long groupId;
    private String code;
    private long stepIndex;
    private String editMode;
    private Date stepDateTime;
    private LocalTime stepTime;
    private long stepHijriDate;

    public GroupRitualStepVo(long id, long groupId, String code, long stepIndex, String editMode, Date stepDateTime) {
        this.id = id;
        this.groupId = groupId;
        this.code = code;
        this.stepIndex = stepIndex;
        this.editMode = editMode;
        this.stepDateTime = stepDateTime;
    }


}
