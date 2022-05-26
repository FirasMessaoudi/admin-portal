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
import com.elm.shj.admin.portal.services.dto.CountryLookupDto;
import com.elm.shj.admin.portal.services.lookup.CountryLookupService;
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
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
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

    private final static int BADGE_WIDTH = (int) Math.round(5.74 * 96);
    private final static int BADGE_HEIGHT = (int) Math.round(9.12 * 96);
    private final static int ICON_WIDTH = (int) Math.round(0.42 * 96);
    private final static int ICON_HEIGHT = (int) Math.round(0.48 * 96);
    private final static int PHOTO_MAX_HEIGHT = (int) Math.round(2.58 * 96);
    private final static int QR_CODE_MAX_HEIGHT = (int) Math.round(2.61 * 68);

    private final static int MOHU_LOGO_MAX_HEIGHT = (int) Math.round(1.15 * 96);
    private final static int TABLE_HEADER_HEIGHT = 64;
    private final static int TABLE_VERTICAL_OFFSET = 22;
    private final static int MOBILE_BADGE_HEIGHT = (int) Math.round(12.12 * 96);

    private final static String BADGE_RESOURCES_PATH = "badge/";
    private final static String ELM_FONT_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "DINNextLTArabic-Regular-2.ttf";
    private final static String TOP_BG_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "top-bg.png";
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
    private final CountryLookupService countryLookupService;
    private final RitualTypeLookupRepository ritualTypeLookupRepository;
    private final CompanyStaffService companyStaffService;
    private final CompanyStaffTitleLookupRepository companyStaffTitleLookupRepository;

    static {
        try {
            shaaerFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(new ClassPathResource(ELM_FONT_RESOURCE_FILE_NAME).getInputStream()));
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

        g2d.setColor(Color.WHITE);
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
        List<CountryLookupDto> mainLangCountryLookupList = countryLookupService.findAllByCode(applicantRitualCardLite.getNationalityCode());
        CountryLookupDto arabicCountryLookup = mainLangCountryLookupList.stream().filter(countryLookup -> countryLookup.getLang().equals("ar")).findFirst().orElse(null);
        String nationalityAr = arabicCountryLookup == null ? "" : arabicCountryLookup.getLabel();
        CountryLookupDto englishCountryLookup = mainLangCountryLookupList.stream().filter(countryLookup -> countryLookup.getLang().equals("en")).findFirst().orElse(null);
        String nationalityEn = englishCountryLookup == null ? "" : englishCountryLookup.getLabel();

        BufferedImage badgeImage = new BufferedImage(BADGE_WIDTH, withQr ? MOBILE_BADGE_HEIGHT : BADGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = badgeImage.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, BADGE_WIDTH, withQr ? MOBILE_BADGE_HEIGHT : BADGE_HEIGHT);

        g2d.setColor(new Color(86, 86, 86));

        ImageUtils.applyQualityRenderingHints(g2d);

        addHeaderBg(g2d, withQr);
        addFooterBg(g2d, withQr);
        addZone(g2d);
        g2d.setColor(new Color(86, 86, 86));
        addPilgrimImage(g2d, applicantRitualCardLite.getPhoto(), true);

        addNameAndNationality(g2d, applicantRitualCardLite.getFullNameAr(), applicantRitualCardLite.getFullNameEn(), nationalityAr, nationalityEn);

        addRitual(g2d, ritualType, applicantRitualCardLite.getHijriSeason() + "");

        addCampRectangle(g2d, applicantRitualCardLite.getUnitCode(), applicantRitualCardLite.getGroupCode(), applicantRitualCardLite.getCampCode());
        addBusRectangle(g2d, applicantRitualCardLite.getBusNumber(), applicantRitualCardLite.getSeatNumber());
        addLeaderRectangle(g2d, applicantRitualCardLite.getLeaderNameAr(), applicantRitualCardLite.getLeaderMobile());
        String decodedBarCode = getBarCodeFoApplicantItemsAsString(uin, applicantRitualCardLite);
        addBarCode(g2d, decodedBarCode, true);
        if (withQr) {
            addQrCodeRectangle(g2d, uin, String.valueOf(applicantRitualCardLite.getCardId()), ritualType, applicantRitualCardLite.getHijriSeason());
        }

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

    private void addHeaderBg(Graphics2D g2d, boolean withQr) {
        BufferedImage topBackground = ImageUtils.loadFromClasspath(TOP_BG_RESOURCE_FILE_NAME);
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
        g2d.drawImage(img, BADGE_WIDTH - img.getWidth(null) - 32, (int) Math.round(1.5 * 96), null);


        // draw a line underneath it

        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int xDif = BADGE_WIDTH - img.getWidth(null) - 32;
        int yDif = (int) Math.round(1.5 * 96) + img.getHeight(null) + 8;
        g2d.drawLine(xDif, yDif, xDif + img.getWidth(null), yDif);

        FontRenderContext frc = g2d.getFontRenderContext();

        Font font = shaaerFont.deriveFont(22f);
        xDif -= 30;
        yDif += 10;
        TextLayout layout = new TextLayout(ritualType, font, frc);
        yDif += 22;
        layout.draw(g2d, xDif + img.getWidth(null) / 2 - 10, yDif);
        font = shaaerFont.deriveFont(26f);
        xDif += 30;
        layout = new TextLayout(ritualYear, font, frc);
        yDif += 32;
        layout.draw(g2d, xDif + img.getWidth(null) / 2 - 22, yDif);
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
        FontRenderContext frc = g2d.getFontRenderContext();

        Font font = shaaerFont.deriveFont(32f);

        FontMetrics fm = g2d.getFontMetrics(font);

        int xDif = ((BADGE_WIDTH - fm.stringWidth(fullNameAr)) / 2);
        int yDif = PHOTO_MAX_HEIGHT + fm.getAscent() + (int)Math.round(0.85*96) - 8;

        TextLayout layout = new TextLayout(fullNameAr, font, frc);
        layout.draw(g2d, xDif, yDif);

        xDif = ((BADGE_WIDTH - fm.stringWidth(fullNameEn)) / 2);
        yDif += 44;
        layout = new TextLayout(fullNameEn, font, frc);
        layout.draw(g2d, xDif, yDif);

        // draw a line underneath it
        yDif += 18;
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine((int) (BADGE_WIDTH * 0.125), yDif, (int) (BADGE_WIDTH * 0.875), yDif);

        font = shaaerFont.deriveFont(24f);
        fm = g2d.getFontMetrics(font);

        yDif += 30;
        // xDif += 95;
        xDif = ((BADGE_WIDTH - fm.stringWidth(nationalityAr)) / 2);

        layout = new TextLayout(nationalityAr, font, frc);
        layout.draw(g2d, xDif, yDif);

        xDif = ((BADGE_WIDTH - fm.stringWidth(nationalityEn)) / 2);
        yDif += 36;
        layout = new TextLayout(nationalityEn, font, frc);
        layout.draw(g2d, xDif, yDif);
    }

    private void addPilgrimImage(Graphics2D g2d, String base64Photo, boolean isApplicant) {
        BufferedImage pilgrimImage = ImageUtils.loadFromBase64String(base64Photo);
        if (pilgrimImage != null) {
            Image img = ImageUtils.resizeImage(pilgrimImage, PHOTO_MAX_HEIGHT, PHOTO_MAX_HEIGHT);
            int yDif = isApplicant ? (int) Math.round(0.9 * 100) : Math.round(2 * 93);
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
        g2d.setColor(new Color(59, 121, 55));
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
        BufferedImage qrCode = generateQRcode(uin, cardId);
        Image img = ImageUtils.resizeImage(qrCode, QR_CODE_MAX_HEIGHT, QR_CODE_MAX_HEIGHT);
        g2d.setBackground(new Color(235, 241, 235));
        g2d.drawImage(img, (BADGE_WIDTH - img.getWidth(null)) / 2 - 180, yDif - 140, null);


    }

    private BufferedImage generateQRcode(String uin, String cardId) {
        try {
            String charset = "UTF-8";
            String data = uin +"#"+ cardId;
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, 300, 300);
            MatrixToImageConfig conf = new MatrixToImageConfig(0xFF37793B, 0xFFEBF1EB);
            BufferedImage qrcode = MatrixToImageWriter.toBufferedImage(matrix, conf);
            //MatrixToImageWriter.writeToPath(matrix, "png", root.resolve(Paths.get("qrCode.png")));
            return qrcode;
        } catch (IOException | WriterException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
