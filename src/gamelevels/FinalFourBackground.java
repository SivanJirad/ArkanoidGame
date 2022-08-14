package gamelevels;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 *  * @author sivan Jhirad, ID: 209193481
 *  * game.FinalFourBackground
 */
public class FinalFourBackground extends LevelBackground {

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(new Color(195, 236, 228));
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(Color.WHITE);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 20; j++) {
                surface.drawLine(60 + 500 * i + 6 * j, 400 + 100 * i, 30 + 500 * i + 6 * j, 600);
            }
        }
        for (int i = 0; i < 2; i++) {
            surface.setColor(new Color(204, 204, 204));
            surface.fillCircle(75 + 500 * i, 400 + 100 * i, 25);
            surface.fillCircle(95 + 500 * i, 425 + 100 * i, 30);
            surface.setColor(new Color(187, 187 , 187));
            surface.fillCircle(112 + 500 * i, 395 + 100 * i, 35);
            surface.setColor(new Color(170, 170, 170));
            surface.fillCircle(155 + 500 * i, 405 + 100 * i, 30);
            surface.fillCircle(120 + 500 * i, 425 + 100 * i, 25);
        }
    }
}
