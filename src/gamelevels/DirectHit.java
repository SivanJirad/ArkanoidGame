package gamelevels;

import game.Velocity;
import geometry.Point;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.DirectHit
 */
public class DirectHit implements LevelInformation {

    //ball
    private static final int NUMBERS_OF_BALLS = 1;
    private static final double SPEED_BALL = 7;
    private static final double ANGLE_SPEED_BALL = 180;
    //paddle
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 60;
    //blocks
    private static final int NUMBER_OF_BLOCKS = 1;
    private static final double BLOCK_WIDTH = 30;
    private static final double BLOCK_HEIGHT = 30;
    private static final double LEFT_UPPER_POINT_OF_BLOCK_X = (400 - 30 / 2);
    private static final double LEFT_UPPER_POINT_OF_BLOCK_Y = 130;
    //blocks color
    private static final Color COLOR = Color.RED;


    @Override
    public int numberOfBalls() {
        return NUMBERS_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE_SPEED_BALL, SPEED_BALL));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        geometry.Rectangle react = new geometry.Rectangle(new Point(LEFT_UPPER_POINT_OF_BLOCK_X ,
                LEFT_UPPER_POINT_OF_BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT);
        blocksList.add(new Block(react, COLOR));
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }
}

