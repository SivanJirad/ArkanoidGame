package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisionobject.Collidable;
import game.GameLevel;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
/**
 * @author sivan Jhirad, ID: 209193481
 * sprites.Paddle
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private Velocity velocity;
    private final double epsilon = Math.pow(10, -1);
    private static final int REGION_TWO = 2;
    private static final int REGION_THREE = 3;
    private static final int REGION_FOUR = 4;
    private static final int REGION_FIVE = 5;
    private static final double REGION_ONE_ANGLE = 300;
    private static final double REGION_TWO_ANGLE = 330;
    private static final double REGION_FOUR_ANGLE = 30;
    private static final double REGION_FIVE_ANGLE = 60;

    /**
     * constructor.
     * @param paddle rectangle
     * @param keyboard keyboard
     */
    public Paddle(Rectangle paddle, biuoop.KeyboardSensor keyboard) {
        this.paddle = paddle;
        this.keyboard = keyboard;
    }



    /**
     * set velocity.
     * @param dx change in position on the `x`
     * @param dy change in position on the `y`
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     *move left.
     */
    public void moveLeft() {
        paddle = new Rectangle(new Point(paddle.getUpperLeft().getX() - velocity.getDx(),
                paddle.getUpperLeft().getY()), paddle.getWidth(), paddle.getHeight());
    }

    /**
     * move right.
     */
    public void moveRight() {
        paddle = new Rectangle(new Point(paddle.getUpperLeft().getX() + velocity.getDx(),
                paddle.getUpperLeft().getY()), paddle.getWidth(), paddle.getHeight());

    }

    /**
     * A function that prevents the paddle from going beyond the screen.
     */
    public void fixUpperLeft() {
        if (this.paddle.getUpperLeft().getX() < 15) {
            this.paddle.getUpperLeft().setX(15);
        } else if (this.paddle.getUpperLeft().getX() > 785 - this.paddle.getWidth()) {
            this.paddle.getUpperLeft().setX(785 - this.paddle.getWidth());
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        fixUpperLeft();
    }

    /**
     * The method draw the paddle on the given DrawSurface.
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * @return get collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return paddle;
    }

    /**
     *Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter ball that hit
     * @param collisionPoint collisionPoint
     * @param currentVelocity currentVelocity
     * @return the new velocity expected after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        if (collisionPoint.equalsY(this.paddle.getUpperLeft())
                && this.paddle.getUpperWidth().isInRangeOfX(this.paddle.getUpperWidth(), collisionPoint.getX())) {
            double leftX = this.paddle.getUpperLeft().getX();
            double smallWidth = this.paddle.getWidth() / REGION_FIVE;
            double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
            //Areas
            if (leftX - epsilon <= collisionPoint.getX() && collisionPoint.getX() <= leftX + smallWidth) {
                currentVelocity = Velocity.fromAngleAndSpeed(REGION_ONE_ANGLE, speed);
            } else if (leftX + smallWidth < collisionPoint.getX() && collisionPoint.getX() <= leftX + REGION_TWO
                    * smallWidth) {
                currentVelocity = Velocity.fromAngleAndSpeed(REGION_TWO_ANGLE, speed);
            } else if (leftX + REGION_TWO * smallWidth < collisionPoint.getX() && collisionPoint.getX()
                    <= leftX +  REGION_THREE * smallWidth) {
                currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            } else if (leftX + REGION_THREE * smallWidth < collisionPoint.getX() && collisionPoint.getX() <= leftX
                    + REGION_FOUR * smallWidth) {
                currentVelocity = Velocity.fromAngleAndSpeed(REGION_FOUR_ANGLE, speed);
            } else if (leftX + REGION_FOUR * smallWidth < collisionPoint.getX() && collisionPoint.getX() <= leftX
                    + REGION_FIVE * smallWidth + epsilon) {
                currentVelocity = Velocity.fromAngleAndSpeed(REGION_FIVE_ANGLE, speed);
            }
        } else if (collisionPoint.equalsX(this.paddle.getUpperLeft())
                || collisionPoint.equalsX(this.paddle.getUpperRight())) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (collisionPoint.equalsY(this.paddle.getUpperLeft())) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     * @param gameLevel game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }
}