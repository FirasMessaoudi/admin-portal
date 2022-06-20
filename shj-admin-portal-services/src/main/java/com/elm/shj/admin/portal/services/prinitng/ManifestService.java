package com.elm.shj.admin.portal.services.prinitng;

import com.elm.shj.admin.portal.orm.entity.ApplicantBasicInfoVo;
import com.elm.shj.admin.portal.services.card.BadgeService;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.services.utils.ImageUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ManifestService {
    private final static int BADGE_WIDTH = 2480;
    private final static int BADGE_HEIGHT = 3508;
    private final static int CELL_HEIGHT = 120;
    private final static int CELL_WIDTH = 615;
    private final ApplicantRitualCardLiteService applicantCardService;
    private final CompanyStaffCardService staffCardService;
    private final PrintRequestService printRequestService;
    private final MessageSource messageSource;
    private static Font shaaerFont;
    private final static String BADGE_RESOURCES_PATH = "badge/";
    private final static String ELM_FONT_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "DINNextLTArabic-Regular-2.ttf";

    static {
        try {
            shaaerFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(ManifestService.class.getClassLoader().getResourceAsStream(ELM_FONT_RESOURCE_FILE_NAME)));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(shaaerFont);
        } catch (IOException | FontFormatException e) {
            log.error("Error while creating Hajj App font.", e);
        }
    }

    public List<ManifestVo> generateManifest(String printRequestReferenceNumber, BatchCollectionVO batchCollection, EManifestType manifestType) {
        if (EManifestType.IMAGE == manifestType)
            return generateManifestAsImages(printRequestReferenceNumber, batchCollection);
        throw new IllegalArgumentException("Manifest Type not supported " + manifestType);

    }

    private List<ManifestVo> generateManifestAsImages(String printRequestReferenceNumber, BatchCollectionVO batchCollectionVO) {
        List<ManifestVo> manifestImages = new ArrayList<>();
        PrintRequestDto printRequestDto = printRequestService.findByReferenceNumber(printRequestReferenceNumber);

        batchCollectionVO.getBatchMainCollections().forEach(batchMainCollectionDto -> batchMainCollectionDto.getSubCollections()
                .forEach(subCollectionVO -> {
                    BufferedImage subCollectionImage = new BufferedImage(BADGE_WIDTH, BADGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D g2d = subCollectionImage.createGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, BADGE_WIDTH, BADGE_HEIGHT);

                    g2d.setColor(new Color(86, 86, 86));
                    Font font = shaaerFont.deriveFont(40f);
                    g2d.setFont(font);
                    drawRow(g2d, 0, Arrays.asList(messageSource.getMessage("printing.request.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())), printRequestReferenceNumber));
                    drawRow(g2d, 1, Arrays.asList(messageSource.getMessage("batch.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())), batchCollectionVO.getBatchReferenceNumber()));
                    drawRow(g2d, 2, Arrays.asList(messageSource.getMessage("collection.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())), batchMainCollectionDto.getReferenceNumber()));
                    drawRow(g2d, 3, Arrays.asList(messageSource.getMessage("sub.collection.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())), subCollectionVO.getReferenceNumber()));
                    List<ApplicantBasicInfoVo> basicInfoVoList = new ArrayList<>();
                    if (printRequestDto.getTarget().equalsIgnoreCase(EPrintingRequestTarget.APPLICANT.name()))
                        basicInfoVoList = applicantCardService.findApplicantsBasicInfoByDigitalIds(subCollectionVO.getDigitalIds());
                    else if (printRequestDto.getTarget().equalsIgnoreCase(EPrintingRequestTarget.STAFF.name()))
                        basicInfoVoList = staffCardService.findStaffBasicInfoByDigitalIds(subCollectionVO.getDigitalIds());
                    if (basicInfoVoList.size() != 0) {
                        font = shaaerFont.deriveFont(40f);
                        g2d.setFont(font);
                        drawRow(g2d, 4, Arrays.asList(messageSource.getMessage("smart.id.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                                messageSource.getMessage("full.name.en", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                                messageSource.getMessage("full.name.ar", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                                messageSource.getMessage("card.serial", null, Locale.forLanguageTag(batchCollectionVO.getLocale()))));
                        for (int i = 0; i < basicInfoVoList.size(); i++) {
                            font = shaaerFont.deriveFont(40f);
                            g2d.setFont(font);
                            drawBody(g2d, basicInfoVoList.get(i), i + 5);
                        }
                    }
                    try {

                        manifestImages.add(ManifestVo.builder()
                                .referenceNumber(subCollectionVO.getReferenceNumber())
                                .image(ImageUtils.imgToBase64String(subCollectionImage))
                                .build());
                    } catch (IOException e) {
                        throw new RuntimeException("Error while converting image to base64");
                    }
                }));
        return manifestImages;
    }


    public ByteArrayInputStream generateManifestPDF(String printRequestReferenceNumber, BatchCollectionVO batchCollectionVO) {
        log.info("Start generate Manifest PDF printRequestReferenceNumber: {}, batchCollection: {}", printRequestReferenceNumber, batchCollectionVO);
        PrintRequestDto printRequestDto = printRequestService.findByReferenceNumber(printRequestReferenceNumber);
        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
        PdfWriter.getInstance(doc, out);
        doc.open();
        batchCollectionVO.getBatchMainCollections().forEach(batchMainCollectionDto -> batchMainCollectionDto.getSubCollections()
                .forEach(subCollectionVO -> {
                    try {

                       PdfPTable table = new PdfPTable(4);
                       com.itextpdf.text.Font shaeerFont = FontFactory.getFont(ELM_FONT_RESOURCE_FILE_NAME, BaseFont.IDENTITY_H, 12, Font.TRUETYPE_FONT);

                       table.setRunDirection(batchCollectionVO.getLocale().equals("en") ? PdfWriter.RUN_DIRECTION_LTR : PdfWriter.RUN_DIRECTION_RTL);
                       PdfPCell referenceNumberHeader = new PdfPCell(new Phrase(messageSource.getMessage("printing.request.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                               shaeerFont));
                       referenceNumberHeader.setColspan(2);
                       referenceNumberHeader.setFixedHeight(20f);
                       referenceNumberHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                       table.addCell(referenceNumberHeader);

                       PdfPCell referenceNumberValue = new PdfPCell(new Phrase(printRequestReferenceNumber));
                       referenceNumberValue.setColspan(2);
                       table.addCell(referenceNumberValue);
                       table.completeRow();

                       PdfPCell batchNumberHeader = new PdfPCell(new Phrase(messageSource.getMessage("batch.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                               shaeerFont));
                       batchNumberHeader.setColspan(2);
                       batchNumberHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                       batchNumberHeader.setFixedHeight(20f);
                       table.addCell(batchNumberHeader);

                       PdfPCell batchNumberValue = new PdfPCell(new Phrase(batchCollectionVO.getBatchReferenceNumber()));
                       batchNumberValue.setColspan(2);
                       table.addCell(batchNumberValue);
                       table.completeRow();

                       PdfPCell mainCollectionHeader = new PdfPCell(new Phrase(messageSource.getMessage("collection.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                               shaeerFont));
                       mainCollectionHeader.setColspan(2);
                       mainCollectionHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                       mainCollectionHeader.setFixedHeight(20f);
                       table.addCell(mainCollectionHeader);

                       PdfPCell mainCollectionValue = new PdfPCell(new Phrase(batchMainCollectionDto.getReferenceNumber()));
                       mainCollectionValue.setColspan(2);
                       table.addCell(mainCollectionValue);
                       table.completeRow();

                       PdfPCell subCollectionHeader = new PdfPCell(new Phrase(messageSource.getMessage("sub.collection.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                               shaeerFont));
                       subCollectionHeader.setColspan(2);
                       subCollectionHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                       subCollectionHeader.setFixedHeight(20f);
                       table.addCell(subCollectionHeader);

                       PdfPCell subCollectionValue = new PdfPCell(new Phrase(subCollectionVO.getReferenceNumber()));
                       subCollectionValue.setColspan(2);
                       table.addCell(subCollectionValue);
                       table.completeRow();

                       List<ApplicantBasicInfoVo> basicInfoVoList = new ArrayList<>();
                        if (printRequestDto.getTarget().equalsIgnoreCase(EPrintingRequestTarget.APPLICANT.name()))
                            basicInfoVoList = applicantCardService.findApplicantsBasicInfoByDigitalIds(subCollectionVO.getDigitalIds());
                        else if (printRequestDto.getTarget().equalsIgnoreCase(EPrintingRequestTarget.STAFF.name()))
                            basicInfoVoList = staffCardService.findStaffBasicInfoByDigitalIds(subCollectionVO.getDigitalIds());
                        if (basicInfoVoList.size() != 0) {
                            PdfPCell digitalIdHeader = new PdfPCell(new Phrase(messageSource.getMessage("smart.id.number", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                                    shaeerFont));
                            digitalIdHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            digitalIdHeader.setFixedHeight(20f);
                            table.addCell(digitalIdHeader);

                            PdfPCell fullNameEnHeader = new PdfPCell(new Phrase(messageSource.getMessage("full.name.en", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                                    shaeerFont));
                            fullNameEnHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            fullNameEnHeader.setFixedHeight(20f);
                            table.addCell(fullNameEnHeader);

                            PdfPCell fullNameArHeader = new PdfPCell(new Phrase(messageSource.getMessage("full.name.ar", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                                    shaeerFont));
                            fullNameArHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            fullNameArHeader.setFixedHeight(20f);
                            table.addCell(fullNameArHeader);

                            PdfPCell cardSerialHeader = new PdfPCell(new Phrase(messageSource.getMessage("card.serial", null, Locale.forLanguageTag(batchCollectionVO.getLocale())),
                                    shaeerFont));
                            cardSerialHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            cardSerialHeader.setFixedHeight(20f);
                            table.addCell(cardSerialHeader);

                            for (ApplicantBasicInfoVo basicInfo: basicInfoVoList) {
                                PdfPCell digitalId = new PdfPCell(new Phrase(basicInfo.getUin()));
                                table.addCell(digitalId);

                                PdfPCell fullNameEn = new PdfPCell(new Phrase(basicInfo.getFullNameEn()));
                                table.addCell(fullNameEn);

                                PdfPCell fullNameAr = new PdfPCell(new Phrase(basicInfo.getFullNameAr(), shaeerFont));
                                fullNameAr.setFixedHeight(20f);
                                table.addCell(fullNameAr);

                                PdfPCell cardSerial = new PdfPCell(new Phrase(basicInfo.getSerialNumber() == null ?  "---" :  basicInfo.getSerialNumber()));
                                table.addCell(cardSerial);
                            }
                        }
                        doc.add(table);
                        doc.newPage();
                    } catch (DocumentException e) {
                        log.error("Error while creating document");
                        doc.close();
                        throw new RuntimeException("Error while creating document");
                    }
                }));
        } catch (DocumentException e) {
            log.error("Error while creating document");
            doc.close();
            throw new RuntimeException("Error while creating document");
        }
        doc.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private void drawBody(Graphics2D g2d, ApplicantBasicInfoVo applicantBasicInfoVo, int index) {
        if (applicantBasicInfoVo.getSerialNumber() == null)
            applicantBasicInfoVo.setSerialNumber("---");
        drawRow(g2d, index, Arrays.asList(applicantBasicInfoVo.getUin(), applicantBasicInfoVo.getFullNameEn(), applicantBasicInfoVo.getFullNameAr(), applicantBasicInfoVo.getSerialNumber()));
    }

    private void drawRow(Graphics2D g2d, int index, List<String> values) {

        for (int i = 0; i < values.size(); i++) {
            drawCell(g2d, CELL_HEIGHT * index + CELL_HEIGHT, i, values.get(i));
        }

    }


    private void drawCell(Graphics2D g2d, int y, int index, String value) {

        g2d.drawRect(CELL_WIDTH * index, y, CELL_WIDTH, CELL_HEIGHT);

        if (value == null)
            value = "---";
        g2d.drawString(value, 10 + (CELL_WIDTH * index), y + (CELL_HEIGHT / 2));

    }

}


