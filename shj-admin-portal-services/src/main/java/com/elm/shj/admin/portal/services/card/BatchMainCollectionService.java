/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaBatchMainCollection;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Service handling batch cards generation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BatchMainCollectionService extends GenericService<JpaBatchMainCollection, BatchMainCollectionDto, Long> {

    private final SftpService sftpService;
    private final BadgeService badgeService;
    private static final String CARDS_CONFIG_PROPERTIES = "cardsConfigProperties";


    @Async
    public void generateBatchCards(BatchCollectionVO batchCollectionVO) {
        // String sftpPath = sftpService.generateSftpFilePath(p.getFileName().toString(), referenceNumber, false);

        for (BatchMainCollectionDto batchMainCollectionDto : batchCollectionVO.getBatchMainCollections()) {
            batchMainCollectionDto.setStatusCode(EDataRequestStatus.UNDER_PROCESSING.name());
            BatchMainCollectionDto savedBatchMainCollection = save(batchMainCollectionDto);
            String sftpPath = "";
            try {
                for (SubCollectionVO subCollectionVO : batchMainCollectionDto.getSubCollections()) {
                    for (String digitalId : subCollectionVO.getDigitalIds()) {
                        BadgeVO badge = badgeService.generateApplicantBadge(digitalId);
                        if (badge != null) {
                            InputStream targetStream = new ByteArrayInputStream(badge.getBadgeImage().getBytes());
                            // String sftpPath = sftpService.generateSftpFilePath("card", digitalId, false);
                            sftpPath = batchCollectionVO.getBatchReferenceNumber() + "/" + batchMainCollectionDto.getReferenceNumber() + "/" + subCollectionVO.getReferenceNumber() + "/" + digitalId + ".jpg";
                            sftpService.uploadFile(sftpPath, targetStream, CARDS_CONFIG_PROPERTIES);
                            log.info("file uploaded successfully to: {}", sftpPath);
                        }


                    }

                }
                //sftpService.pack(batchCollectionVO.getBatchReferenceNumber());
                savedBatchMainCollection.setStatusCode(EDataRequestStatus.PROCESSED_SUCCESSFULLY.name());
                save(savedBatchMainCollection);


            } catch (JSchException e) {
                savedBatchMainCollection.setStatusCode(EDataRequestStatus.FAILED.name());
                save(batchMainCollectionDto);
                log.error("Unable to open attached file", e);
                throw new IllegalArgumentException("Unable to open attached file");
            }


        }

    }

}
