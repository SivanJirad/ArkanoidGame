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
 * game.WideEasy
 */
public class WideEasy implements LevelInformation {

    //ball
    private static final int NUMBERS_OF_BALLS = 10;
    private static final double ANGLE_SPEED_BALL_RIGHT = 30;
    private static final double ANGLE_SPEED_BALL_LEFT = 330;

    //paddle
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 600;

    //blocks
    private static final int NUMBER_OF_BLOCKS = 15;
    private static final double BLOCK_WIDTH = 51;
    private static final double BLOCK_HEIGHT = 20;
    private static final double LEFT_UPPER_POINT_OF_LEFT_BLOCK_X = 17.5;
    private static final double LEFT_UPPER_POINT_OF_LEFT_BLOCK_Y = 300;
    //blocks color
    private static final Color COLOR_LINE_0 = new Color(222, 213, 67);
    private static final Color COLOR_LINE_1 = new Color(189, 152, 12);
    private static final Color COLOR_LINE_2 = new Color(255, 248, 128);;
    private static final Color COLOR_LINE_3 = new Color(170, 163, 145);
    private static final Color COLOR_LINE_4 = new Color(216, 239, 14);
    private static final Color COLOR_LINE_5 = new Color(252, 246, 13);
    private static final Color COLOR_LINE_6 = new Color(139, 135, 5);



    @Override
    public int numberOfBalls() {
        return NUMBERS_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < NUMBERS_OF_BALLS / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(ANGLE_SPEED_BALL_LEFT - 10 * i, 7));
            velocities.add(Velocity.fromAngleAndSpeed(ANGLE_SPEED_BALL_RIGHT + 10 * i, 7));
        }
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
            return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {


        Color[] color = new Color[7];
        color[0] = COLOR_LINE_0;
        color[1] = COLOR_LINE_1;
        color[2] = COLOR_LINE_2;
        color[3] = COLOR_LINE_3;
        color[4] = COLOR_LINE_4;
        color[5] = COLOR_LINE_5;
        color[6] = COLOR_LINE_6;
        List<Block> blocksList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
            geometry.Rectangle react = new geometry.Rectangle(new Point(LEFT_UPPER_POINT_OF_LEFT_BLOCK_X
                    + (BLOCK_WIDTH * i),
                    LEFT_UPPER_POINT_OF_LEFT_BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT);
            int colorInArr;
            if (i % 2 == 0) {
                if (i <= 6) {
                    colorInArr = i / 2;
                } else {
                    colorInArr = (i - 2) / 2;
                }
            } else {
                colorInArr = (i - 1) / 2;
            }

            blocksList.add(new Block(react, color[colorInArr]));
        }


        return blocksList;


    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }
}

