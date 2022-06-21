/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.CompanyStaffVO;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffTitleLookupRepository;
import com.elm.shj.admin.portal.orm.repository.RitualTypeLookupRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.services.dto.BadgeVO;
import com.elm.shj.admin.portal.services.dto.NationalityLookupDto;
import com.elm.shj.admin.portal.services.lookup.NationalityLookupService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.services.utils.ImageUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.PDF417Writer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * Service handling applicant and staff badge generation
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BadgeService {

    private final static int BADGE_WIDTH = (int) Math.round(5.71 * 95);
    private final static int BADGE_HEIGHT = (int) Math.round(8.52 * 96);
    private final static int ICON_WIDTH = (int) Math.round(0.42 * 96);
    private final static int ICON_HEIGHT = (int) Math.round(0.48 * 96);
    private final static int PHOTO_MAX_HEIGHT = (int) Math.round(1.85 * 96);
    private final static int QR_CODE_MAX_HEIGHT = (int) Math.round(1.9 * 68);
    private final static int QR_CODE_BACK_MAX_HEIGHT = (int) Math.round(1.73 * 68);

    private final static int MOHU_LOGO_MAX_HEIGHT = (int) Math.round(1.15 * 96);
    private final static int TABLE_HEADER_HEIGHT = 64;
    private final static int TABLE_VERTICAL_OFFSET = 22;
    private final static int MOBILE_BADGE_HEIGHT = (int) Math.round(12.12 * 96);

    private final static String BADGE_RESOURCES_PATH = "badge/";
    private final static String ELM_FONT_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "DINNextLTArabic-Regular-2.ttf";
    private final static String TOP_BG_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "top-bg.png";

    private final static String TOP_BG_NEW_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "top_BG.png";
    private final static String HAJJ_APP_LOGO_FILE_NAME = BADGE_RESOURCES_PATH + "logo.png";

    private final static String BOTTOM_BG_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "bottom-bg.png";
    private final static String MOHU_LOGO_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "mohu-logo.png";
    private final static String TENT_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "tent.jpg";
    private final static String BUS_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "bus.jpg";
    private final static String LEADER_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "leader.jpg";
    private final static int STAFF_BADGE_HEIGHT = (int) Math.round(11.12 * 96);
    private final static String STAFF_TOP_BG_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "staff-top-bg.png";
    private final static String STAFF_LEFT_LOGO_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "staff-left-logo.png";
    private final static String STAFF_RIGHT_LOGO_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "staff-right-logo.png";
    private final static String STAFF_CARD_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "staff-card-status.png";
    private final static String STAFF_FOOTER_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "staff-footer.png";

    private static Font shaaerFont;

    private final ApplicantRitualCardLiteService applicantCardService;
    private final ApplicantPackageService applicantPackageService;
    private final NationalityLookupService nationalityLookupService;
    private final RitualTypeLookupRepository ritualTypeLookupRepository;
    private final CompanyStaffService companyStaffService;
    private final CompanyStaffTitleLookupRepository companyStaffTitleLookupRepository;

    static {
        try {
            shaaerFont = Font.createFont(Font.PLAIN, Objects.requireNonNull(new ClassPathResource(ELM_FONT_RESOURCE_FILE_NAME).getInputStream()));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(shaaerFont);
        } catch (IOException | FontFormatException e) {
            log.error("Error while creating Shaaer font.", e);
        }
    }

    public BadgeVO generateStaffCard(String suin) {
        Optional<CompanyStaffVO> staffRitual = companyStaffService.findStaffRitualBySuin(suin);
        if (staffRitual.isEmpty()) {
            log.error("Cannot generate badge, no details for the staff with {} suin", suin);
            return null;
        }
        CompanyStaffVO staffData = staffRitual.get();
        BufferedImage badgeImage = new BufferedImage(BADGE_WIDTH, STAFF_BADGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = badgeImage.createGraphics();

        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, 0, BADGE_WIDTH, STAFF_BADGE_HEIGHT);

        g2d.setColor(new Color(86, 86, 86));

        ImageUtils.applyQualityRenderingHints(g2d);
        addStaffHeaderBg(g2d);
        addPilgrimImage(g2d, staffData.getPhoto(), false);
        addStaffRightLogo(g2d);
        String jobTitle = companyStaffTitleLookupRepository.findLabelByCodeAndLanguage(staffData.getJobTitleCode(), "ar");
        addStaffNameAndJob(g2d, staffData.getFullNameAr(), jobTitle);
        String ritualType = ritualTypeLookupRepository.findLabelByCodeAndLanguage(staffData.getRitualTypeCode(), "ar");
        addStaffRitual(g2d, ritualType, staffData.getRitualSeason() + "");
        addStaffCamp(g2d, "");
        addCardStatus(g2d);
        addStaffFooterBg(g2d);
        String decodedBarCode = getBarCodeForStaffItemsAsString(staffData, String.valueOf(staffData.getCardId()));
        addBarCode(g2d, decodedBarCode, false);
        addQrCodeRectangle(g2d, staffData.getSuin(), String.valueOf(staffData.getCardId()), ritualType, staffData.getRitualSeason());
        String imgStr = null;
        try {
            imgStr = ImageUtils.imgToBase64String(badgeImage);
        } catch (IOException e) {
            log.error("Error while converting image to base64 string for {} digital id.", suin, e);
        }
        return BadgeVO.builder().badgeImage(imgStr).build();

    }

    public BadgeVO generateBackBadge(String uin) {
        BufferedImage badgeImage = new BufferedImage(BADGE_WIDTH, BADGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = badgeImage.createGraphics();

        g2d.setColor(new Color(0xFFF7F7F7));
        g2d.fillRect(0, 0, BADGE_WIDTH, BADGE_HEIGHT);

        g2d.setColor(new Color(86, 86, 86));

        ImageUtils.applyQualityRenderingHints(g2d);
        addBackBadgeBg(g2d);
        addBackBadgeFooter(g2d);
        addHeaderQrCode(g2d, uin);
        String imgStr = null;
        try {
            imgStr = ImageUtils.imgToBase64String(badgeImage);
        } catch (IOException e) {
            log.error("Error while converting image to base64 string for {} digital id.", uin, e);
        }
        return BadgeVO.builder().badgeImage(imgStr).build();
    }


    private void addBackBadgeBg(Graphics2D g2d) {
        BufferedImage topBackground = ImageUtils.loadFromClasspath(BADGE_RESOURCES_PATH + "Back-BG.png");
        if (topBackground != null) {
            g2d.drawImage(ImageUtils.resizeImage(topBackground, BADGE_WIDTH, BADGE_HEIGHT), 0, 0, null);
        }
    }

    private void addBackBadgeFooter(Graphics2D g2d) {
        BufferedImage bottomBackground = ImageUtils.loadFromClasspath(BADGE_RESOURCES_PATH + "Group 338.png");
        if (bottomBackground != null) {
            Image img = ImageUtils.resizeImage(bottomBackground, BADGE_WIDTH, BADGE_HEIGHT);
            g2d.drawImage(ImageUtils.resizeImage(bottomBackground, BADGE_WIDTH / 2 - 50, BADGE_HEIGHT), BADGE_WIDTH / 2 - 260, BADGE_HEIGHT - img.getHeight(null) + 250, null);
        }

    }

    public BadgeVO generateApplicantBadge(String uin, boolean withQr) {
        // get the last applicant package
        Long applicantPackageId = applicantPackageService.findLatestIdByApplicantUIN(uin);
        ApplicantRitualCardLiteDto applicantRitualCardLite = applicantCardService.findCardDetailsByUinAndPackageId(uin, applicantPackageId).orElse(null);
        if (applicantRitualCardLite == null) {
            log.error("Cannot generate badge, no ritual details for the applicant with {} uin", uin);
            return null;
        }
        String ritualType = ritualTypeLookupRepository.findLabelByCodeAndLanguage(applicantRitualCardLite.getRitualType(), "ar");
        // get the nationality
        List<NationalityLookupDto> mainLangCountryLookupList = nationalityLookupService.findAllByCode(applicantRitualCardLite.getNationalityCode());
        NationalityLookupDto arabicCountryLookup = mainLangCountryLookupList.stream().filter(countryLookup -> countryLookup.getLang().equals("ar")).findFirst().orElse(null);
        String nationalityAr = arabicCountryLookup == null ? "" : arabicCountryLookup.getLabel();
        NationalityLookupDto englishCountryLookup = mainLangCountryLookupList.stream().filter(countryLookup -> countryLookup.getLang().equals("en")).findFirst().orElse(null);
        String nationalityEn = englishCountryLookup == null ? "" : englishCountryLookup.getLabel();
        BufferedImage badgeImage = new BufferedImage(BADGE_WIDTH, withQr ? MOBILE_BADGE_HEIGHT : BADGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = badgeImage.createGraphics();

        g2d.setColor(new Color(0xFFF7F7F7));
        g2d.fillRect(0, 0, BADGE_WIDTH, BADGE_HEIGHT);

        g2d.setColor(new Color(86, 86, 86));

        ImageUtils.applyQualityRenderingHints(g2d);

        addHeaderBg(g2d, withQr, applicantRitualCardLite.getEstablishmentId());
        //addFooterBg(g2d, withQr);
        //addZone(g2d);
        g2d.setColor(new Color(86, 86, 86));
        addPilgrimImage(g2d, applicantRitualCardLite.getPhoto(), true);

        addNameAndNationality(g2d, applicantRitualCardLite.getFullNameAr(), applicantRitualCardLite.getFullNameEn(), nationalityAr, nationalityEn);

        addRitual(g2d, ritualType, applicantRitualCardLite.getHijriSeason() + "");

        //addCampRectangle(g2d, applicantRitualCardLite.getUnitCode(), applicantRitualCardLite.getGroupCode(), applicantRitualCardLite.getCampCode());
        //addBusRectangle(g2d, applicantRitualCardLite.getBusNumber(), applicantRitualCardLite.getSeatNumber());
        addCompanyRectangle(g2d, applicantRitualCardLite, uin);
        //String decodedBarCode = getBarCodeFoApplicantItemsAsString(uin, applicantRitualCardLite);
        //addBarCode(g2d, decodedBarCode, true);

        String imgStr = null;
        try {
            imgStr = ImageUtils.imgToBase64String(badgeImage);
        } catch (IOException e) {
            log.error("Error while converting image to base64 string for {} digital id.", uin, e);
        }
        return BadgeVO.builder().badgeImage(imgStr).build();
    }


    private void addZone(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Font font = shaaerFont.deriveFont(32f);

        g2d.setColor(Color.WHITE);
        FontMetrics fm = g2d.getFontMetrics(font);

        int xDif = ((BADGE_WIDTH - fm.stringWidth("النطاق")) - 450);
        int yDif = 45;

        TextLayout layout = new TextLayout("النطاق", font, frc);
        layout.draw(g2d, xDif, yDif);
        yDif += 44;
        xDif += 5;
        layout = new TextLayout("Zone", font, frc);
        layout.draw(g2d, xDif, yDif);
    }

    private void addStaffHeaderBg(Graphics2D g2d) {
        BufferedImage topBackground = ImageUtils.loadFromClasspath(STAFF_TOP_BG_RESOURCE_FILE_NAME);
        if (topBackground != null) {
            g2d.drawImage(ImageUtils.resizeImage(topBackground, BADGE_WIDTH, STAFF_BADGE_HEIGHT), 0, 0, null);
        }
    }

    private void addHeaderBg(Graphics2D g2d, boolean withQr, Integer establishmentCode) {


        BufferedImage topBackground = ImageUtils.loadFromClasspath(BADGE_RESOURCES_PATH + establishmentCode + "_top_BG.png");
        if (topBackground != null) {
            g2d.drawImage(ImageUtils.resizeImage(topBackground, BADGE_WIDTH, withQr ? MOBILE_BADGE_HEIGHT : BADGE_HEIGHT), 0, 0, null);
        }
    }

    private void addStaffFooterBg(Graphics2D g2d) {
        BufferedImage bottomBackground = ImageUtils.loadFromClasspath(STAFF_FOOTER_RESOURCE_FILE_NAME);
        if (bottomBackground != null) {
            Image img = ImageUtils.resizeImage(bottomBackground, BADGE_WIDTH, BADGE_HEIGHT);
            g2d.drawImage(ImageUtils.resizeImage(bottomBackground, BADGE_WIDTH, BADGE_HEIGHT), 0, BADGE_HEIGHT - img.getHeight(null), null);
        }
    }

    private void addFooterBg(Graphics2D g2d, boolean withQr) {
        BufferedImage bottomBackground = ImageUtils.loadFromClasspath(BOTTOM_BG_RESOURCE_FILE_NAME);
        if (bottomBackground != null) {
            Image img = ImageUtils.resizeImage(bottomBackground, BADGE_WIDTH, withQr ? MOBILE_BADGE_HEIGHT : BADGE_HEIGHT);
            g2d.drawImage(ImageUtils.resizeImage(bottomBackground, BADGE_WIDTH, BADGE_HEIGHT), 0, BADGE_HEIGHT - img.getHeight(null), null);
        }
    }

    private void addCardStatus(Graphics2D g2d) {
        BufferedImage cardBackground = ImageUtils.loadFromClasspath(STAFF_CARD_RESOURCE_FILE_NAME);
        if (cardBackground != null) {
            Image img = ImageUtils.resizeImage(cardBackground, BADGE_WIDTH, 60);
            g2d.drawImage(ImageUtils.resizeImage(cardBackground, BADGE_WIDTH, 60), (int) (BADGE_WIDTH / 3.7), BADGE_HEIGHT - img.getHeight(null) - 90, null);
        }
    }

    private void addStaffRightLogo(Graphics2D g2d) {
        BufferedImage ministryImage = ImageUtils.loadFromClasspath(MOHU_LOGO_RESOURCE_FILE_NAME);
        if (ministryImage == null) return;

        // add the logo image
        Image img = ImageUtils.resizeImage(ministryImage, MOHU_LOGO_MAX_HEIGHT, MOHU_LOGO_MAX_HEIGHT);
        g2d.drawImage(img, BADGE_WIDTH - img.getWidth(null) - 32, (int) Math.round(1.5 * 96), null);

        // draw a line underneath it

        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int xDif = BADGE_WIDTH - img.getWidth(null) - 32;
        int yDif = (int) Math.round(1.5 * 96) + img.getHeight(null) + 8;
        g2d.drawLine(xDif, yDif, xDif + img.getWidth(null), yDif);
        BufferedImage amin = ImageUtils.loadFromClasspath(STAFF_RIGHT_LOGO_RESOURCE_FILE_NAME);
        if (amin == null) return;

        // add the right logo image
        Image rightLogo = ImageUtils.resizeImage(amin, MOHU_LOGO_MAX_HEIGHT, MOHU_LOGO_MAX_HEIGHT);
        g2d.drawImage(rightLogo, BADGE_WIDTH - rightLogo.getWidth(null) - 45, (int) Math.round(2.90 * 96), null);

    }

    private void addStaffRitual(Graphics2D g2d, String ritualType, String ritualYear) {
        BufferedImage letfLogoImage = ImageUtils.loadFromClasspath(STAFF_LEFT_LOGO_RESOURCE_FILE_NAME);
        if (letfLogoImage == null) return;

        // add the logo image
        Image img = ImageUtils.resizeImage(letfLogoImage, MOHU_LOGO_MAX_HEIGHT, MOHU_LOGO_MAX_HEIGHT);
        g2d.drawImage(img, BADGE_WIDTH - 520, (int) Math.round(1.5 * 96), null);

        // draw a line underneath it

        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int xDif = BADGE_WIDTH - 510;
        int yDif = (int) Math.round(1.5 * 96) + img.getHeight(null) + 8;
        g2d.drawLine(xDif, yDif, xDif + img.getWidth(null) - 10, yDif);

        FontRenderContext frc = g2d.getFontRenderContext();

        Font font = shaaerFont.deriveFont(22f);
        yDif += 10;
        xDif -= 50;
        TextLayout layout = new TextLayout(ritualType, font, frc);
        yDif += 22;
        layout.draw(g2d, xDif + img.getWidth(null) / 2 - 10, yDif);
        font = shaaerFont.deriveFont(26f);
        xDif += 30;
        layout = new TextLayout(ritualYear, font, frc);
        yDif += 32;
        layout.draw(g2d, xDif + img.getWidth(null) / 2 - 22, yDif);
    }

    private void addRitual(Graphics2D g2d, String ritualType, String ritualYear) {
        BufferedImage ministryImage = ImageUtils.loadFromClasspath(MOHU_LOGO_RESOURCE_FILE_NAME);
        if (ministryImage == null) return;

        // add the logo image
        Image img = ImageUtils.resizeImage(ministryImage, MOHU_LOGO_MAX_HEIGHT, MOHU_LOGO_MAX_HEIGHT);
        g2d.drawImage(img, BADGE_WIDTH - img.getWidth(null) - 32, (int) Math.round(2.2 * 96), null);

        BufferedImage shaaerImage = ImageUtils.loadFromClasspath(HAJJ_APP_LOGO_FILE_NAME);
        if (shaaerImage == null) return;

        // add the logo image
        Image image = ImageUtils.resizeImage(shaaerImage, MOHU_LOGO_MAX_HEIGHT, MOHU_LOGO_MAX_HEIGHT);
        g2d.drawImage(image, BADGE_WIDTH - image.getWidth(null) - 450, (int) Math.round(2.2 * 96), null);


        // draw a line underneath it

        //g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        //int xDif = BADGE_WIDTH - img.getWidth(null) - 32;
        //int yDif = (int) Math.round(1.5 * 96) + img.getHeight(null) + 8;
        //g2d.drawLine(xDif, yDif, xDif + img.getWidth(null), yDif);

        //FontRenderContext frc = g2d.getFontRenderContext();

        /*Font font = shaaerFont.deriveFont(22f);
        xDif -= 30;
        yDif += 10;
        TextLayout layout = new TextLayout(ritualType, font, frc);
        yDif += 22;
        layout.draw(g2d, xDif + img.getWidth(null) / 2 - 10, yDif);
        font = shaaerFont.deriveFont(26f);
        xDif += 30;
        layout = new TextLayout(ritualYear, font, frc);
        yDif += 32;
        layout.draw(g2d, xDif + img.getWidth(null) / 2 - 22, yDif);*/
    }

    private void addStaffNameAndJob(Graphics2D g2d, String fullNameAr, String jobTitle) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Font font = shaaerFont.deriveFont(43f);

        FontMetrics fm = g2d.getFontMetrics(font);

        int xDif = ((BADGE_WIDTH - fm.stringWidth(fullNameAr)) / 2);
        int yDif = PHOTO_MAX_HEIGHT + fm.getAscent() + (int) Math.round(2 * 96);

        TextLayout layout = new TextLayout(fullNameAr, font, frc);
        layout.draw(g2d, xDif, yDif);
        // draw a line underneath it
        yDif += 22;
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine((int) (BADGE_WIDTH * 0.125), yDif, (int) (BADGE_WIDTH * 0.875), yDif);
        xDif = ((BADGE_WIDTH - fm.stringWidth(jobTitle)) / 2);
        yDif += 45;
        layout = new TextLayout(jobTitle, font, frc);
        layout.draw(g2d, xDif, yDif);
    }
    private void addNameAndNationality(Graphics2D g2d, String fullNameAr, String fullNameEn, String nationalityAr, String nationalityEn) {
        fullNameEn=  fullNameEn == null ? "---": fullNameEn;
        fullNameAr=  fullNameAr == null ? "---": fullNameAr;
        nationalityAr=  nationalityAr == null ? "---": nationalityAr;
        nationalityEn=  nationalityEn == null ? "---": nationalityEn;

        FontRenderContext frc = g2d.getFontRenderContext();

        Font font = shaaerFont.deriveFont(26f);

        FontMetrics fm = g2d.getFontMetrics(font);

        int xDif = ((BADGE_WIDTH - fm.stringWidth(fullNameAr + "")) / 2);
        int yDif = PHOTO_MAX_HEIGHT + fm.getAscent() + (int) Math.round(1.25 * 96) - 8;
        g2d.setColor(Color.black);
        TextLayout layout = new TextLayout(fullNameAr + "", font, frc);
        layout.draw(g2d, xDif, yDif);

        g2d.setColor(new Color(86, 86, 86));
        xDif = ((BADGE_WIDTH - fm.stringWidth(fullNameEn + "")) / 2);
        yDif += 44;
        layout = new TextLayout(fullNameEn + "", font, frc);
        layout.draw(g2d, xDif, yDif);

        // draw a line underneath it
        yDif += 18;
        g2d.setColor(new Color(0xFFD3D3D3));
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine((int) (BADGE_WIDTH * 0.125), yDif, (int) (BADGE_WIDTH * 0.875), yDif);
        g2d.setColor(new Color(86, 86, 86));
        font = shaaerFont.deriveFont(22f);
        fm = g2d.getFontMetrics(font);

        yDif += 30;
        // xDif += 95;
        xDif = ((BADGE_WIDTH - fm.stringWidth(nationalityAr + " | " + nationalityEn)) / 2);

        layout = new TextLayout(nationalityAr + " | " + nationalityEn, font, frc);
        layout.draw(g2d, xDif, yDif);

        //  xDif = ((BADGE_WIDTH - fm.stringWidth(nationalityEn + "")) / 2);
        // yDif += 36;
        // layout = new TextLayout(nationalityEn, font, frc);
        //layout.draw(g2d, xDif, yDif);
    }

    private void addHeaderQrCode(Graphics2D g2d, String uin) {
        BufferedImage qrCode = makeRoundedCorner(generateQRcode(uin, "5", true), 30);
        if (qrCode != null) {
            Image img = ImageUtils.resizeImage(qrCode, QR_CODE_BACK_MAX_HEIGHT, QR_CODE_BACK_MAX_HEIGHT);
            int yDif = (int) Math.round(1.4 * 100);
            g2d.drawImage(img, (BADGE_WIDTH - img.getWidth(null)) / 2, yDif, null);
        }
    }

    private void addPilgrimImage(Graphics2D g2d, String base64Photo, boolean isApplicant) {
        BufferedImage pilgrimImage = ImageUtils.loadImageInCircle(base64Photo);
        if (pilgrimImage != null) {
            Image img = ImageUtils.resizeImage(pilgrimImage, PHOTO_MAX_HEIGHT, PHOTO_MAX_HEIGHT);
            int yDif = isApplicant ? (int) Math.round(0.2 * 100) : Math.round(2 * 93);
            g2d.drawImage(img, (BADGE_WIDTH - img.getWidth(null)) / 2, yDif, null);
        }
    }

    public void addBarCode(Graphics2D g2d, String decodedBarCode, boolean isApplicant) {
        PDF417Writer barcodeWriter = new PDF417Writer();
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix;
        try {
            bitMatrix = barcodeWriter.encode(decodedBarCode, BarcodeFormat.PDF_417, BADGE_WIDTH * 2 / 3, (int) Math.round(0.72 * 96), hintMap);
            BufferedImage barCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            int yDiff = isApplicant ? BADGE_HEIGHT - barCodeImage.getHeight() - 34 : BADGE_HEIGHT - barCodeImage.getHeight() - 54;
            g2d.drawImage(barCodeImage, (BADGE_WIDTH - barCodeImage.getWidth()) / 2, yDiff, null);
        }
        catch (WriterException e) {
            log.error("Error while writing the bar code", e);
        }
    }

    private void addCampRectangle(Graphics2D g2d, String unit, String group, String camp) {
        int rectHeight = (int) Math.round(1.08 * 96);
        int rectWidth = (int) Math.round(2.91 * 96);
        int rectX = BADGE_WIDTH * 2 / 5 + 30;
        int rectY = PHOTO_MAX_HEIGHT + (int)Math.round(3.05*96);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(rectX, rectY, rectWidth, rectHeight, 24, 24);
        g2d.draw(roundedRectangle);
        Line2D verticalLine1 = new Line2D.Float(rectX + rectWidth * 1.23f / 4, rectY + TABLE_VERTICAL_OFFSET, rectX + rectWidth * 1.23f / 4, rectY + rectHeight);
        g2d.draw(verticalLine1);
        Line2D verticalLine2 = new Line2D.Float(rectX + rectWidth * 2.77f / 4, rectY + TABLE_VERTICAL_OFFSET, rectX + rectWidth * 2.77f / 4, rectY + rectHeight);
        g2d.draw(verticalLine2);
        Line2D horizontalLine = new Line2D.Float(rectX, rectY + TABLE_HEADER_HEIGHT, rectX + rectWidth, rectY + TABLE_HEADER_HEIGHT);
        g2d.draw(horizontalLine);

        unit = StringUtils.isEmpty(unit)? "--" : unit;
        group = StringUtils.isEmpty(group)? "--" : group;
        camp = StringUtils.isEmpty(camp) ? "--" : camp;

        writeTable(g2d, rectWidth, rectX, rectY, new String[]{"الوحدة", "المجموعة", "المخيم"}, new String[]{"Unit", "Group", "Camp"}, new String[]{unit, group, camp});

        BufferedImage iconImage = ImageUtils.loadFromClasspath(TENT_RESOURCE_FILE_NAME);
        if (iconImage != null) {
            Image img = ImageUtils.resizeImage(iconImage, (int) (ICON_WIDTH * 1.25), (int) (ICON_HEIGHT * 1.25));
            g2d.drawImage(img, rectX + (int) (rectWidth - (ICON_WIDTH * 1.25)) / 2, rectY - (int) (0.75 * ICON_HEIGHT), null);
        }
    }

    private void addStaffCamp(Graphics2D g2d, String camp) {
        int rectHeight = (int) Math.round(1.08 * 96);
        int rectWidth = (int) Math.round(2.22 * 96);
        int rectX = BADGE_WIDTH / 3;
        int rectY = PHOTO_MAX_HEIGHT + (int) Math.round(4.05 * 90);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(rectX, rectY, rectWidth, rectHeight, 24, 24);
        g2d.draw(roundedRectangle);
        Line2D horizontalLine = new Line2D.Float(rectX, rectY + TABLE_HEADER_HEIGHT, rectX + rectWidth, rectY + TABLE_HEADER_HEIGHT);
        g2d.draw(horizontalLine);

        camp = StringUtils.isEmpty(camp) ? "--" : camp;

        writeTable(g2d, rectWidth, rectX, rectY, new String[]{"المخيم - Camp"}, new String[]{" "}, new String[]{camp});

        BufferedImage iconImage = ImageUtils.loadFromClasspath(TENT_RESOURCE_FILE_NAME);
        if (iconImage != null) {
            Image img = ImageUtils.resizeImage(iconImage, ICON_WIDTH, ICON_HEIGHT);
            g2d.drawImage(img, rectX + (rectWidth - ICON_WIDTH) / 2, rectY - (int) (0.65 * ICON_HEIGHT), null);
        }
    }

    private void addBusRectangle(Graphics2D g2d, String busNumber, String seatNumber) {
        int rectHeight = (int) Math.round(1.08 * 96);
        int rectWidth = (int) Math.round(2.22 * 96);
        int rectX = 20;
        int rectY = PHOTO_MAX_HEIGHT + (int) Math.round(3.05 * 96);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(rectX, rectY, rectWidth, rectHeight, 24, 24);
        g2d.draw(roundedRectangle);
        Line2D verticalLine = new Line2D.Float(rectX + rectWidth / 2, rectY + TABLE_VERTICAL_OFFSET, rectX + rectWidth / 2, rectY + rectHeight);
        g2d.draw(verticalLine);
        Line2D horizontalLine = new Line2D.Float(rectX, rectY + TABLE_HEADER_HEIGHT, rectX + rectWidth, rectY + TABLE_HEADER_HEIGHT);
        g2d.draw(horizontalLine);

        seatNumber = StringUtils.isEmpty(seatNumber)? "--" : seatNumber;
        busNumber = StringUtils.isEmpty(busNumber)? "--" : busNumber;

        writeTable(g2d, rectWidth, rectX, rectY, new String[]{"المقعد", "الحافلة"}, new String[]{"Seat", "Bus"}, new String[]{seatNumber, busNumber});

        BufferedImage iconImage = ImageUtils.loadFromClasspath(BUS_RESOURCE_FILE_NAME);
        if (iconImage != null) {
            Image img = ImageUtils.resizeImage(iconImage, ICON_WIDTH, ICON_HEIGHT);
            g2d.drawImage(img, rectX + (rectWidth - ICON_WIDTH)/2, rectY-(int)(0.65*ICON_HEIGHT), null);
        }
    }

    private void addCompanyRectangle(Graphics2D g2d, ApplicantRitualCardLiteDto applicantRitualCard, String uin) {
        uin = uin == null ? "---":uin;
        int rectHeight = (int) Math.round(2 * 84);
        int rectWidth = (int) Math.round(5.28 * 96);
        int rectX = 21;
        int rectY = PHOTO_MAX_HEIGHT + (int) Math.round(2.75 * 96);
        //company rect
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(rectX, rectY, rectWidth, rectHeight, 24, 24);

        g2d.setColor(new Color(0xFFD3D3D3));
        g2d.draw(roundedRectangle);
        g2d.setColor(Color.white);
        g2d.fillRoundRect(rectX, rectY, rectWidth, rectHeight, 24, 24);

        g2d.setColor(new Color(0xFFD3D3D3));
        Line2D verticalLine = new Line2D.Float(rectX + rectWidth / 2, rectY, rectX + rectWidth / 2, rectY + rectHeight);
        g2d.draw(verticalLine);

        writeTable(g2d, rectWidth, rectX, rectY + 25, new String[]{makeLabelFit(applicantRitualCard.getEstablishmentNameAr()), makeLabelFit(applicantRitualCard.getCompanyNameAr())}, new String[]{makeLabelFit(applicantRitualCard.getEstablishmentNameEn()), makeLabelFit(applicantRitualCard.getCompanyName())});


        //applicant details
        g2d.setColor(new Color(0xFFD3D3D3));
        int rectYApplicant = PHOTO_MAX_HEIGHT + (int) Math.round(5.12 * 89);
        RoundRectangle2D applicantDetailsRectangle = new RoundRectangle2D.Float(rectX, rectYApplicant, rectWidth, rectHeight, 24, 24);
        g2d.draw(applicantDetailsRectangle);
        g2d.setColor(Color.white);
        g2d.fillRoundRect(rectX, rectYApplicant, rectWidth, rectHeight, 24, 24);
        g2d.setColor(new Color(0xFF212121));
        Font font = shaaerFont.deriveFont(20f);

        FontMetrics fm = g2d.getFontMetrics(font);
        FontRenderContext frc = g2d.getFontRenderContext();
        int xDif = ((BADGE_WIDTH - fm.stringWidth("رقم الهوية")) - 60);
        int yDif = rectYApplicant + 70;
        TextLayout layout = new TextLayout("رقم الهوية", font, frc);
        layout.draw(g2d, xDif, yDif);
        g2d.setColor(new Color(0xFF6e6d6b));
        yDif += 30;
        layout = new TextLayout("ID Number", font, frc);
        layout.draw(g2d, xDif - 10, yDif);


        BufferedImage qrCode = makeRoundedCorner(generateQRcode(uin, applicantRitualCard.getCardId() + "", false), 30);

        Image img = ImageUtils.resizeImage(qrCode, QR_CODE_MAX_HEIGHT, QR_CODE_MAX_HEIGHT);
        g2d.drawImage(img, (BADGE_WIDTH - img.getWidth(null)) / 2, yDif - 60, null);

        yDif += 45;
        g2d.setColor(Color.black);
        font = shaaerFont.deriveFont(22f);
        font = font.deriveFont(
                Collections.singletonMap(
                        TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD));
        layout = new TextLayout(applicantRitualCard.getIdNumber(), font, frc);
        layout.draw(g2d, xDif - 10, yDif);
        font = font.deriveFont(
                Collections.singletonMap(
                        TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR));
        int yDifLeft = rectYApplicant + 70;
        int xDifLeft = (BADGE_WIDTH / 2 - 220);
        g2d.setColor(new Color(0xFF212121));
        font = shaaerFont.deriveFont(20f);
        layout = new TextLayout("البطاقة الذكية", font, frc);
        layout.draw(g2d, xDifLeft, yDifLeft);
        yDifLeft += 30;
        g2d.setColor(new Color(0xFF6e6d6b));
        layout = new TextLayout(" Smart Card", font, frc);
        layout.draw(g2d, xDifLeft, yDifLeft);
        yDifLeft += 45;
        g2d.setColor(Color.black);
        font = shaaerFont.deriveFont(22f);
        font = font.deriveFont(
                Collections.singletonMap(
                        TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD));
        layout = new TextLayout(uin, font, frc);
        layout.draw(g2d, xDifLeft - 20, yDifLeft);
    }

    public String makeLabelFit(String label) {
        if (label.length() > 28) {
            return label.substring(0, 23) + "...";
        }
        return label + " ";
    }

    public BufferedImage makeRoundedCorner(BufferedImage image,
                                           int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,
                cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
    protected int toARGB(String nm) {
        Long longValue = Long.decode(nm);
        int i = longValue.intValue();
        int a = (i >> 24) & 0xFF;
        int r = (i >> 16) & 0xFF;
        int g = (i >> 8) & 0xFF;
        int b = i & 0xFF;
        return ((a & 0xFF) << 24) | ((b & 0xFF) << 16) | ((g & 0xFF) << 8) | ((r & 0xFF));
    }


    private void addLeaderRectangle(Graphics2D g2d, String leaderName, String leaderMobile) {
        int rectHeight = (int) Math.round(1.15 * 96);
        int rectWidth = (int) Math.round(5.28 * 96);
        int rectX = 20;
        int rectY = PHOTO_MAX_HEIGHT + (int)Math.round(4.35*96);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(rectX, rectY, rectWidth, rectHeight, 24, 24);
        g2d.draw(roundedRectangle);
        Line2D verticalLine = new Line2D.Float(rectX + rectWidth / 2, rectY + TABLE_VERTICAL_OFFSET, rectX + rectWidth / 2, rectY + rectHeight);
        g2d.draw(verticalLine);
        Line2D horizontalLine = new Line2D.Float(rectX, rectY + TABLE_HEADER_HEIGHT, rectX + rectWidth, rectY + TABLE_HEADER_HEIGHT);
        g2d.draw(horizontalLine);

        leaderMobile = StringUtils.isEmpty(leaderMobile)? "--" : leaderMobile;
        leaderName = StringUtils.isEmpty(leaderName)? "--" : leaderName;

        writeTable(g2d, rectWidth, rectX, rectY, new String[]{"جوال القائد", "اسم القائد"}, new String[]{"Leader Mobile", "Leader Name"}, new String[]{leaderMobile, leaderName});

        BufferedImage iconImage = ImageUtils.loadFromClasspath(LEADER_RESOURCE_FILE_NAME);
        if (iconImage != null) {
            Image img = ImageUtils.resizeImage(iconImage, (int)(ICON_WIDTH * 1.75), (int)(ICON_HEIGHT * 1.75));
            g2d.drawImage(img, rectX + (int)(rectWidth - (ICON_WIDTH * 1.75))/2 + 6, rectY-(int)(0.34*ICON_HEIGHT), null);
        }
    }

    private void writeTable(Graphics2D g2d, int rectWidth, int rectX, int rectY, String[] headersAr, String[] headersEn, String[] values) {
        g2d.setColor(new Color(86, 86, 86));
        FontRenderContext frc = g2d.getFontRenderContext();
        Font font;
        LineMetrics lm;
        TextLayout layout;
        for (int i = 0; i< headersAr.length; i++) {
            font = shaaerFont.deriveFont(25f);
            font = font.deriveFont(
                    Collections.singletonMap(
                            TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD));
            // header AR
            lm = font.getLineMetrics(headersAr[i], frc);
            layout = new TextLayout(headersAr[i], font, frc);
            layout.draw(g2d, rectX + (i * rectWidth / headersAr.length) + (int) (rectWidth / headersAr.length - font.getStringBounds(headersAr[i], frc).getWidth()) / 2, rectY + lm.getHeight() / 2 + 8);
            // header EN
            lm = font.getLineMetrics(headersEn[i], frc);
            layout = new TextLayout(headersEn[i], font, frc);
            layout.draw(g2d, rectX + (i * rectWidth / headersAr.length) + (int) (rectWidth / headersAr.length - font.getStringBounds(headersEn[i], frc).getWidth()) / 2, rectY + lm.getHeight() + 16);
            // values
            font = shaaerFont.deriveFont(26f);
            lm = font.getLineMetrics(values[i], frc);
            layout = new TextLayout(values[i], font, frc);
            layout.draw(g2d, rectX + (i * rectWidth/ headersAr.length) + (int) (rectWidth/ headersAr.length - font.getStringBounds(values[i], frc).getWidth()) / 2, rectY + lm.getHeight() + 56);
        }
    }

    private void writeTable(Graphics2D g2d, int rectWidth, int rectX, int rectY, String[] labelEn, String[] labelAr) {
        FontRenderContext frc = g2d.getFontRenderContext();
        Font font;
        LineMetrics lm;
        TextLayout layout;
        for (int i = 0; i< labelAr.length; i++) {
            font = shaaerFont.deriveFont(21f);
            /*font = font.deriveFont(
                    Collections.singletonMap(
                            TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD));*/
            // header AR
            lm = font.getLineMetrics(labelAr[i], frc);
            layout = new TextLayout(labelAr[i], font, frc);
            g2d.setColor(new Color(0xFF212121));
            layout.draw(g2d, rectX + (i * rectWidth / labelAr.length) + (int) (rectWidth / labelAr.length - font.getStringBounds(labelAr[i], frc).getWidth()) / 2, rectY + lm.getHeight() + 26);
            // header EN
            font = shaaerFont.deriveFont(21f);
            //  g2d.setColor(new Color(86, 86, 86, 20));
            lm = font.getLineMetrics(labelEn[i], frc);
            layout = new TextLayout(labelEn[i], font, frc);
            g2d.setColor(new Color(0xFF6e6d6b));
            layout.draw(g2d, rectX + (i * rectWidth / labelAr.length) + (int) (rectWidth / labelAr.length - font.getStringBounds(labelEn[i], frc).getWidth()) / 2, rectY + lm.getHeight() + 66);
            // values
            /*font = shaaerFont.deriveFont(26f);
            lm = font.getLineMetrics(values[i], frc);
            layout = new TextLayout(values[i], font, frc);
            layout.draw(g2d, rectX + (i * rectWidth/ headersAr.length) + (int) (rectWidth/ headersAr.length - font.getStringBounds(values[i], frc).getWidth()) / 2, rectY + lm.getHeight() + 56);*/
        }
    }

   private String convertArabicCharactersToUnicode(String input){

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int ch = input.charAt(i);
            buf.append(ch);
            if(i<input.length()-1)
                buf.append(",");
        }
        return buf.toString();
    }
    private String getBarCodeFoApplicantItemsAsString(String uin,ApplicantRitualCardLiteDto card) {
        StringBuilder barCodeItems = new StringBuilder();
        barCodeItems.append(uin);
        barCodeItems.append("#");
        barCodeItems.append(card.getFullNameEn());
        barCodeItems.append("#");
//        barCodeItems.append(convertArabicCharactersToUnicode(card.getFullNameAr()));
//        barCodeItems.append("#");
//        barCodeItems.append(card.getNationalityCode());
//        barCodeItems.append("#");
        barCodeItems.append(card.getCompanyName());

        return barCodeItems.toString();
    }

    private String getBarCodeForStaffItemsAsString(CompanyStaffVO staffData, String cardId) {
        StringBuilder barCodeItems = new StringBuilder();
        barCodeItems.append(staffData.getSuin());
        barCodeItems.append("#");
        barCodeItems.append(staffData.getFullNameEn());
        barCodeItems.append("#");
//        barCodeItems.append(convertArabicCharactersToUnicode(staffData.getFullNameAr()));
//        barCodeItems.append("#");
//        barCodeItems.append(staffData.getJobTitleCode());
//        barCodeItems.append("#");
        barCodeItems.append(staffData.getCompanyLabelEn());
//        barCodeItems.append(staffData.getIdNumber() != null ? staffData.getIdNumber() : staffData.getPassport());
//        barCodeItems.append("#");
        return barCodeItems.toString();
    }


    private void addQrCodeRectangle(Graphics2D g2d, String uin, String cardId, String ritualType, int ritualSeason) {
        int rectHeight = (int) Math.round(4 * 50);
        int rectWidth = BADGE_WIDTH;
        int rectX = 0;
        int rectY = uin.length() == 14 ? BADGE_HEIGHT + 14 : BADGE_HEIGHT;
        Rectangle2D roundedRectangle = new Rectangle2D.Float(rectX, rectY, rectWidth, rectHeight);
        g2d.draw(roundedRectangle);
        g2d.setColor(new Color(235, 241, 235));
        g2d.fillRect(rectX, rectY, rectWidth, rectHeight);
        FontRenderContext frc = g2d.getFontRenderContext();

        Font font = shaaerFont.deriveFont(38f);
        font = font.deriveFont(
                Collections.singletonMap(
                        TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD));

        FontMetrics fm = g2d.getFontMetrics(font);
        g2d.setColor(new Color(59, 121, 55, 6));
        int xDif = ((BADGE_WIDTH - fm.stringWidth("فعالة")) - 15);
        int yDif = BADGE_HEIGHT + 70;
        TextLayout layout = new TextLayout("فعالة", font, frc);
        layout.draw(g2d, xDif, yDif);

        font = shaaerFont.deriveFont(24f);
        fm = g2d.getFontMetrics(font);
        String card = "رقم شعائر " + uin;
        yDif += 55;
        xDif = ((BADGE_WIDTH - fm.stringWidth(card)) - 15);
        layout = new TextLayout(card, font, frc);
        layout.draw(g2d, xDif, yDif);
        yDif += 40;
        String ritual = "الشعيرة " + ritualType + " " + ritualSeason;
        xDif = ((BADGE_WIDTH - fm.stringWidth(ritual)) - 15);
        layout = new TextLayout(ritual, font, frc);
        layout.draw(g2d, xDif, yDif);
        BufferedImage qrCode = generateQRcode(uin, cardId, false);
        Image img = ImageUtils.resizeImage(qrCode, QR_CODE_MAX_HEIGHT, QR_CODE_MAX_HEIGHT);
        g2d.setBackground(new Color(235, 241, 235));
        g2d.drawImage(img, (BADGE_WIDTH - img.getWidth(null)) / 2 - 180, yDif - 140, null);


    }

    private BufferedImage generateQRcode(String uin, String cardId, boolean isTransparent) {
        try {
            String charset = "UTF-8";
            String data = uin + "#" + cardId;
            Map<EncodeHintType, Object> hashMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hashMap.put(EncodeHintType.MARGIN, 2);
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, 300, 300, hashMap);
            MatrixToImageConfig conf = new MatrixToImageConfig(0xFF212121, isTransparent ? -1 : 0xFFF5F5F5);
            BufferedImage qrcode = MatrixToImageWriter.toBufferedImage(matrix, conf);
            //MatrixToImageWriter.writeToPath(matrix, "png", root.resolve(Paths.get("qrCode.png")));
            return qrcode;
        } catch (IOException | WriterException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
