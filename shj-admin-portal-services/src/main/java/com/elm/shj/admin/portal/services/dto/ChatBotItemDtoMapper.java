/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaChatBotItem;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ChatBotItemDto} class
 *
 * @author rameez imtiaz
 * @since 1.0.0
 */
@Mapper(componentModel = "spring")
public abstract class ChatBotItemDtoMapper implements IGenericMapper<ChatBotItemDto, JpaChatBotItem>, HibernateAwareMapper {
}
