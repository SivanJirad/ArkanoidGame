package gamelevels;

import game.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.LevelInformation
 */
public interface LevelInformation {

    /**
     * @return numberOfBalls count of ball in level
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return List of balls speeds
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * @return paddle width
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return the Blocks that make up this level, each block contains
     *  its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}
