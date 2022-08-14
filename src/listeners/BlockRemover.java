package listeners;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * @author sivan Jhirad, ID: 209193481
 * notifiersandlisteners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */

public class BlockRemover extends Listener {

    /**
     * constructor.
     * @param gameLevel the game
     * @param remainingBlocks The number of blocks left in the game
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        super(gameLevel, remainingBlocks);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.getGame());
        beingHit.removeHitListener(this);
        this.getRemainingCollidablee().decrease(1);
    }
}