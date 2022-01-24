/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ChatMessageVo;
import com.elm.shj.admin.portal.orm.entity.JpaChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository for Chat Message Table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface ChatMessageRepository extends JpaRepository<JpaChatMessage, Long> {

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ChatMessageVo(  " +
            " contact.id, contact.contactUin, message.text, message.sentDate, message.receivedDate,message.readDate, contact.deleted ) " +
            "FROM JpaChatMessage message " +
            "JOIN JpaApplicantChatContact  contact ON contact.id = message.sender.id OR contact.id = message.receiver.id " +
            "WHERE contact.applicantUin= :applicantUin " +
            "AND message.id IN ( SELECT max(messages.id) FROM JpaChatMessage messages " +
            "JOIN JpaApplicantChatContact  contact ON contact.id = messages.sender.id OR contact.id = messages.receiver.id " +
            "GROUP BY contact.id) " +
            "ORDER BY message.sentDate desc ")
    List<ChatMessageVo> findChatContactsWithLatestMessage(@Param("applicantUin") String uin);
    Page<JpaChatMessage> findBySenderIdOrReceiverIdAndSentDateLessThanEqual(long senderId, long receiverId, Date time, Pageable pageable);
}
