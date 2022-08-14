package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;

/**
 *  * @author sivan Jhirad, ID: 209193481
 *  * game.PauseScreen
 */

public class PauseScreen extends KeyPressStoppableAnimation {

    private static final Color COLOR_BACKGROUND =  new Color(236, 195, 222);

    /**
     * constructor.
     * @param keyboardSensor keyboard sensor
     */
    public PauseScreen(KeyboardSensor keyboardSensor) {
        super(keyboardSensor, KeyboardSensor.SPACE_KEY);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(COLOR_BACKGROUND);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(330, d.getHeight() / 2, "paused", 32);
        d.drawText(220, 400 , "press space to continue", 32);
        super.doOneFrame(d);
    }
}