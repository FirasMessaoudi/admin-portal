package com.elm.shj.admin.portal.services.dto;


import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaAreaLayerLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link AreaLayerLookupDto} class
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class AreaLayerLookupDtoMapper implements IGenericMapper<AreaLayerLookupDto, JpaAreaLayerLookup> {
}
