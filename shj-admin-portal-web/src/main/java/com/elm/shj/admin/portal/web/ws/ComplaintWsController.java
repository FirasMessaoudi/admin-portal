package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.complaint.ApplicantComplaintLiteService;
import com.elm.shj.admin.portal.services.complaint.ApplicantComplaintService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Controller for exposing web services for external party for applicant complaint.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_COMPLAINTS_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComplaintWsController {

    private final ApplicantComplaintService applicantComplaintService;
    private final ApplicantComplaintLiteService applicantComplaintLiteService;

    @PostMapping("/list/{companyRefCode}/{companyTypeCode}")
    private ResponseEntity<WsResponse<?>> list(@PathVariable Long companyRefCode, @PathVariable String companyTypeCode, @RequestBody ComplaintSearchCriteriaDto criteriaDto, Pageable pageable){

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantComplaintService.findAll(criteriaDto, companyRefCode, companyTypeCode, pageable)).build());
    }

    @GetMapping("/find/{id}")
    private ResponseEntity<WsResponse<?>> findById(@PathVariable Long id){

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantComplaintService.findById(id)).build());
    }

    /**
     * Handles complaint by marking it as resolved or closed and update resolution comment
     *
     * @param complaintId the ID of the complaint to update
     * @return the {@link ResponseEntity} with status
     */
    @PutMapping("/handle/{complaintId}")
    public ResponseEntity<WsResponse<?>> handleComplaint(@PathVariable long complaintId,
                                                  @RequestBody ApplicantComplaintVo applicantComplaintVo) throws NotFoundException {
        log.debug("Handle complaint #{}", complaintId);
        ApplicantComplaintLiteDto complaint = applicantComplaintLiteService.findOne(complaintId);

        if (complaint != null && complaint.getStatusCode() == EComplaintStatus.UNDER_PROCESSING.getCode()) {

            applicantComplaintService.update(complaintId, applicantComplaintVo);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(StringUtils.EMPTY).build());
        } else {
            log.info("Finished Handle complaint #{}, Failure {}", complaintId, "COMPLAINT_NOT_FOUND_NOT_UNDER_PROCESSING");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.COMPLAINT_NOT_FOUND_NOT_UNDER_PROCESSING.getCode()).referenceNumber("COMPLAINT_NOT_FOUND_NOT_UNDER_PROCESSING").build()).build());
        }
    }

    /**
     * Downloads applicant complaint attachment
     *
     * @param attachmentId data request Id
     * @return WsResponse of  the saved complaint attachment
     */
    @GetMapping("/attachments/{attachmentId}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable long attachmentId) throws Exception {
        log.info("Downloading complaint attachment with id# {} ", attachmentId);
        Resource attachment = applicantComplaintService.downloadApplicantComplaintAttachment(attachmentId);
        if (attachment != null) {
            String attachmentName = "img.jpg";
            if (Objects.requireNonNull(attachment.getDescription()).contains("[")) {
                attachmentName = attachment.getDescription().split("\\[")[1].replaceAll("]", "");
            }
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentName + "\"");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentName + "\"")
                    .body(attachment);
        }
        return null;
    }

    /**
     * Handles complaint by marking it as resolved or closed and update resolution comment
     *
     * @body ApplicantComplaintVoCRM of the complaint to update
     * @return the {@link ResponseEntity} with status
     */
    @PutMapping("/handle")
    public ResponseEntity<WsResponse<?>> handleComplaintByCRM(@RequestBody ApplicantComplaintVoCRM applicantComplaintVo) throws NotFoundException {
        log.debug("Handle complaint CrmTicketNumber{}", applicantComplaintVo.getCrmTicketNumber());
        ApplicantComplaintLiteDto complaint = applicantComplaintLiteService.findByCrmTicketNumber(applicantComplaintVo.getCrmTicketNumber());

        if (complaint != null && complaint.getStatusCode() == EComplaintStatus.UNDER_PROCESSING.getCode()) {
            applicantComplaintService.updateByCrm(complaint.getId(), applicantComplaintVo);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(StringUtils.EMPTY).build());
        } else {
            log.info("Finished Handle complaint CrmTicketNumber{}, Failure {}", applicantComplaintVo.getCrmTicketNumber(), "COMPLAINT_NOT_FOUND_NOT_UNDER_PROCESSING");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.COMPLAINT_NOT_FOUND_NOT_UNDER_PROCESSING.getCode()).referenceNumber("COMPLAINT_NOT_FOUND_NOT_UNDER_PROCESSING").build()).build());
        }
    }
}
