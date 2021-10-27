/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaReligiousOccasionsDayLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaMonthDayLookup.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface ReligiousOccasionsDayLookupRepository extends JpaRepository<JpaReligiousOccasionsDayLookup, Long> {
}
