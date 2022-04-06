/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaBatchMainCollection;
import com.elm.shj.admin.portal.orm.repository.BatchMainCollectionRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
    private final BatchMainCollectionRepository batchMainCollectionRepository;
    private final Path root = Paths.get("uploads");


    @Async
    public void generateBatchCards(BatchCollectionVO batchCollectionVO) {
        try {
            // create a temporary folder
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
        if (batchCollectionVO.getTarget().equals(EPrintingRequestTarget.APPLICANT.name())) {
            for (BatchMainCollectionDto batchMainCollectionDto : batchCollectionVO.getBatchMainCollections()) {
                Optional<JpaBatchMainCollection> batchMainCollection = batchMainCollectionRepository.findTopByReferenceNumberOrderByCreationDateDesc(batchMainCollectionDto.getReferenceNumber());
                if (!batchMainCollection.isPresent() || batchMainCollection.get().getStatusCode().equals(ECollectionStatus.FAIL_TO_GENERATE.name())) {
                    if (batchMainCollection.isPresent()) {
                        batchMainCollectionDto.setId(batchMainCollection.get().getId());
                    }
                    batchMainCollectionDto.setStatusCode(ECollectionStatus.GENERATING_CARDS.name());
                    BatchMainCollectionDto savedBatchMainCollection = save(batchMainCollectionDto);
                    String sftpPath = "";
                    try {
                        for (SubCollectionVO subCollectionVO : batchMainCollectionDto.getSubCollections()) {
                            for (String digitalId : subCollectionVO.getDigitalIds()) {
                                BadgeVO badge = badgeService.generateApplicantBadge(digitalId);
                                if (badge != null) {
                                    byte[] decodedImage = Base64.getDecoder().decode(badge.getBadgeImage());
                                    InputStream targetStream = new ByteArrayInputStream(decodedImage);
                                    // String sftpPath = sftpService.generateSftpFilePath("card", digitalId, false);
                                    sftpPath = batchCollectionVO.getBatchReferenceNumber() + "/" + batchMainCollectionDto.getReferenceNumber() + "/" + subCollectionVO.getReferenceNumber();
                                    Path p = Files.createDirectories(root.resolve(Paths.get(sftpPath)));
                                    Files.copy(targetStream, p.resolve(Paths.get(digitalId + ".jpg")));
                                    //  sftpService.uploadFile(sftpPath, targetStream, CARDS_CONFIG_PROPERTIES);
                                    log.info("file uploaded successfully to: {}", sftpPath);
                                }


                            }

                        }

                        // create zip file for the main collection
                        Path zipSource = root.resolve(batchCollectionVO.getBatchReferenceNumber()).resolve(batchMainCollectionDto.getReferenceNumber());
                        sftpService.zipFolder(zipSource);
                        String zipPath = batchCollectionVO.getBatchReferenceNumber() + "/" + batchMainCollectionDto.getReferenceNumber() + ".zip";
                        FileInputStream fis = new FileInputStream(root + "/" + zipPath);
                        sftpService.uploadFile(zipPath, fis, CARDS_CONFIG_PROPERTIES);
                        fis.close();
                        Files.delete(root.resolve(zipPath));
                        savedBatchMainCollection.setStatusCode(ECollectionStatus.READY.name());
                        savedBatchMainCollection.setUrl(zipPath);
                        save(savedBatchMainCollection);


                    } catch (Exception e) {
                        savedBatchMainCollection.setStatusCode(ECollectionStatus.FAIL_TO_GENERATE.name());
                        save(savedBatchMainCollection);
                        log.error("Unable to open attached file", e);
                        throw new IllegalArgumentException("Unable to open attached file");
                    }
                }


            }

            // create the zip file for  the whole batch
            try {
                Path zipSource = root.resolve(Paths.get(batchCollectionVO.getBatchReferenceNumber()));
                sftpService.zipFolder(zipSource);
                String zipPath = batchCollectionVO.getBatchReferenceNumber() + ".zip";
                FileInputStream fis = new FileInputStream(root + "/" + zipPath);
                sftpService.uploadFile(batchCollectionVO.getBatchReferenceNumber() + "/" + zipPath, fis, CARDS_CONFIG_PROPERTIES);
                fis.close();
                Files.delete(root.resolve(zipPath));
                deleteDirectory(new File(root + "/" + batchCollectionVO.getBatchReferenceNumber()));


            } catch (IOException | JSchException e) {
                e.printStackTrace();
            }
        }

    }

    public List<BatchMainCollectionDto> findBatchStatusByReference(String referenceNumber) {
        return mapList(batchMainCollectionRepository.findBatchStatusByReference(referenceNumber));
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }


}
