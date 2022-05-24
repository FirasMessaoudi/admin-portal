/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ChatMessageVo;
import com.elm.shj.admin.portal.orm.entity.JpaChatMessage;
import com.elm.shj.admin.portal.orm.entity.UnreadMessagesCount;
import com.elm.shj.admin.portal.orm.repository.ChatMessageRepository;
import com.elm.shj.admin.portal.services.dto.ChatMessageDto;
import com.elm.shj.admin.portal.services.dto.EChatMessageType;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("ChatMessageService ::: Start listChatContactsWithLatestMessage uin: {}", uin);
        List<ChatMessageVo> latestMessageList = chatMessageRepository.findChatContactsWithLatestMessage(uin);
        List<UnreadMessagesCount> unreadMessagesCount = chatMessageRepository.findUnreadMessagesCount(uin);

        unreadMessagesCount.stream().forEach(e-> {
            ChatMessageVo matchedChatMessageVo = latestMessageList.parallelStream().filter(p -> p.getId() == e.getContactId()).findFirst().get();
            matchedChatMessageVo.setUnreadMessagesCount(e.getUnreadMessagesCount());
        });
        log.info("ChatMessageService ::: Finish listChatContactsWithLatestMessage uin: {}", uin);
        return latestMessageList;
    }

    public List<ChatMessageDto> findChatMessagesBySenderIdOrReceiverId(int page, int limit, long contactId, long time) {
        log.info("ChatMessageService ::: Start findChatMessagesBySenderIdOrReceiverId  page: {},  limit: {},  contactId: {},  time: {}", page,  limit,  contactId,  time);
        Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("sentDate").descending());
        Page<JpaChatMessage> chatMessagesPage = chatMessageRepository.findBySenderIdOrReceiverIdAndSentDateLessThanEqual(contactId, contactId, new Date(time), pageableRequest);
        List<JpaChatMessage> chatMessageList = chatMessagesPage.getContent();
        List<ChatMessageDto> chatMessageDtoList = mapList(chatMessageList);
        log.info("ChatMessageService ::: Finish findChatMessagesBySenderIdOrReceiverId  chatMessageDtoListSize: {}",chatMessageDtoList.size());
        return chatMessageDtoList;
    }

    @Transactional
    public ChatMessageDto saveMessage(ChatMessageDto chatMessage) {
        log.info("ChatMessageService ::: Start saveMessage  chatMessageText:{}",chatMessage.getText());
        chatMessage.setTypeCode(EChatMessageType.TEXT.name());
        ChatMessageDto saveMessage = save(chatMessage);
        log.info("ChatMessageService ::: Finish saveMessage  SentDate:{}",chatMessage.getSentDate());
        return saveMessage;
    }

    @Transactional
    public void markMessagesAsRead(long chatContactId) {
        log.info("ChatMessageService ::: Start markMessagesAsRead  chatContactId:{}",chatContactId);
        chatMessageRepository.updateChatMessageReadDate(chatContactId);
        log.info("ChatMessageService ::: Finish markMessagesAsRead ");
    }
}
