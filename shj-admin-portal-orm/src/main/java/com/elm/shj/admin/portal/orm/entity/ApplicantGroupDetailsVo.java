/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Dto class for the applicant group Details domain.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicantGroupDetailsVo implements Serializable {

    // data: '{"id":456,"groupNameAr":"المجموعة الثانية","groupNameEn":"Second Group","leaderNameAr":"عبدالعزيز القحطاني","leaderNameEn":"Abdulaziz Alqahtani","leaderIdentityNo":"1151141189","leaderPassportNo":"115663","busId":131313,"busInfo":"باص الحج الثاني","campId":464646,"campInfo":"مخيم الحج االثاني - عرفة"}'
    private static final long serialVersionUID = 7617423887048517970L;

    private long id;

    private long localOfficeId;

    private String groupName;
    private String leaderNameAr;
    private String leaderNameEn;
    private String leaderIdentityNo;
    private String leaderPassportNo;

    private String busInfo;
    private String campInfo;

}
