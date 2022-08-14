package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * @author sivan Jhirad, ID: 209193481
 * notifiersandlisteners.HitListener
 */

public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit object is hit and that are hit should be removed from the game
     * @param hitter the sprites.Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
