/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import lombok.*;

import java.util.Map;

/**
 * Localized lookup data
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalizedLookupVo {

    private String code;
    // map of lang and label
    private Map<String, String> localizedLabels;
}
