/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Google Maps utility functions
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
public class MapUtils {

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    /**
     * Loads dynamically Google Maps Api Key
     *
     * @return the Api Key
     */
    public String retrieveGoogleMapsApiKey() {
        return this.googleMapsApiKey;
    }
}
