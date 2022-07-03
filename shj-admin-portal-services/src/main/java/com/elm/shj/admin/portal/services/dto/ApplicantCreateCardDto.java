package com.elm.shj.admin.portal.services.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantCreateCardDto {
    private String actionCode;
    private long cardId;
    private long ritualId;
}
