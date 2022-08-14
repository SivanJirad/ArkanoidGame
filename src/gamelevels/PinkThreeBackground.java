package gamelevels;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.PinkThreeBackground
 */
public class PinkThreeBackground extends LevelBackground  {

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(new Color(236, 195, 222));
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(Color.BLACK);
        surface.fillRectangle(80, 420   , 80, 180);
        surface.setColor(Color.WHITE);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                surface.fillRectangle(85 + 15 * j, 425 + 25 * i   , 10, 20);

            }
        }
        surface.setColor(new Color(62, 58, 57));
        surface.fillRectangle(105, 350, 30, 70);
        surface.setColor(new Color(76, 77, 71));
        surface.fillRectangle(115, 150, 10, 200);
        Color[] color = new Color[3];

        color[0] = Color.WHITE;
        color[1] = Color.RED;
        color[2] = new Color(199, 182, 106);
        for (int i = 2; i >= 0; i--) {
            surface.setColor(color[i]);
            surface.fillCircle(120, 141, 3 + 3 * i);
        }



    }
}
