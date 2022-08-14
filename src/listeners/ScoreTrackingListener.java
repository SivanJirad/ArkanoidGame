package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * @author sivan Jhirad, ID: 209193481
 * listeners.ScoreTrackingListener
 */

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }


}