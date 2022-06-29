/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.JpaIncidentAttachmentLite;
import com.elm.shj.admin.portal.services.dto.IncidentAttachmentLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service handling Applicant Complaint Attachment operations
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IncidentAttachmentLiteService extends GenericService<JpaIncidentAttachmentLite, IncidentAttachmentLiteDto, Long> {


    public IncidentAttachmentLiteDto save(IncidentAttachmentLiteDto dto){
        return getMapper().fromEntity(getRepository().save(getMapper().toEntity(dto, mappingContext)), mappingContext);
    }
}

