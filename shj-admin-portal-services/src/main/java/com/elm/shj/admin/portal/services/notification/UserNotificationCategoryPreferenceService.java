/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaUserNotificationCategoryPreference;
import com.elm.shj.admin.portal.orm.repository.UserNotificationCategoryPreferenceRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.UserNotificationCategoryPreferenceDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service handling user notification category preference
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserNotificationCategoryPreferenceService extends GenericService<JpaUserNotificationCategoryPreference, UserNotificationCategoryPreferenceDto, Long> {

    private final UserNotificationCategoryPreferenceRepository notificationCategoryPreferenceRepository;

    /**
     * Find user notification category preference.
     *
     * @param userId
     * @return
     */
    public List<UserNotificationCategoryPreferenceDto> findUserNotificationCategoryPreference(long userId) {
        return mapList(notificationCategoryPreferenceRepository.findByUserId(userId));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public UserNotificationCategoryPreferenceDto save(UserNotificationCategoryPreferenceDto userNotificationCategoryPreference) {
        // persist the record
        return super.save(userNotificationCategoryPreference);
    }
}
