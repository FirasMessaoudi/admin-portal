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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    @Value("${card.generation.max-retries}")
    private int maxRetries;
    @Value("${card.generation.retry.period}")
    private int retryPeriod;


    @SneakyThrows
    @Async
    public void generateBatchCards(BatchCollectionVO batchCollectionVO) {

        // create temporary folder to save the cards before saving them in sftp server
        String tmpdir = Files.createTempDirectory("cards").toFile().getAbsolutePath();
        Path root = Paths.get(tmpdir);

        mainCollectionLoop:
        for (BatchMainCollectionDto batchMainCollectionDto : batchCollectionVO.getBatchMainCollections()) {
            Optional<JpaBatchMainCollection> batchMainCollection = batchMainCollectionRepository.findTopByReferenceNumberOrderByCreationDateDesc(batchMainCollectionDto.getReferenceNumber());
            if (!batchMainCollection.isPresent() || batchMainCollection.get().getStatusCode().equals(ECollectionStatus.FAIL_TO_GENERATE.name())) {
                if (batchMainCollection.isPresent()) {
                    batchMainCollectionDto.setId(batchMainCollection.get().getId());
                }
                batchMainCollectionDto.setStatusCode(ECollectionStatus.GENERATING_CARDS.name());
                BatchMainCollectionDto savedBatchMainCollection = save(batchMainCollectionDto);
                log.info("batch main collection saved successfully");

                String sftpPath = "";
                for (SubCollectionVO subCollectionVO : batchMainCollectionDto.getSubCollections()) {

                    for (String digitalId : subCollectionVO.getDigitalIds()) {
                        BadgeVO badge;
                        if (batchCollectionVO.getTarget().equals(EPrintingRequestTarget.APPLICANT.name())) {
                            badge = badgeService.generateApplicantBadge(digitalId, false);
                        } else {
                            badge = badgeService.generateStaffCard(digitalId);
                        }
                        if (badge != null) {
                            //TODO: surround with try catch , update status and break from outer loop
                            byte[] decodedImage = Base64.getDecoder().decode(badge.getBadgeImage());
                            InputStream targetStream = new ByteArrayInputStream(decodedImage);
                            sftpPath = batchCollectionVO.getBatchReferenceNumber() + "/" + batchMainCollectionDto.getReferenceNumber() + "/" + subCollectionVO.getReferenceNumber();
                            Path p = Files.createDirectories(root.resolve(Paths.get(sftpPath)));
                            Files.copy(targetStream, p.resolve(Paths.get(digitalId + ".jpg")), StandardCopyOption.REPLACE_EXISTING);
                            log.info("file uploaded successfully to: {}", sftpPath);
                        }


                    }

                }

                // create zip file for the main collection
                //TODO: surround with try / catch and update status
                Path zipSource = root.resolve(batchCollectionVO.getBatchReferenceNumber()).resolve(batchMainCollectionDto.getReferenceNumber());
                sftpService.zipFolder(zipSource);
                String zipPath = batchCollectionVO.getBatchReferenceNumber() + "/" + batchMainCollectionDto.getReferenceNumber() + ".zip";
                FileInputStream fis = new FileInputStream(root + "/" + zipPath);

                for (int i = 0; i < maxRetries; i++)
                    try {
                        sftpService.uploadFile(zipPath, fis, CARDS_CONFIG_PROPERTIES);
                        savedBatchMainCollection.setStatusCode(ECollectionStatus.READY.name());
                        savedBatchMainCollection.setUrl(zipPath);
                        save(savedBatchMainCollection);
                        break;
                    } catch (Exception e) {
                        log.debug("failed to connect to sftp server , will retry ");
                        Thread.sleep(retryPeriod);
                        if (i == maxRetries) {
                            log.debug("failed to connect to sftp server after achieving max retries");
                            savedBatchMainCollection.setStatusCode(ECollectionStatus.FAIL_TO_GENERATE.name());
                            save(savedBatchMainCollection);
                        }

                    } finally {
                        fis.close();
                        Files.delete(root.resolve(zipPath));
                    }

            }


        }

        // create the zip file for  the whole batch
        try {

            //TODO: check if files have been already created before zipping them
            Path zipSource = root.resolve(Paths.get(batchCollectionVO.getBatchReferenceNumber()));
            sftpService.zipFolder(zipSource);
            String zipPath = batchCollectionVO.getBatchReferenceNumber() + ".zip";
            FileInputStream fis = new FileInputStream(root + "/" + zipPath);
            sftpService.uploadFile(batchCollectionVO.getBatchReferenceNumber() + "/" + zipPath, fis, CARDS_CONFIG_PROPERTIES);
            fis.close();
            Files.delete(root.resolve(zipPath));
            deleteDirectory(new File(root + "/" + batchCollectionVO.getBatchReferenceNumber()));


        } catch (IOException | JSchException e) {
            deleteDirectory(new File(root.toString()));
            throw new IllegalArgumentException("Unable to find path");
        }


    }

    public List<BatchMainCollectionDto> findBatchStatusByReference(String referenceNumber) {
        return mapList(batchMainCollectionRepository.findByReferenceNumberStartsWith(referenceNumber));
    }

    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }


    public Resource downloadBatchCards(String referenceNumber) throws Exception {
        String path = referenceNumber + "/" + referenceNumber + ".zip";
        return sftpService.downloadCardsZipFile(path);
    }

    public Resource downloadMainCollectionCards(String referenceNumber) throws Exception {
        Optional<JpaBatchMainCollection> batchMainCollectionOptional = batchMainCollectionRepository.findTopByReferenceNumberOrderByCreationDateDesc(referenceNumber);
        return sftpService.downloadCardsZipFile(batchMainCollectionOptional.map(JpaBatchMainCollection::getUrl).get());
    }
}
