package gamelevels;

import game.GameLevel;
import sprites.Sprite;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.LevelBackground
 */
public  abstract class LevelBackground implements Sprite {

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);

    }
}
