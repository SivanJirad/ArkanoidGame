package gamelevels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 *  * @author sivan Jhirad, ID: 209193481
 *  * game.DirectHit
 */
public class DirectHitBackground  extends LevelBackground implements Sprite {

    private static final int CENTER_CIRCLE_X = 400;
    private static final int CENTER_CIRCLE_Y = 130 + 15;
    private static final int RADIUS_START = 100;

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(new Color(234, 180, 191));
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(new Color(95, 9, 32));
        for (int i = 0; i < 3; i++) {
            surface.drawCircle(CENTER_CIRCLE_X, CENTER_CIRCLE_Y, RADIUS_START - 20 * i);
        }
        //Vertical lines
        surface.drawLine(CENTER_CIRCLE_X, CENTER_CIRCLE_Y - 15 - 5, CENTER_CIRCLE_X, CENTER_CIRCLE_Y - 15 - 100);
        surface.drawLine(CENTER_CIRCLE_X, CENTER_CIRCLE_Y + 15 + 5, CENTER_CIRCLE_X, CENTER_CIRCLE_Y + 15 + 100);
        //Horizontal line
        surface.drawLine(CENTER_CIRCLE_X - 15 - 5, CENTER_CIRCLE_Y, CENTER_CIRCLE_X - 15 - 100, CENTER_CIRCLE_Y);
        surface.drawLine(CENTER_CIRCLE_X + 15 + 5, CENTER_CIRCLE_Y, CENTER_CIRCLE_X + 15 + 100, CENTER_CIRCLE_Y);

    }

}
