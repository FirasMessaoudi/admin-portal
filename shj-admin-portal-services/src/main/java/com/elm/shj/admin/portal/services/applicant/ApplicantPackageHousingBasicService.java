/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousingBasic;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageHousingBasicRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageHousingRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service handling applicant package housing
 *
 * @author rameez imtiaz
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantPackageHousingBasicService extends GenericService<JpaApplicantPackageHousingBasic, ApplicantPackageHousingBasicDto, Long> {

    private final ApplicantPackageHousingBasicRepository applicantPackageHousingBasicRepository;

}
