package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaCompanySeasonPackage;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link CompanySeasonPackageDto} class
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class CompanySeasonPackageMapper implements IGenericMapper<CompanySeasonPackageDto, JpaCompanySeasonPackage> {
}
