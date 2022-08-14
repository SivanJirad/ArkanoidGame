package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collisionobject.Collidable;
import gamelevels.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.Counter;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.Game
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private GUI gui;
    private Counter counterBlocks;
    private Counter counterBall;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    //frame
    private static final Point LEFT_UPPER_POINT_BLOCK = new Point(0, 20);
    private static final Point RIGHT_UPPER_POINT_BLOCK = new Point(782.5, 0);
    private static final double TOP_AND_BOTTOM_BLOCK_WIDTH = 800;
    private static final double TOP_AND_BOTTOM_BLOCK_HEIGHT = 17.5;
    private static final double RIGHT_AND_LEFT_BLOCK_WIDTH = 17.5;
    private static final double RIGHT_AND_LEFT_BLOCK_HEIGHT = 800;
    private static final int COUNT_BLOCKS_OF_FRAME = 3;
    //death region
    private static final Point LEFT_DEATH_POINT_BLOCK = new Point(0, 600);
    private static final double LEFT_DEATH_BLOCK_WIDTH = 800;
    private static final double LEFT_DEATH_BLOCK_HEIGHT = 10;
    //ball
    private static final int RADIUS_BALL = 6;
    private static final java.awt.Color COLOR_BALL = new Color(147, 4, 104);
    private static final Point CENTER_BALL = new Point(400, 543);

    //paddle
    private static final double PADDLE_HEIGHT = 18;
    //scoreIndicator
    private static final int LEFT_UPPER_POINT_SCORE_X = 0;
    private static final int LEFT_UPPER_POINT_SCORE_Y = 0;
    private static final int SCORE_WIDTH = 800;
    private static final int SCORE_HEIGHT = 20;


    /**
     * constructor - run of the current level.
     * @param gui the board of the game
     * @param levelInformation current level
     * @param keyboardSensor Keyboard sensor
     * @param animationRunner animation runner
     * @param score score of the game
     */
    public GameLevel(GUI gui , LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.gui = gui;
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.score = score;

        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = new Counter(0);
        this.counterBall = new Counter(0);
    }

    /**
     * @return game
     */
    public GameLevel getGame() {
        return this;
    }

    /**
     * add the given collidable to the environment.
     * @param c collisionobject.Collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * remove the given collidable from the environment.
     * @param c collisionobject.Collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove the given sprite from the sprite collection.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * add the given sprite to the sprite collection.
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * create block of frame.
     */
    public void  createFrame() {
        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        Block[] blocksFrame = new Block[COUNT_BLOCKS_OF_FRAME];
        blocksFrame[0] = new Block(new Rectangle(LEFT_UPPER_POINT_BLOCK, TOP_AND_BOTTOM_BLOCK_WIDTH,
                TOP_AND_BOTTOM_BLOCK_HEIGHT), Color.BLACK);
        blocksFrame[1] = new Block(new Rectangle(LEFT_UPPER_POINT_BLOCK, RIGHT_AND_LEFT_BLOCK_WIDTH,
                RIGHT_AND_LEFT_BLOCK_HEIGHT), Color.BLACK);
        blocksFrame[2] = new Block(new Rectangle(RIGHT_UPPER_POINT_BLOCK, RIGHT_AND_LEFT_BLOCK_WIDTH,
                RIGHT_AND_LEFT_BLOCK_HEIGHT), Color.BLACK);
        for (int i = 0; i < COUNT_BLOCKS_OF_FRAME; i++) {
            blocksFrame[i].addToGame(this);
            blocksFrame[i].createHitListenerList();
        }
    }

    /**
     * create "death block".
     * @return "death block"
     */
    public Block createDeathRegion() {
        Block deathRegion = new Block(new Rectangle(LEFT_DEATH_POINT_BLOCK, LEFT_DEATH_BLOCK_WIDTH,
                LEFT_DEATH_BLOCK_HEIGHT), Color.GRAY);
        deathRegion.addToGame(this);
        return deathRegion;
    }

    /**
     * create 2 balls.
     */
    private void createBall() {
        BallRemover ballRemover = new BallRemover(this, counterBall);
        List<Ball> balls = new ArrayList();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(CENTER_BALL, RADIUS_BALL, COLOR_BALL);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            balls.add(ball);
        }

        Block deathRegion = createDeathRegion();
        for (Ball ball: balls) {
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            ball.createHitListenerList();
            this.counterBall.increase(1);
            ball.addHitListener(ballRemover);
            deathRegion.createHitListenerList();
            deathRegion.addHitListener(ballRemover);
        }
    }

    /**
     * create paddle.
     */
    private void createPaddle() {
        Point leftUpperPointPaddle = new Point(400 - levelInformation.paddleWidth() / 2, 550);
        Paddle paddle = new Paddle(new Rectangle(leftUpperPointPaddle, levelInformation.paddleWidth(), PADDLE_HEIGHT),
                keyboard);
        paddle.setVelocity(levelInformation.paddleSpeed(), 0);
        paddle.addToGame(this);
    }


    /**
     * create blocks of game.
     */
    private void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        for (Block block: levelInformation.blocks()) {
            block.createHitListenerList();
            this.counterBlocks.increase(1);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
        }
    }

    /**
     * create background.
     */
    private void createBackGround() {
        Sprite backGround =  levelInformation.getBackground();
        backGround.addToGame(this);
    }

    /**
     * create score indicator.
     */
    private void createScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Rectangle(new Point(LEFT_UPPER_POINT_SCORE_X,
                LEFT_UPPER_POINT_SCORE_Y), SCORE_WIDTH, SCORE_HEIGHT), Color.PINK, score);
        scoreIndicator.setLevelName(levelInformation.levelName());
        scoreIndicator.addToGame(this);
    }

    /**
     * initialize the game.
     */
    public void initialize() {
        createBackGround();
        createFrame();
        createScoreIndicator();
        createBall();
        createPaddle();
        createBlocks();
    }


    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    // (KeyboardSensor keyboardSensor, Animation animation) {

    @Override
    public void doOneFrame(DrawSurface d) {
        this.running = counterBall.getValue() != 0 && counterBlocks.getValue() != 0;
        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();
        if (this.keyboard != null && this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    /**
     * Adding 100 points if player win and last run of the game.
     */
    public void endLevel() {
        if (counterBlocks.getValue() == 0) {
            score.increase(100);
        }
        DrawSurface d = gui.getDrawSurface();
        this.doOneFrame(d);
        gui.show(d);
        }

    /**
     * @return count of balls in the level
     */
    public int getCountOfBalls() {
        return counterBall.getValue();
    }

    /**
     * @return count of blocks in the level
     */
    public int getCountOfBlock() {
        return counterBlocks.getValue();
    }

    /**
     *
     * @return sprites of level.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
