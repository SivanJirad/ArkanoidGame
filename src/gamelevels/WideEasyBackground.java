package gamelevels;

import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author sivan Jhirad, ID: 209193481
 * game.WideEasyBackground
 */
public class WideEasyBackground  extends LevelBackground {

    private static final int CENTER_CIRCLE_X = 150;
    private static final int CENTER_CIRCLE_Y = 180;
    private static final int RADIUS_START = 50;

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(new Color(206, 248, 247));
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());


        surface.setColor(new Color(241, 233, 185));

        surface.drawLine(220, 190, 700, 300);
        //left
        for (int i = 0; i < 18; i++) {
            surface.drawLine(90 + 4 * i, 215 , 17 + 7 * i, 300);
        }
        //middle17 + 7 * i
        surface.drawLine(150, 215, 150, 300);

        //right
        for (int i = 0; i < 70; i++) {
            surface.drawLine(150 + i, 180, 150 + 10 * i, 300);

        }
        Color[] color = new Color[3];
        color[0] = new Color(254, 225, 23);
        color[1] = new Color(237, 212, 88);
        color[2] = new Color(236, 231, 176);
        for (int i = 2; i >= 0; i--) {
            surface.setColor(color[i]);
            surface.fillCircle(CENTER_CIRCLE_X, CENTER_CIRCLE_Y, RADIUS_START + 10 * i);
        }
    }
}

