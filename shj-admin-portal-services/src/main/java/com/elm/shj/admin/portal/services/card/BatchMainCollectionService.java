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


    @Async
    //TODO(flaifel): split the code into different methods.
    public void generateBatchCards(BatchCollectionVO batchCollectionVO) {
        log.info("Starting card generation process ");
        // create temporary folder to save the cards before saving them in sftp server
        String tmpdir;
        try {
            tmpdir = Files.createTempDirectory("cards").toFile().getAbsolutePath();
        } catch (IOException e) {
            log.debug("failed to create temp folder");
            return;
        }
        Path root = Paths.get(tmpdir);
        boolean mainCollectionCreated = false;
        for (BatchMainCollectionDto batchMainCollectionDto : batchCollectionVO.getBatchMainCollections()) {
            // get main collection by reference number and process only the new or the failed
            Optional<JpaBatchMainCollection> batchMainCollection = batchMainCollectionRepository.findTopByReferenceNumberOrderByCreationDateDesc(batchMainCollectionDto.getReferenceNumber());
            if (!batchMainCollection.isPresent() || batchMainCollection.get().getStatusCode().equals(ECollectionStatus.FAIL_TO_GENERATE.name())) {
                if (batchMainCollection.isPresent()) {
                    batchMainCollectionDto.setId(batchMainCollection.get().getId());
                }
                // start the generation and update the status
                batchMainCollectionDto.setStatusCode(ECollectionStatus.GENERATING_CARDS.name());
                BatchMainCollectionDto savedBatchMainCollection = save(batchMainCollectionDto);
                log.info("batch main collection saved successfully {} ", batchMainCollectionDto.getReferenceNumber());

                String sftpPath;
                subCollectionLoop:
                for (SubCollectionVO subCollectionVO : batchMainCollectionDto.getSubCollections()) {

                    for (String digitalId : subCollectionVO.getDigitalIds()) {
                        try {
                            BadgeVO badge;
                            if (batchCollectionVO.getTarget().equals(EPrintingRequestTarget.APPLICANT.name())) {
                                badge = badgeService.generateApplicantBadge(digitalId, false);
                            } else {
                                badge = badgeService.generateStaffCard(digitalId);
                            }
                            if (badge != null) {
                                try {
                                    // save the card in temp folder
                                    byte[] decodedImage = Base64.getDecoder().decode(badge.getBadgeImage());
                                    InputStream targetStream = new ByteArrayInputStream(decodedImage);
                                    sftpPath = batchCollectionVO.getBatchReferenceNumber() + "/" + batchMainCollectionDto.getReferenceNumber() + "/" + subCollectionVO.getReferenceNumber();
                                    Path p = Files.createDirectories(root.resolve(Paths.get(sftpPath)));
                                    Files.copy(targetStream, p.resolve(Paths.get(digitalId + ".jpg")), StandardCopyOption.REPLACE_EXISTING);
                                    log.info("file uploaded successfully to: {}", sftpPath);
                                } catch (IOException e) {
                                    log.debug("failed to save card in temporary folder");
                                    updateMainCollection(savedBatchMainCollection, ECollectionStatus.FAIL_TO_GENERATE.name());
                                    break subCollectionLoop;

                                }
                            } else {
                                break subCollectionLoop;
                            }
                        } catch (Exception e) {
                            log.debug("failed to generate badge for uin {}", digitalId);
                            updateMainCollection(savedBatchMainCollection, ECollectionStatus.FAIL_TO_GENERATE.name());
                            break subCollectionLoop;
                        }


                    }

                }

                try {
                    // create zip file for the main collection in temp folder
                    Path zipSource = root.resolve(batchCollectionVO.getBatchReferenceNumber()).resolve(batchMainCollectionDto.getReferenceNumber());
                    sftpService.zipFolder(zipSource);
                    String zipPath = batchCollectionVO.getBatchReferenceNumber() + "/" + batchMainCollectionDto.getReferenceNumber() + ".zip";
                    FileInputStream fis = new FileInputStream(root + "/" + zipPath);

                    // start auto retry
                    for (int i = 0; i < maxRetries; i++)
                        try {
                            //upload main collection zip folder in sftp server and mark main collection as ready
                            sftpService.uploadFile(zipPath, fis, CARDS_CONFIG_PROPERTIES);
                            savedBatchMainCollection.setUrl(zipPath);
                            updateMainCollection(savedBatchMainCollection, ECollectionStatus.READY.name());
                            if (!mainCollectionCreated) {
                                mainCollectionCreated = true;
                            }
                            break;
                        } catch (Exception e) {
                            log.debug("failed to connect to sftp server , will retry ");
                            Thread.sleep(retryPeriod);
                            if (i == maxRetries) {
                                log.debug("failed to connect to sftp server after achieving max retries");
                                updateMainCollection(savedBatchMainCollection, ECollectionStatus.FAIL_TO_GENERATE.name());
                            }

                        } finally {
                            fis.close();
                            Files.delete(root.resolve(zipPath));
                        }
                } catch (Exception e) {
                    log.debug("failed to create zip folder for main collection {} ", batchMainCollectionDto.getReferenceNumber());
                    updateMainCollection(savedBatchMainCollection, ECollectionStatus.FAIL_TO_GENERATE.name());
                }


            }


        }

        // create the zip file for  the whole batch
        try {

            Path zipSource = root.resolve(Paths.get(batchCollectionVO.getBatchReferenceNumber()));
            if (mainCollectionCreated) {
                // create zip folder for the whole batch in temp folder
                sftpService.zipFolder(zipSource);
                String zipPath = batchCollectionVO.getBatchReferenceNumber() + ".zip";
                FileInputStream fis = new FileInputStream(root + "/" + zipPath);
                //upload zip folder in sftp server
                sftpService.uploadFile(batchCollectionVO.getBatchReferenceNumber() + "/" + zipPath, fis, CARDS_CONFIG_PROPERTIES);
                fis.close();
                //delete temp folder and sub folders
                Files.delete(root.resolve(zipPath));
                deleteDirectory(new File(root + "/" + batchCollectionVO.getBatchReferenceNumber()));
            }
            log.info("finish card generation process ");


        } catch (IOException | JSchException e) {
            deleteDirectory(new File(root.toString()));
            throw new IllegalArgumentException("Unable to find path");
        }


    }

    public List<BatchMainCollectionDto> findBatchStatusByReference(String BatchReferenceNumber) {
        // main collections in command portal use print request batch number as a prefix with "_" as a separator
        // "_" was concatenated with the batchReferenceNumber when passing the parameters
        return mapList(batchMainCollectionRepository.findByReferenceNumberStartsWith(BatchReferenceNumber + "_"));
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

    private void updateMainCollection(BatchMainCollectionDto batchMainCollectionDto, String status) {
        batchMainCollectionDto.setStatusCode(status);
        save(batchMainCollectionDto);
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
