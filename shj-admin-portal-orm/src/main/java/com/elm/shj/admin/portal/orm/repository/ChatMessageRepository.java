/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ChatMessageVo;
import com.elm.shj.admin.portal.orm.entity.JpaChatMessage;
import com.elm.shj.admin.portal.orm.entity.UnreadMessagesCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
            " contact.id,contact.digitalId, contact.contactDigitalId, " +
            "case when applicant.id is not null then applicant.fullNameAr " +
            "ELSE staff.fullNameAr END , " +
            "case when applicant.id is not null then applicant.fullNameEn " +
            "ELSE staff.fullNameEn END  , " +
            "case when applicant.id is not null then applicantDigitalId.statusCode " +
            "ELSE staffDigitalId.statusCode END  , " +
            "contact.type.id, contact.alias, contact.avatar,contact.systemDefined, contact.staffTitleCode, contact.relationshipCode," +
            "contact.mobileNumber ,contact.countryPhonePrefix, contact.countryCode, contact.autoAdded, contact.deleted, " +
            "message.text, message.sentDate, message.receivedDate,message.readDate,contact.creationDate ) " +
            "FROM JpaChatMessage message " +
            "JOIN JpaChatContact  contact ON contact.id = message.sender.id OR contact.id = message.receiver.id " +
            "LEFT JOIN JpaApplicantDigitalId applicantDigitalId on applicantDigitalId.uin = contact.contactDigitalId " +
            "LEFT JOIN JpaApplicant  applicant on applicant.id = applicantDigitalId.applicantId " +
            "LEFT JOIN JpaCompanyStaffDigitalId staffDigitalId on staffDigitalId.suin = contact.contactDigitalId " +
            "LEFT JOIN staffDigitalId.companyStaff staff " +
            "WHERE contact.digitalId= :digitalId " +
            "AND message.id IN ( SELECT max(messages.id) FROM JpaChatMessage messages " +
            "JOIN JpaChatContact  contact ON contact.id = messages.sender.id OR contact.id = messages.receiver.id " +
            "GROUP BY contact.id) " +
            "ORDER BY message.sentDate desc ")
    List<ChatMessageVo> findChatContactsWithLatestMessage(@Param("digitalId") String uin);


    Page<JpaChatMessage> findBySenderIdOrReceiverIdAndSentDateLessThanEqual(long senderId, long receiverId, Date time, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.UnreadMessagesCount(  " +
            " contact.id, COUNT(message.id) ) " +
            "FROM JpaChatMessage message " +
            "JOIN JpaChatContact  contact ON contact.id = message.receiver.id " +
            "WHERE contact.digitalId= :digitalId " +
            "AND message.readDate IS NULL " +
            "AND message.receivedDate IS NOT NULL " +
            "GROUP BY contact.id ")
    List<UnreadMessagesCount> findUnreadMessagesCount(@Param("digitalId") String uin);

    @Modifying
    @Query("UPDATE JpaChatMessage message SET message.readDate=CURRENT_TIMESTAMP,  message.updateDate = CURRENT_TIMESTAMP WHERE message.receiver.id = :chatContactId AND message.readDate is null ")
    void updateChatMessageReadDate(@Param("chatContactId") long chatContactId);

    List<JpaChatMessage> findBySenderIdOrReceiverId(long senderId, long receiverId);
}
