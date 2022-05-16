/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.card.BatchMainCollectionService;
import com.elm.shj.admin.portal.services.dto.BatchCollectionVO;
import com.elm.shj.admin.portal.services.dto.EManifestType;
import com.elm.shj.admin.portal.services.dto.ManifestVo;
import com.elm.shj.admin.portal.services.prinitng.ManifestService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
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

    private final SftpService sftpService;

    /**
     * generate batch cards
     *
     * @param batchCollection the batch main collection with the batch reference number
     * @return
     */
    @PostMapping("/generate")
    public ResponseEntity<WsResponse<?>> generateBatchCards(@RequestBody BatchCollectionVO batchCollection) {
        log.info("Handler for {} , generating batch cards");
        batchMainCollectionService.generateBatchCards(batchCollection);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                "SUCCESS").build());
    }

    @GetMapping("/collection-status/{reference}")
    public ResponseEntity<WsResponse<?>> trackBatchCollectionStatus(@PathVariable String reference) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                batchMainCollectionService.findBatchStatusByReference(reference + "_")).build());
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
        ByteArrayInputStream manifest = manifestService.generateManifestPDF(printRequestReferenceNumber, batchCollection);
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
    public ResponseEntity<Resource> downloadBatchCards(@PathVariable("referenceNumber")String referenceNumber) throws Exception {

        Resource fileResource = batchMainCollectionService.downloadBatchCards(referenceNumber);
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
    public ResponseEntity<Resource> downloadMainCollectionCards(@PathVariable("referenceNumber")String referenceNumber) throws Exception {

        Resource fileResource = batchMainCollectionService.downloadMainCollectionCards(referenceNumber);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + referenceNumber + ".zip\"")
                .body(fileResource);
    }

}
