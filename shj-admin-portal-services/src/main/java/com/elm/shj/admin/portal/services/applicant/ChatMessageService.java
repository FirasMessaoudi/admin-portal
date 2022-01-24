/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ChatMessageVo;
import com.elm.shj.admin.portal.orm.entity.JpaChatMessage;
import com.elm.shj.admin.portal.orm.repository.ChatMessageRepository;
import com.elm.shj.admin.portal.services.dto.ChatMessageDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service handling chat messages
 *
 * @author salzoubi
 * @since 1.1.0
 */

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ChatMessageService extends GenericService<JpaChatMessage, ChatMessageDto, Long> {

    private final ChatMessageRepository chatMessageRepository;



    public List<ChatMessageVo> listChatContactsWithLatestMessage(String uin) {
        return chatMessageRepository.findChatContactsWithLatestMessage(uin);



    }

    public List<ChatMessageDto> findChatMessagesBySenderIdOrReceiverId(int page, int limit, long contactId, long time) {
        Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("sentDate").descending());
        Page<JpaChatMessage> chatMessagesPage = chatMessageRepository.findBySenderIdOrReceiverIdAndSentDateLessThanEqual(contactId, contactId, new Date(time), pageableRequest);
        List<JpaChatMessage> chatMessageList = chatMessagesPage.getContent();
        List<ChatMessageDto> chatMessageDtoList = mapList(chatMessageList);
        return chatMessageDtoList;
    }

    public ChatMessageDto saveMessage(ChatMessageDto chatMessage) {
        return save(chatMessage);
    }
}
