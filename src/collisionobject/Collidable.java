package collisionobject;

import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * @author sivan Jhirad, ID: 209193481
 * collisionobject.Collidable
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint collisionPoint
     * @param currentVelocity currentVelocity
     * @param hitter ball
     * @return is the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
