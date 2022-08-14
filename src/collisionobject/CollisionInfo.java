package collisionobject;

import geometry.Point;

/**
 * @author sivan Jhirad, ID: 209193481
 * collisionobject.CollisionInfo
 */
public class CollisionInfo implements Comparable<CollisionInfo> {
    private Collidable object;
    private Point collisionPoint;

    /**
     * constructor.
     * @param object collisionobject.Collidable object
     * @param collisionPoint collisionPoint
     */
    public CollisionInfo(Collidable object, Point collisionPoint) {
        this.object = object;
        this.collisionPoint = collisionPoint;
    }

    /**
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return object;
    }

    /**
     * sort.
     * @param intersection collidable object
     * @return 1 or -1
     */
    public int compareTo(CollisionInfo intersection) {
        if (this.collisionPoint.distance(this.collisionPoint.getBallCenter())
                < intersection.collisionPoint.distance(this.collisionPoint.getBallCenter())) {
            return -1;
        } else if (this.collisionPoint.distance(this.collisionPoint.getBallCenter())
                > intersection.collisionPoint.distance(this.collisionPoint.getBallCenter())) {
            return 1;
        } else {
            return 0;
        }
    }
}
