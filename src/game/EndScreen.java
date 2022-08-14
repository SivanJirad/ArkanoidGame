package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 *  * @author sivan Jhirad, ID: 209193481
 *  * game.EndScreen
 */
public class EndScreen extends KeyPressStoppableAnimation {

    private String massage;

    private static final Color COLOR_BACKGROUND = Color.BLACK;

    /**
     * constructor.
     *
     * @param keyboardSensor keyboard sensor
     * @param massage massage of the board
     */
    public EndScreen(KeyboardSensor keyboardSensor, String massage) {
        super(keyboardSensor, KeyboardSensor.SPACE_KEY);
        this.massage = massage;

    }


    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(COLOR_BACKGROUND);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, massage, 32);
        d.setColor(Color.RED);
        d.drawText(200, 400 , "press space to continue", 32);
        super.doOneFrame(d);
    }
}



