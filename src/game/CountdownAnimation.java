package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author sivan Jhirad, ID: 209193481
 * The class will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen, before it is replaced with the next one.
 * game.CountdownAnimation
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int countNow;

    /**
     * constructor.
     * @param numOfSeconds num sf seconds of the count
     * @param countFrom The number from which the count begins
     * @param gameScreen sprites of the level
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.countNow = countFrom;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);

        Sleeper sleeper = new Sleeper();
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countNow--), 32);
        if (countNow < 2) {
            sleeper.sleepFor((long) ((numOfSeconds / countFrom) * 1000));
        }
    }

    @Override
    public boolean shouldStop() {
        if (countNow >= 0) {
            return false;
        }
        return true;
    }
}