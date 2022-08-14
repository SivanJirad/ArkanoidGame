package gamelevels;

import game.Velocity;
import geometry.Point;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 *  * @author sivan Jhirad, ID: 209193481
 *  * game.FinalFour
 */
public class FinalFour implements LevelInformation {
    //ball
    private static final int NUMBERS_OF_BALLS = 3;
    private static final double SPEED_BALL_ONE = 5;
    private static final double SPEED_BALL_TWO = 5;
    private static final double SPEED_BALL_THREE = 5;
    private static final double ANGLE_SPEED_BALL_ONE = 30;
    private static final double ANGLE_SPEED_BALL_TWO = 180;
    private static final double ANGLE_SPEED_BALL_THREE = 330;
    //paddle
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 60;
    //blocks
    private static final int NUMBER_OF_BLOCKS = 115;
    private static final int COUNT_LINES_BLOCKS = 7;
    private static final int COUNT_COLUMN_BLOCKS = 15;
    private static final double BLOCK_WIDTH = 51;
    private static final double BLOCK_HEIGHT = 20;
    private static final double LEFT_UPPER_POINT_OF_RIGHT_BLOCK_X = 800 - 17.5 - 51;
    private static final double LEFT_UPPER_POINT_OF_RIGHT_BLOCK_Y = 120;
    //blocks color
    private static final Color COLOR_LINE_0 = new Color(49, 152, 158);
    private static final Color COLOR_LINE_1 = new Color(84, 186, 199);
    private static final Color COLOR_LINE_2 = new Color(71, 219, 192);
    private static final Color COLOR_LINE_3 = new Color(76, 172, 169);
    private static final Color COLOR_LINE_4 = new Color(89, 45, 191);
    private static final Color COLOR_LINE_5 = new Color(121, 139, 224);
    private static final Color COLOR_LINE_6 = new Color(59, 88, 224);


    @Override
    public int numberOfBalls() {
        return NUMBERS_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE_SPEED_BALL_ONE, SPEED_BALL_ONE));
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE_SPEED_BALL_TWO, SPEED_BALL_TWO));
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE_SPEED_BALL_THREE, SPEED_BALL_THREE));

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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
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
        //lines
        for (int i = 0; i < COUNT_LINES_BLOCKS; i++) {
            //column
            for (int j = 0; j < COUNT_COLUMN_BLOCKS; j++) {
                geometry.Rectangle react = new geometry.Rectangle(new Point(LEFT_UPPER_POINT_OF_RIGHT_BLOCK_X
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
