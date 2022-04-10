package com.elm.shj.admin.portal.services.prinitng;

import com.elm.shj.admin.portal.orm.entity.ApplicantBasicInfoVo;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.services.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ManifestService {
    private final static int BADGE_WIDTH = 3000;
    private final static int BADGE_HEIGHT = 5000;
    private final static int CELL_HEIGHT = 50;
    private final static int CELL_WIDTH = 500;

    private final ApplicantRitualCardLiteService applicantCardService;


    public List<ManifestVo> generateManifest(String printRequestReferenceNumber, BatchCollectionVO batchCollection, EManifestType manifestType) {
        if (EManifestType.IMAGE == manifestType)
            return generateManifestAsImages(printRequestReferenceNumber, batchCollection);
        throw new IllegalArgumentException("Manifest Type not supported " + manifestType);

    }

    private List<ManifestVo> generateManifestAsImages(String printRequestReferenceNumber, BatchCollectionVO batchCollectionVO) {
        List<ManifestVo> manifestImages = new ArrayList<>();
        batchCollectionVO.getBatchMainCollections().forEach(batchMainCollectionDto -> batchMainCollectionDto.getSubCollections()
                .forEach(subCollectionVO -> {
                    BufferedImage subCollectionImage = new BufferedImage(BADGE_WIDTH, BADGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D g2d = subCollectionImage.createGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, BADGE_WIDTH, BADGE_HEIGHT);

                    g2d.setColor(new Color(86, 86, 86));
                    drawRow(g2d, 0, Arrays.asList("Print Request Number", printRequestReferenceNumber));
                    drawRow(g2d, 1, Arrays.asList("Batch Number", batchCollectionVO.getBatchReferenceNumber()));
                    drawRow(g2d, 2, Arrays.asList("Collection Number", batchMainCollectionDto.getReferenceNumber()));
                    drawRow(g2d, 3, Arrays.asList("Sub Collection Number", subCollectionVO.getReferenceNumber()));
                    List<ApplicantBasicInfoVo> applicantBasicInfoVoList = applicantCardService.findApplicantsBasicInfoByDigitalIds(subCollectionVO.getDigitalIds());
                    for (int i = 0; i < applicantBasicInfoVoList.size(); i++) {
                        drawBody(g2d, applicantBasicInfoVoList.get(i), i + 4);
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

    private void drawBody(Graphics2D g2d, ApplicantBasicInfoVo applicantBasicInfoVo, int index) {
        if (applicantBasicInfoVo.getSerialNumber() == null)
            return;
        drawRow(g2d, index, Arrays.asList(applicantBasicInfoVo.getUin(), applicantBasicInfoVo.getFullNameAr(), applicantBasicInfoVo.getFullNameEn(), applicantBasicInfoVo.getSerialNumber()));
    }

    private void drawRow(Graphics2D g2d, int index, List<String> values) {

        for (int i = 0; i < values.size(); i++) {
            drawCell(g2d, CELL_HEIGHT * index + CELL_HEIGHT, i, values.get(i));
        }

    }


    private void drawCell(Graphics2D g2d, int y, int index, String value) {

        g2d.drawRect(CELL_WIDTH * index, y, CELL_WIDTH, CELL_HEIGHT);
        g2d.drawString(value, 10 + (CELL_WIDTH * index), y + (CELL_HEIGHT / 2));

    }

}


