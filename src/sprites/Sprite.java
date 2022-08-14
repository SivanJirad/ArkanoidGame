package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * @author sivan Jhirad, ID: 209193481
 * sprites.Sprite
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param surface DrawSurface
     */
    void drawOn(DrawSurface surface);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * @param gameLevel gameLevel
     */
    void addToGame(GameLevel gameLevel);
}
