package com.elm.shj.admin.portal.services.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffCreateCardDto {
    private String actionCode;
    private long cardId;
}
