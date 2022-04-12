package com.elm.shj.admin.portal.services.dto;


import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaSupplicationLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link SupplicationLookupDto } class
 *
 * @author Nihed Sidhom
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class SupplicationLookupDtoMapper implements IGenericMapper<SupplicationLookupDto, JpaSupplicationLookup> {
}
