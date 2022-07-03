/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaChatBotItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for JpaChatBotItem.
 *
 * @author rameez imtiaz
 * @since 1.0.0
 */
public interface ChatBotItemRepository extends JpaRepository<JpaChatBotItem, Long> {

    List<JpaChatBotItem> findAllByLang(String lang);

}
