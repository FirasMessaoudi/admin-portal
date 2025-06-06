/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestCard;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link PrintRequestCardDto} class
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Mapper(componentModel = "spring")
public abstract class PrintRequestCardDtoMapper implements IGenericMapper<PrintRequestCardDto, JpaPrintRequestCard> {

//    @AfterMapping
//    void afterToDTO(JpaPrintRequestCard jpaPrintRequestCard, @MappingTarget PrintRequestCardDto printRequestCardDto) {
//        CardVO cardVO = new CardVO();
//        cardVO.setId(jpaPrintRequestCard.getCardId());
//        printRequestCardDto.setCard(cardVO);
//    }
//
//    @AfterMapping
//    void afterToEntity(PrintRequestCardDto printRequestCardDto, @MappingTarget JpaPrintRequestCard jpaPrintRequestCard) {
//        jpaPrintRequestCard.setCardId(printRequestCardDto.getCard().getId());
//    }
}
