/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Chat Message Table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface ChatMessageRepository extends JpaRepository<JpaChatMessage, Long> {

    @Query(nativeQuery = true,
            value = "select contact.id,contact.contact_uin , " +
            "(select top 1 latest_message.text from shc_portal.shc_chat_message latest_message where  latest_message.sender_id = contact.id or latest_message.receiver_id = contact.id  order by latest_message.sent_date desc,latest_message.id desc) text , " +
            "(select top 1 latest_message.sent_date from shc_portal.shc_chat_message latest_message where  latest_message.sender_id = contact.id or latest_message.receiver_id = contact.id   order by latest_message.sent_date desc,latest_message.id desc) sent_date " +
                    "from shc_portal.shc_applicant_chat_contact contact " +
                    "join shc_portal.shc_chat_message messages on messages.sender_id = contact.id or messages.receiver_id = contact.id " +
                    "where contact.applicant_uin= :applicantUin and contact.deleted=0 " +
                    "group by contact.contact_uin,contact.id " +
                    "order by sent_date desc")
    List<Object[]> findChatContactsWithLatestMessage(@Param("applicantUin") String uin);

    List<JpaChatMessage> findTop20BySenderIdOrReceiverIdOrderBySentDateDescIdDesc(long senderId,long receiverId);
}
