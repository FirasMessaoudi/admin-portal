/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.card.BatchMainCollectionService;
import com.elm.shj.admin.portal.services.dto.BatchCollectionVO;
import com.elm.shj.admin.portal.services.dto.BatchMainCollectionDto;
import com.elm.shj.admin.portal.services.prinitng.ManifestService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;


/**
 * Controller for exposing batch main collection web services for external party.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_BATCH)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatchMainCollectionWsController {


    private final BatchMainCollectionService batchMainCollectionService;
    private final ManifestService manifestService;


    /**
     * generate batch cards
     *
     * @param batchCollection the batch main collection with the batch reference number
     */
    @PostMapping("/generate")
    public ResponseEntity<WsResponse<?>> generateBatchCards(@RequestBody BatchCollectionVO batchCollection) {
        log.info("Start generating batch cards {}", batchCollection);
        batchMainCollectionService.generateBatchCards(batchCollection);
        log.info("Finish generating batch cards with response {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                "SUCCESS").build());
    }

    @GetMapping("/collection-status/{batchReferenceNumber}")
    // this endpoint responsible for checking the main collections statuses for the business operation portal
    public ResponseEntity<WsResponse<?>> trackBatchCollectionStatus(@PathVariable String batchReferenceNumber) {
        log.info("Start track Batch Collection Status {}", batchReferenceNumber);
        List<BatchMainCollectionDto> batchStatusByReference = batchMainCollectionService.findBatchStatusByReference(batchReferenceNumber);
        log.info("Finish track Batch Collection Status with response {}", batchStatusByReference);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                batchStatusByReference).build());
    }

    /**
     * generate manifest file as images for
     * batch cards that belong to batch or main collection or sub collection
     *
     * @param batchCollection the batch main collection with the batch reference number
     * @return image or list of images (each sub collection in one image)
     */
    @PostMapping("/manifest/generate/{printRequestReferenceNumber}")
    public ResponseEntity<Resource> generateManifestFileAsImages(@PathVariable("printRequestReferenceNumber") String printRequestReferenceNumber, @RequestBody BatchCollectionVO batchCollection) {
        log.info("Start Generate manifest file as images {},{}", printRequestReferenceNumber, batchCollection);
        ByteArrayInputStream manifest = manifestService.generateManifestPDF(printRequestReferenceNumber, batchCollection);
        log.info("Finish Generate manifest file as images with response {}", new InputStreamResource(manifest));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=subCollection.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(manifest));
    }

    /*
     * download batch cards
     *
     * @param batchCollection the batch main collection with the batch reference number
     * @return
     */
    @GetMapping(value = "/download/{referenceNumber}", produces = "application/zip")
    public ResponseEntity<Resource> downloadBatchCards(@PathVariable("referenceNumber") String referenceNumber) throws Exception {
        log.info("Start download batch cards {}", referenceNumber);
        Resource fileResource = batchMainCollectionService.downloadBatchCards(referenceNumber);
        log.info("Finish download batch cards with response {}", fileResource);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + referenceNumber + ".zip\"")
                .body(fileResource);
    }

    /*
     * download main collections cards
     *
     * @param batchCollection the batch main collection with the batch reference number
     * @return
     */
    @GetMapping(value = "/download/main-collection/{referenceNumber}", produces = "application/zip")
    public ResponseEntity<Resource> downloadMainCollectionCards(@PathVariable("referenceNumber") String referenceNumber) throws Exception {
        log.info("Start download main collection cards {}", referenceNumber);
        Resource fileResource = batchMainCollectionService.downloadMainCollectionCards(referenceNumber);
        log.info("Finish download main collection cards with response {}", fileResource);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + referenceNumber + ".zip\"")
                .body(fileResource);
    }

}
