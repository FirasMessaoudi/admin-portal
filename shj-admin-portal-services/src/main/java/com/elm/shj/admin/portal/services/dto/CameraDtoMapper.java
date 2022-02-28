package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaCamera;
import org.mapstruct.Mapper;


/**
 * Mapper for {@link CameraDto} class
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public  abstract class CameraDtoMapper implements IGenericMapper<CameraDto, JpaCamera> {

}
