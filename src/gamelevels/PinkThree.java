package gamelevels;

import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.PinkThree
 */
public class PinkThree implements LevelInformation {

    private static final double TOP_AND_BOTTOM_BLOCK_WIDTH = 800;
    private static final double TOP_AND_BOTTOM_BLOCK_HEIGHT = 17.5;
    //ball
    private static final int NUMBERS_OF_BALLS = 2;
    private static final double SPEED_BALL_ONE = 7;
    private static final double SPEED_BALL_TWO = 4;
    private static final double ANGLE_SPEED_BALL_ONE = 30;
    private static final double ANGLE_SPEED_BALL_TWO = 330;
    //paddle
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 60;
    //blocks
    private static final int NUMBER_OF_BLOCKS = 57;
    private static final int COUNT_LINES_BLOCKS = 6;
    private static final int COUNT_COLUMN_BLOCKS = 12;
    private static final double BLOCK_WIDTH = 50;
    private static final double BLOCK_HEIGHT = 20;
    private static final double LEFT_UPPER_POINT_OF_RIGHT_BLOCK_X = TOP_AND_BOTTOM_BLOCK_WIDTH
            - TOP_AND_BOTTOM_BLOCK_HEIGHT - BLOCK_WIDTH;
    private static final double LEFT_UPPER_POINT_OF_RIGHT_BLOCK_Y = 100;
    //blocks color
    private static final Color COLOR_LINE_0 = new Color(222, 166, 214);
    private static final Color COLOR_LINE_1 = new Color(238, 130, 238);
    private static final Color COLOR_LINE_2 = new Color(255, 0, 255);
    private static final Color COLOR_LINE_3 = new Color(186, 85, 211);
    private static final Color COLOR_LINE_4 = new Color(147, 112, 219);
    private static final Color COLOR_LINE_5 = new Color(148, 0, 211);


    @Override
    public int numberOfBalls() {
        return NUMBERS_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE_SPEED_BALL_ONE, SPEED_BALL_ONE));
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE_SPEED_BALL_TWO, SPEED_BALL_TWO));
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
        return "Pink three";
    }

    @Override
    public Sprite getBackground() {
        return new PinkThreeBackground();
    }

    @Override
    public List<Block> blocks() {

        Color[] color = new Color[6];
        color[0] = COLOR_LINE_0;
        color[1] = COLOR_LINE_1;
        color[2] = COLOR_LINE_2;
        color[3] = COLOR_LINE_3;
        color[4] = COLOR_LINE_4;
        color[5] = COLOR_LINE_5;
        List<Block> blocksList = new ArrayList<>();
        //lines
        for (int i = 0; i < COUNT_LINES_BLOCKS; i++) {
            //column
            for (int j = 0; j < COUNT_COLUMN_BLOCKS - i; j++) {
                geometry.Rectangle react = new Rectangle(new Point(LEFT_UPPER_POINT_OF_RIGHT_BLOCK_X
                        - (BLOCK_WIDTH * j),
                        LEFT_UPPER_POINT_OF_RIGHT_BLOCK_Y + (BLOCK_HEIGHT * i)), BLOCK_WIDTH, BLOCK_HEIGHT);
                blocksList.add(new Block(react, color[i]));
            }
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }
}
