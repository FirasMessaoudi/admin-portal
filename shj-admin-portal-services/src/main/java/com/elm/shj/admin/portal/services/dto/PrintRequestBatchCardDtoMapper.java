/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestBatchCard;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper for {@link PrintRequestBatchCardDto} class
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Mapper(componentModel = "spring")
public abstract class PrintRequestBatchCardDtoMapper implements IGenericMapper<PrintRequestBatchCardDto, JpaPrintRequestBatchCard> {
    @AfterMapping
    void afterToDTO(JpaPrintRequestBatchCard jpaPrintRequestBatchCard, @MappingTarget PrintRequestBatchCardDto printRequestBatchCardDto) {
        CardVO cardVO = new CardVO();
        cardVO.setId(jpaPrintRequestBatchCard.getCardId());
        printRequestBatchCardDto.setCard(cardVO);
    }

    @AfterMapping
    void afterToEntity(PrintRequestBatchCardDto printRequestBatchCardDto, @MappingTarget JpaPrintRequestBatchCard jpaPrintRequestBatchCard) {
        jpaPrintRequestBatchCard.setCardId(printRequestBatchCardDto.getCard().getId());
    }
}
