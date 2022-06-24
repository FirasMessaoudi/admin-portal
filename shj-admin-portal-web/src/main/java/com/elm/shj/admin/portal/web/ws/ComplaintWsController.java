package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.complaint.ApplicantComplaintLiteService;
import com.elm.shj.admin.portal.services.complaint.ApplicantComplaintService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.ComplaintTypeLookupService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
    private final ComplaintTypeLookupService complaintTypeLookupService;

    private static final int MIN_GEO_CORDINATES = -90;
    private static final int MAX_GEO_CORDINATES = 90;

    /**
     * Creates a new applicant complaint
     *
     * @param applicantComplaintRequest applicant complaint details
     * @return WsResponse of the persisted applicant complaint
     */
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WsResponse<?>> create(@RequestPart("complaint") @Valid ApplicantComplaintLiteDto applicantComplaintRequest,
                                                @RequestPart(value = "attachment", required = false) MultipartFile complaintAttachment) {

        log.info("Start create Complaint ApplicantComplaintLiteDto ReferenceNumber: {}", applicantComplaintRequest.getReferenceNumber());
        if (complaintAttachment != null) {
            log.debug("create Complaint without attachment");
            //validate file type, allow only images and video
            if (!complaintAttachment.getOriginalFilename().equals("") && !applicantComplaintLiteService.validateFileExtension(complaintAttachment.getOriginalFilename())) {
                log.info("Finish create Complaint {}, {} ","FAILURE", WsError.EWsError.INVALID_FILE_EXTENSION.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.INVALID_FILE_EXTENSION.getCode()).build()).build());
            }
            //validate file size, max size is allowed 15MB
            if (!complaintAttachment.getOriginalFilename().equals("") && !applicantComplaintLiteService.validateFileSize(complaintAttachment.getSize() / (1024 * 1024))) {
                log.info("Finish create Complaint {}, {} ","FAILURE", WsError.EWsError.ExCEED_MAX_SIZE.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.ExCEED_MAX_SIZE.getCode()).build()).build());
            }
        }
        if (applicantComplaintRequest.getLocationLat() != null && applicantComplaintRequest.getLocationLng() != null) {
            log.debug("create Complaint applicantComplaintRequest Location not null");
            // validate latitude cordinates, it should be between -90 and +90
            if (applicantComplaintRequest.getLocationLat().intValue() < MIN_GEO_CORDINATES || applicantComplaintRequest.getLocationLat().intValue() > MAX_GEO_CORDINATES) {
                log.info("Finish create Complaint {}, {} ","FAILURE", WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode()).build()).build());
            }
            // validate longitude cordinates, it should be between -90 and +90
            if (applicantComplaintRequest.getLocationLng().intValue() < MIN_GEO_CORDINATES || applicantComplaintRequest.getLocationLng().intValue() > MAX_GEO_CORDINATES) {
                log.info("Finish create Complaint {}, {} ","FAILURE", WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode()).build()).build());
            }
        }

        // validate camp number, it should be provided if city is holy sites
        if (applicantComplaintRequest.getCity().equals(ECity.HOLY_SITES.name()) && (applicantComplaintRequest.getCampNumber() == null || applicantComplaintRequest.getCampNumber().isEmpty())) {
            log.info("Finish create Complaint {}, {} ","FAILURE", WsError.EWsError.COMPLAINT_CAMP_NUMBER_NOT_PROVIDED.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.COMPLAINT_CAMP_NUMBER_NOT_PROVIDED.getCode()).build()).build());
        }

        ComplaintTypeLookupDto complaintTypeLookupDto = complaintTypeLookupService.findByCode(applicantComplaintRequest.getTypeCode());
        if (complaintTypeLookupDto == null) {
            log.info("Finish create Complaint {}, {} ","FAILURE", WsError.EWsError.COMPLAINT_TYPE_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.COMPLAINT_TYPE_NOT_FOUND.getCode()).build()).build());
        }
        ApplicantComplaintLiteDto applicantComplaintLiteDto = applicantComplaintLiteService.addApplicantComplaint(applicantComplaintRequest, complaintAttachment);
        log.info("Finish create Complaint {}, ReferenceNumber: {} ","SUCCESS",applicantComplaintLiteDto.getReferenceNumber());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantComplaintLiteDto).build());

    }
    
    @PostMapping("/list/{companyRefCode}/{companyTypeCode}")
    private ResponseEntity<WsResponse<?>> list(@PathVariable Long companyRefCode, @PathVariable String companyTypeCode, @RequestBody ComplaintSearchCriteriaDto criteriaDto, Pageable pageable){

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantComplaintService.findAll(criteriaDto, companyRefCode, companyTypeCode, pageable)).build());
    }

    @GetMapping("/find/{id}")
    private ResponseEntity<WsResponse<?>> findById(@PathVariable Long id){

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantComplaintService.findByIdLite(id)).build());
    }

    /**
     * Handles complaint by marking it as resolved or closed and update resolution comment
     *
     * @param complaintId the ID of the complaint to update
     * @return the {@link ResponseEntity} with status
     */
    @PostMapping("/handle/{complaintId}")
    public ResponseEntity<WsResponse<?>> handleComplaint(@PathVariable long complaintId,
                                                  @RequestBody ApplicantComplaintVo applicantComplaintVo) throws NotFoundException {
        log.debug("Handle complaint #{}", complaintId);
        ApplicantComplaintLiteDto complaint = applicantComplaintLiteService.findOne(complaintId);

        if (complaint != null && complaint.getStatusCode().equals(EComplaintStatus.UNDER_PROCESSING.name())) {

            applicantComplaintService.update(complaint, applicantComplaintVo);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(StringUtils.EMPTY).build());
        } else {
            log.info("Finished Handle complaint #{}, Failure {}", complaintId, "COMPLAINT_NOT_FOUND_OR_NOT_UNDER_PROCESSING");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.COMPLAINT_NOT_FOUND_OR_NOT_UNDER_PROCESSING.getCode()).referenceNumber("COMPLAINT_NOT_FOUND_OR_NOT_UNDER_PROCESSING").build()).build());
        }
    }

    /**
     * Downloads applicant complaint attachment
     *
     * @param attachmentId data request Id
     * @return WsResponse of  the saved complaint attachment
     */
    @GetMapping("/attachments/{attachmentId}")
    public ResponseEntity<?> downloadAttachment(@PathVariable long attachmentId) throws Exception {
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
        } else {
            log.info("Finished Downloading complaint attachment #{}, Failure {}", attachmentId, "COMPLAINT_ATTACHMENT_NOT_FOUND");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.COMPLAINT_ATTACHMENT_NOT_FOUND.getCode()).referenceNumber("COMPLAINT_ATTACHMENT_NOT_FOUND").build()).build());
        }
    }

    /**
     * Handles complaint by marking it as resolved or closed and update resolution comment
     *
     * @body ApplicantComplaintVoCRM of the complaint to update
     * @return the {@link ResponseEntity} with status
     */
    @PostMapping("/handle")
    public ResponseEntity<WsResponse<?>> handleComplaintByCRM(@RequestBody ApplicantComplaintVoCRM applicantComplaintVo) throws NotFoundException {
        log.debug("Handle complaint CrmTicketNumber{}", applicantComplaintVo.getCrmTicketNumber());
        ApplicantComplaintLiteDto complaint = applicantComplaintLiteService.findByCrmTicketNumber(applicantComplaintVo.getCrmTicketNumber());

        if (complaint != null && complaint.getStatusCode().equals(EComplaintStatus.UNDER_PROCESSING.name())) {
            applicantComplaintService.updateByCrm(complaint.getId(), applicantComplaintVo);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(StringUtils.EMPTY).build());
        } else {
            log.info("Finished Handle complaint CrmTicketNumber {}, Failure {}", applicantComplaintVo.getCrmTicketNumber(), "COMPLAINT_NOT_FOUND_OR_NOT_UNDER_PROCESSING");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.COMPLAINT_NOT_FOUND_OR_NOT_UNDER_PROCESSING.getCode()).referenceNumber("COMPLAINT_NOT_FOUND_OR_NOT_UNDER_PROCESSING").build()).build());
        }
    }
}
