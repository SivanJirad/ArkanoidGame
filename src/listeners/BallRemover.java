package listeners;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * @author sivan Jhirad, ID: 209193481
 * notifiersandlisteners.BallRemover.
 */

public class BallRemover extends Listener {

    /**
     * constructor.
     * @param gameLevel the game
     * @param remainingBalls The number of balls left in the game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        super(gameLevel, remainingBalls);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.getGame());
        hitter.removeHitListener(this);
        this.getRemainingCollidablee().decrease(1);
    }
}

