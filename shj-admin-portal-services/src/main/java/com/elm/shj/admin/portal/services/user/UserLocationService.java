/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */

package com.elm.shj.admin.portal.services.user;

import com.elm.shj.admin.portal.orm.entity.JpaUserLocation;
import com.elm.shj.admin.portal.orm.repository.UserLocationRepository;
import com.elm.shj.admin.portal.services.dto.EDigitalIdStatus;
import com.elm.shj.admin.portal.services.dto.UserLocationDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserLocationService extends GenericService<JpaUserLocation, UserLocationDto, Long> {

    private final UserLocationRepository userLocationRepository;

    @Transactional
    public boolean storeUserLocation(List<UserLocationDto> locations) {
        List<UserLocationDto> validLocations = new ArrayList<>();
        locations.parallelStream().forEach(location -> {
            if (location.getLongitude() == null || location.getLatitude() == null || location.getAltitude() == null) {
                log.debug("Invalid location Missing one of mandatory params Longitude: {}, Latitude: {}, Altitude: {}", location.getLongitude(), location.getLatitude(), location.getAltitude());
            } else if (location.getLongitude() < -90 || location.getLongitude() > 90) {
                log.debug("Invalid location Longitude {}", location.getLongitude());
            } else if (location.getLatitude() < -90 || location.getLatitude() > 90) {
                log.debug("Invalid location Latitude {}", location.getLatitude());
            } else if (location.getTimestamp() == null || location.getTimestamp().isEmpty()) {
                log.debug("Invalid location Timestamp {}", location.getTimestamp());
            } else {
                try {
                    location.setGpsTime(LocalDateTime.parse(location.getTimestamp().substring(0, location.getTimestamp().length() - 1)));
                    validLocations.add(location);
                    log.debug("Valid location {}", location);
                } catch (DateTimeParseException e) {
                    log.debug("Invalid location Timestamp {}", location.getTimestamp());
                }
            }
        });
        if (validLocations.size() != locations.size())
            return false;
        this.saveAll(validLocations);
        return true;
    }

    public UserLocationDto findTopByUserIdAndUserTypeOrderByCreationDateDesc(String uin, String type){
        return getMapper().fromEntity(userLocationRepository.findTopByUserIdAndUserTypeOrderByCreationDateDesc(uin, type).orElse(null), mappingContext);
    }
}
