/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.chatbot;

import com.elm.shj.admin.portal.orm.entity.JpaChatBotItem;
import com.elm.shj.admin.portal.orm.repository.ChatBotItemRepository;
import com.elm.shj.admin.portal.services.dto.ChatBotItemDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling chatbot item
 *
 * @author rameez imtiaz
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ChatBotItemService extends GenericService<JpaChatBotItem, ChatBotItemDto, Long> {

    private final ChatBotItemRepository chatBotItemRepository;

    /**
     * Find chatbot items all based on the lang.
     *
     * @param lang
     * @return
     */
    public List<ChatBotItemDto> findAllByLang(String lang) {
        log.info("start find all chat boot items with lang");
        List<ChatBotItemDto> chatBotItemDtoList = getMapper().fromEntityList(chatBotItemRepository.findAllByLang(lang), mappingContext);
        log.info("Finish find all chat boot items with lang");
        return chatBotItemDtoList;
    }
}
