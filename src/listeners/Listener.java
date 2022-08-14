package listeners;

import game.GameLevel;

/**
 * @author sivan Jhirad, ID: 209193481
 * notifiersandlisteners.Listener
 */
public abstract class Listener implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingCollidable;

    /**
     * constructor.
     * @param gameLevel the game
     * @param remainingCollidable the number of collidable left in the game
     */
    public Listener(GameLevel gameLevel, Counter remainingCollidable) {
        this.gameLevel = gameLevel;
        this.remainingCollidable = remainingCollidable;
    }

    /**
     * @return  the game
     */
    public GameLevel getGame() {
        return gameLevel;
    }

    /**
     * @return the number of balls or blocks left in the game
     */
    public Counter getRemainingCollidablee() {
        return remainingCollidable;
    }

}
