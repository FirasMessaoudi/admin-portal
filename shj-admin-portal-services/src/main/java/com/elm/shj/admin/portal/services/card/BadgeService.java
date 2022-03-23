/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.services.applicant.ApplicantPackageService;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.services.dto.BadgeVO;
import com.elm.shj.admin.portal.services.dto.CountryLookupDto;
import com.elm.shj.admin.portal.services.lookup.CountryLookupService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.services.utils.ImageUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.PDF417Writer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private final static int MOHU_LOGO_MAX_HEIGHT = (int) Math.round(1.15 * 96);
    private final static int TABLE_HEADER_HEIGHT = 64;
    private final static int TABLE_VERTICAL_OFFSET = 22;

    private final static String BADGE_RESOURCES_PATH = "badge/";
    private final static String ELM_FONT_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "DINNextLTArabic-Regular-2.ttf";
    private final static String USER_PHOTO_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "pilgrim-photo.txt";
    private final static String TOP_BG_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "top-bg.png";
    private final static String BOTTOM_BG_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "bottom-bg.png";
    private final static String MOHU_LOGO_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "mohu-logo.png";
    private final static String TENT_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "tent.jpg";
    private final static String BUS_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "bus.jpg";
    private final static String LEADER_RESOURCE_FILE_NAME = BADGE_RESOURCES_PATH + "leader.jpg";

    private static Font shaaerFont;

    private final ApplicantRitualCardLiteService applicantCardService;
    private final ApplicantPackageService applicantPackageService;
    private final CountryLookupService countryLookupService;

    static {
        try {
            shaaerFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(BadgeService.class.getClassLoader().getResourceAsStream(ELM_FONT_RESOURCE_FILE_NAME)));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(shaaerFont);
        } catch (IOException | FontFormatException e) {
            log.error("Error while creating Shaaer font.", e);
        }
    }

    public BadgeVO generateApplicantBadge(String uin) {
        //get applicant details
        //applicant table: fullNameAr; fullNameEn; photo, nationality code;
        //country lookup table: nationalityAr; nationalityEn;
        //applicant package, ritual package, ritual type lookup tables: ritualType;
        //applicant package, ritual package, company ritual season, ritual season tables: ritualSeasonYear;
        // unit; group; camp; seat; bus; --> ignore for now
        //group applicant list, applicant group, company staff tables: groupLeaderName; groupLeaderMobile;


        // get the last applicant package
        Long applicantPackageId = applicantPackageService.findLatestIdByApplicantUIN(uin);
        ApplicantRitualCardLiteDto applicantRitualCardLite = applicantCardService.findCardDetailsByUinAndPackageId(uin, applicantPackageId).orElse(null);
        if (applicantRitualCardLite == null) {
            log.error("Cannot generate badge, no ritual details for the applicant with {} uin", uin);
            return null;
        }

        // get the nationality
        List<CountryLookupDto> mainLangCountryLookupList = countryLookupService.findAllByCode(applicantRitualCardLite.getNationalityCode());
        CountryLookupDto arabicCountryLookup = mainLangCountryLookupList.stream().filter(countryLookup -> countryLookup.getLang().equals("ar")).findFirst().orElse(null);
        String nationalityAr = arabicCountryLookup == null ? "" : arabicCountryLookup.getLabel();
        CountryLookupDto englishCountryLookup = mainLangCountryLookupList.stream().filter(countryLookup -> countryLookup.getLang().equals("en")).findFirst().orElse(null);
        String nationalityEn = englishCountryLookup == null ? "" : englishCountryLookup.getLabel();

        BufferedImage badgeImage = new BufferedImage(BADGE_WIDTH, BADGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = badgeImage.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, BADGE_WIDTH, BADGE_HEIGHT);

        g2d.setColor(new Color(86, 86, 86));

        ImageUtils.applyQualityRenderingHints(g2d);

        addHeaderBg(g2d);
        addFooterBg(g2d);

        addPilgrimImage(g2d, applicantRitualCardLite.getPhoto());

        addNameAndNationality(g2d, applicantRitualCardLite.getFullNameAr(), applicantRitualCardLite.getFullNameEn(), nationalityAr, nationalityEn);

        addRitual(g2d, applicantRitualCardLite.getRitualType(), applicantRitualCardLite.getHijriSeason() + "");

        addCampRectangle(g2d, applicantRitualCardLite.getUnitCode(), applicantRitualCardLite.getGroupCode(), applicantRitualCardLite.getCampCode());
        addBusRectangle(g2d, applicantRitualCardLite.getBusNumber(), applicantRitualCardLite.getSeatNumber());
        addLeaderRectangle(g2d, applicantRitualCardLite.getLeaderNameAr(), applicantRitualCardLite.getLeaderMobile());
        addBarCode(g2d, uin);

        String imgStr = null;
        try {
            imgStr = ImageUtils.imgToBase64String(badgeImage);
        } catch (IOException e) {
            log.error("Error while converting image to base64 string for {} digital id.", uin, e);
        }

        return BadgeVO.builder().badgeImage(imgStr).build();
    }

    private void addHeaderBg(Graphics2D g2d) {
        BufferedImage topBackground = ImageUtils.loadFromClasspath(TOP_BG_RESOURCE_FILE_NAME);
        if (topBackground != null) {
            g2d.drawImage(ImageUtils.resizeImage(topBackground, BADGE_WIDTH, BADGE_HEIGHT), 0, 0, null);
        }
    }

    private void addFooterBg(Graphics2D g2d) {
        BufferedImage bottomBackground = ImageUtils.loadFromClasspath(BOTTOM_BG_RESOURCE_FILE_NAME);
        if (bottomBackground != null) {
            Image img = ImageUtils.resizeImage(bottomBackground, BADGE_WIDTH, BADGE_HEIGHT);
            g2d.drawImage(ImageUtils.resizeImage(bottomBackground, BADGE_WIDTH, BADGE_HEIGHT), 0, BADGE_HEIGHT - img.getHeight(null), null);
        }
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

        TextLayout layout = new TextLayout(ritualType, font, frc);
        yDif += 22;
        layout.draw(g2d, xDif + img.getWidth(null)/2 - 10, yDif);
        font = shaaerFont.deriveFont(26f);
        layout = new TextLayout(ritualYear, font, frc);
        yDif += 32;
        layout.draw(g2d, xDif + img.getWidth(null)/2 - 22, yDif);
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
        g2d.drawLine((int)(BADGE_WIDTH * 0.125), yDif, (int)(BADGE_WIDTH * 0.875), yDif);

        font = shaaerFont.deriveFont(24f);
        fm = g2d.getFontMetrics(font);

        yDif += 30;
        layout = new TextLayout(nationalityAr, font, frc);
        layout.draw(g2d, xDif, yDif);

        xDif = ((BADGE_WIDTH - fm.stringWidth(nationalityEn)) / 2);
        yDif += 36;
        layout = new TextLayout(nationalityEn, font, frc);
        layout.draw(g2d, xDif, yDif);
    }

    private void addPilgrimImage(Graphics2D g2d, String base64Photo) {
        BufferedImage pilgrimImage = ImageUtils.loadFromBase64String(base64Photo);
        if (pilgrimImage != null) {
            Image img = ImageUtils.resizeImage(pilgrimImage, PHOTO_MAX_HEIGHT, PHOTO_MAX_HEIGHT);
            g2d.drawImage(img, (BADGE_WIDTH - img.getWidth(null)) / 2 , (int)Math.round(0.8*96), null);
        }
    }

    public void addBarCode(Graphics2D g2d, String uin) {
        PDF417Writer barcodeWriter = new PDF417Writer();
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix;
        try {
            bitMatrix = barcodeWriter.encode(uin, BarcodeFormat.PDF_417, BADGE_WIDTH*2/3, (int)Math.round(0.72*96), hintMap);
            BufferedImage barCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            g2d.drawImage(barCodeImage, (BADGE_WIDTH - barCodeImage.getWidth()) / 2 , BADGE_HEIGHT - barCodeImage.getHeight() - 34, null);
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
        camp = StringUtils.isEmpty(camp)? "--" : camp;

        writeTable(g2d, rectWidth, rectX, rectY, new String[]{"الوحدة", "المجموعة", "المخيم"}, new String[]{"Unit", "Group", "Camp"}, new String[]{unit, group, camp});

        BufferedImage iconImage = ImageUtils.loadFromClasspath(TENT_RESOURCE_FILE_NAME);
        if (iconImage != null) {
            Image img = ImageUtils.resizeImage(iconImage, (int)(ICON_WIDTH * 1.25), (int)(ICON_HEIGHT * 1.25));
            g2d.drawImage(img, rectX + (int)(rectWidth - (ICON_WIDTH * 1.25))/2, rectY-(int)(0.75*ICON_HEIGHT), null);
        }
    }

    private void addBusRectangle(Graphics2D g2d, String busNumber, String seatNumber) {
        int rectHeight = (int) Math.round(1.08 * 96);
        int rectWidth = (int) Math.round(2.22 * 96);
        int rectX = 20;
        int rectY = PHOTO_MAX_HEIGHT + (int) Math.round(3.05*96);
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
            // header AR
            lm = font.getLineMetrics(headersAr[i], frc);
            layout = new TextLayout(headersAr[i], font, frc);
            layout.draw(g2d, rectX + (i * rectWidth/ headersAr.length) + (int) (rectWidth/ headersAr.length - font.getStringBounds(headersAr[i], frc).getWidth()) / 2, rectY + lm.getHeight() / 2 + 8);
            // header EN
            lm = font.getLineMetrics(headersEn[i], frc);
            layout = new TextLayout(headersEn[i], font, frc);
            layout.draw(g2d, rectX + (i * rectWidth/ headersAr.length) + (int) (rectWidth/ headersAr.length - font.getStringBounds(headersEn[i], frc).getWidth()) / 2, rectY + lm.getHeight() + 16);
            // values
            font = shaaerFont.deriveFont(26f);
            lm = font.getLineMetrics(values[i], frc);
            layout = new TextLayout(values[i], font, frc);
            layout.draw(g2d, rectX + (i * rectWidth/ headersAr.length) + (int) (rectWidth/ headersAr.length - font.getStringBounds(values[i], frc).getWidth()) / 2, rectY + lm.getHeight() + 56);
        }
    }

    //TODO: For test only, to be removed later
