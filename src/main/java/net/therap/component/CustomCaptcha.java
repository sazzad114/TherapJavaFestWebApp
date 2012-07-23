package net.therap.component;

/**
 * Created by
 * User: tahmid
 * Date: 7/15/12
 * Time: 6:07 PM
 */
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.captcha.Captcha;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

@Name("org.jboss.seam.captcha.captcha")
@Scope(ScopeType.SESSION)
@Install(precedence = Install.APPLICATION)
public class CustomCaptcha extends Captcha {

    private Color backgroundColor = new Color(0xf5,0xf5, 0xf5);
    private Font textFont = new Font("Arial", Font.PLAIN, 25);
    private int charsToPrint = 6;
    private int width = 120;
    private int height = 25;
    private int circlesToDraw = 4;
    private float horizMargin = 20.0f;
    private double rotationRange = 0.2;
    private String elegibleChars = "ABDEFGHJKLMRSTUVWXYabdefhjkmnrstuvwxy23456789";
    private char[] chars = elegibleChars.toCharArray();

    @Override
    @Create
    public void init() {
        super.init();

        //setCaptchaString();
    }

    private void setCaptchaString() {
        StringGenerator stringGenerator = new StringGenerator(charsToPrint);
        String finalString = stringGenerator.createString();

        setChallenge(finalString);
        setCorrectResponse(finalString);
    }



    @Override
    public BufferedImage renderChallenge() {

        // Background
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setColor(backgroundColor);
        graphics2D.fillRect(0, 0, width, height);

        // Some obfuscation circles
        drawObfuscatingCircles(graphics2D);

        // Text
        drawText(graphics2D);

        //

        return bufferedImage;
    }

    private void drawText(Graphics2D graphics2D) {
        graphics2D.setFont(textFont);
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int maxAdvance = fontMetrics.getMaxAdvance();
        int fontHeight = fontMetrics.getHeight();
        float spaceForLetters = -horizMargin * 2 + width;
        float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);

        setCaptchaString();

        char[] allChars = getChallenge().toCharArray();
        for (int i = 0; i < allChars.length; i++ ) {
            char charToPrint = allChars[i];
            int charWidth = fontMetrics.charWidth(charToPrint);
            int charDim = Math.max(maxAdvance, fontHeight);
            int halfCharDim = (charDim / 2);
            BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
            Graphics2D charGraphics = charImage.createGraphics();
            charGraphics.translate(halfCharDim, halfCharDim);
            double angle = (Math.random() - 0.5) * rotationRange;
            charGraphics.transform(AffineTransform.getRotateInstance(angle));
            charGraphics.translate(-halfCharDim, -halfCharDim);
            int charColor = 60 + (int)(Math.random() * 90);
            charGraphics.setColor(new Color(charColor, charColor, charColor));
            charGraphics.setFont(textFont);
            int charX = (int) (0.5 * charDim - 0.5 * charWidth);
            charGraphics.drawString("" + charToPrint, charX, ((charDim - fontMetrics.getAscent())/2 + fontMetrics.getAscent()));
            float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
            int y = ((height - charDim) / 2);
            graphics2D.drawImage(charImage, (int) x, y, charDim, charDim, null, null);

            charGraphics.dispose();
        }
        graphics2D.dispose();
    }

    private void drawObfuscatingCircles(Graphics2D graphics2D) {
        for (int i = 0; i < circlesToDraw; i++) {
            int circleColor = 80 + (int)(Math.random() * 70);
            float circleLinewidth = 0.3f + (float)(Math.random());
            graphics2D.setColor(new Color(circleColor, circleColor, circleColor));
            graphics2D.setStroke(new BasicStroke(circleLinewidth));
            int circleRadius = (int) (Math.random() * height / 2.0);
            int circleX = (int) (Math.random() * width - circleRadius);
            int circleY = (int) (Math.random() * height - circleRadius);
            graphics2D.drawOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
        }
    }

    @Override
    public boolean validateResponse(String response) {
       return getChallenge().equals(response);
    }
}
