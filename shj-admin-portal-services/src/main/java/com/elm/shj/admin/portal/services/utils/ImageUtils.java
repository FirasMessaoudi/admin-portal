/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

/**
 * Date utility functions
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public class ImageUtils {

    public static Image resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        int img_width = originalImage.getWidth();
        int img_height = originalImage.getHeight();
        float factx = (float) img_width / targetWidth;
        float facty = (float) img_height / targetHeight;
        float fact = Math.max(factx, facty);
        img_width = (int) (img_width / fact);
        img_height = (int) (img_height / fact);
        return originalImage.getScaledInstance(img_width, img_height, Image.SCALE_SMOOTH);
    }

    public static BufferedImage loadFromClasspath(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(ImageUtils.class.getClassLoader().getResourceAsStream(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }

    public static BufferedImage loadFromBase64String(String base64String) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return image;
    }

    public static void saveToFile(BufferedImage image, String filename) {
        File file = new File(filename);
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void applyQualityRenderingHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    public static BufferedImage tint(float red, float green, float blue, float alpha,
                                     BufferedImage sprite)
    {
        BufferedImage tintedSprite = new BufferedImage(sprite.getWidth(), sprite.
                getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = tintedSprite.createGraphics();
        graphics.drawImage(sprite, 0, 0, null);
        graphics.dispose();

        for (int i = 0; i < tintedSprite.getWidth(); i++)
        {
            for (int j = 0; j < tintedSprite.getHeight(); j++)
            {
                int ax = tintedSprite.getColorModel().getAlpha(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int rx = tintedSprite.getColorModel().getRed(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int gx = tintedSprite.getColorModel().getGreen(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int bx = tintedSprite.getColorModel().getBlue(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                rx *= red;
                gx *= green;
                bx *= blue;
                ax *= alpha;
                tintedSprite.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
            }
        }
        return tintedSprite;
    }

    public static void paintTable(Graphics2D g2, Font font, String[] headersAr, String[] headersEn, int tableWidth, int tableHeight)
    {
        int
                ROWS =  2,
                COLS =  2,
                PAD  = 10;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Insets insets = new Insets(0,0,0,0);    // border info
        double xInc = (tableWidth - insets.left - insets.right - 2*PAD)/COLS;
        double yInc = (tableHeight - insets.top - insets.bottom - 2*PAD)/ROWS;
        // vertical lines
        double x1 = insets.left + PAD, y1 = insets.top + PAD,
                y2 = tableHeight - insets.bottom - PAD, x2;
        for(int j = 0; j <= COLS; j++)
        {
            g2.draw(new Line2D.Double(x1, y1, x1, y2));
            x1 += xInc;
        }
        // horizontal lines
        x1 = insets.left + PAD; x2 = tableWidth - insets.right - PAD;
        for(int j = 0; j <= ROWS; j++)
        {
            g2.draw(new Line2D.Double(x1, y1, x2, y1));
            y1 += yInc;
        }
        // try a couple of cell strings
        g2.setFont(font);
        FontRenderContext frc = g2.getFontRenderContext();
        x1 = 0;
        for(int j = 0; j < headersAr.length; j++)
        {
            float width = (float)font.getStringBounds(headersAr[j], frc).getWidth();
            LineMetrics lm = font.getLineMetrics(headersAr[j], frc);
            float ascent = lm.getAscent(), descent = lm.getDescent();
            float sx = (float)(insets.left + PAD + x1 + (xInc - width)/2);
            float sy = (float)(insets.top + PAD + (yInc + ascent)/2 - descent);
            g2.drawString(headersAr[j], sx, sy);

            lm = font.getLineMetrics(headersEn[j], frc);
            ascent = lm.getAscent();
            descent = lm.getDescent();
            sx = (float)(insets.left + PAD + x1 + (xInc - width)/2);
            sy = (float)(insets.top + PAD + (yInc + 20)/2 + ascent - descent);
            g2.drawString(headersEn[j], sx, sy);
            x1 += xInc;
        }
    }

    public static String imgToBase64String(final BufferedImage img) throws IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(img, "png", os);
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }
}
