package com.elm.shj.admin.portal.services.data.huicIntegration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    //private Object item;
    //@JsonIgnore
    private int rowNumber;
    private List<ErrorItem> errors = new ArrayList<>();
}
