package com.elm.shj.admin.portal.services.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class ManifestVo {

    private String printRequestNumber;
    private List<String> images;
}
