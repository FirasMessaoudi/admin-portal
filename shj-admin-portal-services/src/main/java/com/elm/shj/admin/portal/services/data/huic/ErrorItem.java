package com.elm.shj.admin.portal.services.data.huic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorItem {
    private int rowNumber;
    private String attributeName;
    private String code;
    private String message;
}

