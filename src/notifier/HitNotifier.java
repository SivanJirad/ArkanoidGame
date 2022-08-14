package notifier;

import game.GameLevel;
import listeners.HitListener;

/**
 * @author sivan Jhirad, ID: 209193481
 * notifiers.notifier.HitNotifier - interface indicate that objects that
 * implement it send notifications when they are being hit.
 */

public interface HitNotifier {

    /**
     * Add hit listener as a listener to hit events.
     * @param hl hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener from the list of listeners to hit events.
     * @param hl hit listener
     */
    void removeHitListener(HitListener hl);

    /**
     * remove from game, remove from sprites and from collidable.
     * @param gameLevel game
     */
    void removeFromGame(GameLevel gameLevel);
}
