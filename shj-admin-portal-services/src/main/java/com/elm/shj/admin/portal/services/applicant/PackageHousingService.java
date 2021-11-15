/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaPackageHousing;
import com.elm.shj.admin.portal.orm.repository.PackageHousingRepository;
import com.elm.shj.admin.portal.services.dto.PackageHousingDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling package housing
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PackageHousingService extends GenericService<JpaPackageHousing, PackageHousingDto, Long> {

    private final PackageHousingRepository packageHousingRepository;

    public List<PackageHousingDto> findAllCamps() {
        return mapList(packageHousingRepository.findAllCamps());
    }
}
