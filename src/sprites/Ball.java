package sprites;

import biuoop.DrawSurface;
import collisionobject.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import game.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import notifier.Notifier;

import java.awt.Color;

/**
 * @author sivan Jhirad, ID: 209193481
 * sprites.Ball
 */
public class Ball extends Notifier implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private CollisionInfo optionalCollisionPoint;

    /**
     * constructor1.
     * @param center center point
     * @param r radius
     * @param color color of ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(Math.abs(center.getX()), Math.abs(center.getY()));
        this.r = Math.abs(r);
        this.color = color;
    }

    /**
     * constructor2.
     * @param x x of center point
     * @param y y of center point
     * @param r radius
     * @param color color of ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(Math.abs(x), Math.abs(y));
        this.r = r;
        this.color = color;
    }

    /**
     * @return x of center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return y of center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return radius of ball
     */
    public int getSize() {
        return r;
    }

    /**
     * set new radius of ball.
     * @param size radius
     */
    public void setSize(int size) {
        this.r = size;
    }

    /**
     * @return color of ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * The method initializes the speed of the ball.
     * @param v velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * The method initializes the speed of the ball.
     * @param dx  change in position on the `x`
     * @param dy  change in position on the `y`
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The method changes the speed of the ball depending on its position.
     * @param type Horizontal or vertical speed change
     */
    private void changeVelocity(int type) {
        this.setVelocity(this.velocity.changeVelocityHelp(type));
    }

    /**
     * set game environment.
     * @param environment environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * get game environment.
     * @return game.Game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Creating the trajectory of the ball,
     * starting point - center of the ball before the movement.
     * end point - end point (in the direction of movement, straight) of the ball after the stroke
     * @param applyToPoint point center of the ball after the movement.
     * @return trajectory line
     */
    private Line trajectory(Point applyToPoint) {
        double angle = this.getVelocity().getAngle();
        Velocity newV = Velocity.fromAngleAndSpeed(angle, this.r);
        Point applyToPointAfterRadius = newV.applyToPoint(applyToPoint);
        return new Line(this.center, applyToPointAfterRadius);
    }

    /**
     * This method will be in charge of adding the ball to the game.
     * @param gameLevel game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * If the center of the ball is on collided, the method changes the center of the ball to the appropriate range.
     */
    public void changeCenterIfNeed() {
        Rectangle collisionBlock = this.optionalCollisionPoint.collisionObject().getCollisionRectangle();
        Point collisionPoint = this.optionalCollisionPoint.collisionPoint();
        //Upper and bottom rib
        if (collisionPoint.equalsY(collisionBlock.getUpperLeft())) {
            this.center.setY(collisionBlock.getUpperLeft().getY() - this.getSize());
        } else if (collisionPoint.equalsY(collisionBlock.getBottomLeft())) {
            this.center.setY(collisionBlock.getBottomLeft().getY() + this.getSize());
        }
        //Left and right rib
        if (collisionPoint.equalsX(collisionBlock.getBottomLeft())) {
            this.center.setX(collisionBlock.getBottomLeft().getX() - this.getSize());
        } else if (collisionPoint.equalsX(collisionBlock.getBottomRight())) {
            this.center.setX(collisionBlock.getBottomRight().getX() + this.getSize());
        }
    }

    /**
     * The function checks whether the ball is out of the collidable area and calls for a function that
     * will change its position.
     * */
    public void moveOneStep() {
        if (this.getVelocity() != null) {
            // The center point of the ball after the speed
            Point applyToPoint = this.getVelocity().applyToPoint(this.center);
            // Check if the ball hits objects, If the tip of the ball is in the block area
            this.optionalCollisionPoint = this.gameEnvironment.getClosestCollision(trajectory(applyToPoint));
            // If the collision occurs - changing the direction of the ball speed and changing the position of the hit
            if (this.optionalCollisionPoint != null) {
                this.velocity = this.optionalCollisionPoint.collisionObject().
                        hit(this, this.optionalCollisionPoint.collisionPoint(), this.velocity);
            }
            this.center = applyToPoint;
            if (this.optionalCollisionPoint != null) {
                changeCenterIfNeed();
            }
        }
    }

    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    }