//    public static BadgeContentVO dummy() {
//        BadgeContentVO badgeContent = new BadgeContentVO();
//        badgeContent.setFullNameAr("عبدالرحمن عبدالعزيز الزهراني");
//        badgeContent.setFullNameEn("Abdulrahman Abdulaziz Alzahrani");
//
//        String stringTooLong = new BufferedReader(
//                new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(USER_PHOTO_RESOURCE_FILE_NAME))))
//                .lines().collect(Collectors.joining());
//
//        badgeContent.setPhoto(stringTooLong);
//        badgeContent.setNationalityAr("المملكة العربية السعودية");
//        badgeContent.setNationalityEn("kingdom of Saudi Arabia");
//        badgeContent.setUin("109283007408");
//
//        badgeContent.setRitualType("حج");
//        badgeContent.setRitualSeasonYear("١٤٤٣");
//        badgeContent.setUnit("12");
//        badgeContent.setGroup("102");
//        badgeContent.setCamp("3005");
//        badgeContent.setSeat("758");
//        badgeContent.setBus("12");
//        badgeContent.setGroupLeaderName("عبد الله محمد القرشي");
//        badgeContent.setGroupLeaderMobile("0512345678");
//        return badgeContent;
//    }

    //TODO: For test only, to be removed later
//    public static void main(String[] args) {
//        BadgeService badgeService = new BadgeService();
//        ImageUtils.saveToFile(badgeService.generateBadge(BadgeService.dummy()), "D:\\buffered-test-2.png");
//        String imgStr = null;
//        try {
//            imgStr = ImageUtils.imgToBase64String(badgeService.generateBadge(BadgeService.dummy()));
//            System.out.println(imgStr);
//
//            byte[] data = Base64.decodeBase64(imgStr);
//            try (OutputStream stream = new FileOutputStream("D:\\base-test-2.png")) {
//                stream.write(data);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
