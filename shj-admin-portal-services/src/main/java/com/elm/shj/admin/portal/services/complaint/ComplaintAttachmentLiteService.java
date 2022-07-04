/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.complaint;

import com.elm.shj.admin.portal.orm.entity.JpaComplaintAttachmentLite;
import com.elm.shj.admin.portal.services.dto.ComplaintAttachmentLiteDto;
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
public class ComplaintAttachmentLiteService extends GenericService<JpaComplaintAttachmentLite, ComplaintAttachmentLiteDto, Long> {


    public ComplaintAttachmentLiteDto save(ComplaintAttachmentLiteDto dto){
        log.info("ComplaintAttachmentLiteService ::: Start save with ComplaintAttachmentLiteDto", dto);
        ComplaintAttachmentLiteDto complaintAttachmentLiteDto = getMapper().fromEntity(getRepository().save(getMapper().toEntity(dto, mappingContext)), mappingContext);
        log.info("ComplaintAttachmentLiteService ::: Finish save");
        return complaintAttachmentLiteDto;
    }
}

