package game;

import collisionobject.Collidable;
import collisionobject.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.Game Environment
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidableObjects;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidableObjects = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     * @param c collidable
     */
    public void addCollidable(Collidable c) {
        this.collidableObjects.add(c);
    }

    /**
     *  remove the given collidable from the environment.
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidableObjects.remove(c);
    }

    /**
     * @return Array list of collidable objects
     */
    public ArrayList<Collidable> getCollidableObjects() {
        return collidableObjects;
    }

    /**
     * sprites.Ball trajectory - starts at the center of the ball and ends at the end of the ball after sliding.
     * @param trajectory line that start in
     * @return closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<CollisionInfo> intersectionsArray = new ArrayList<>();
        ArrayList<Collidable> tempCollidableObjects = new ArrayList<>(collidableObjects);
        for (Collidable object : tempCollidableObjects) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            if (intersection != null) {
                CollisionInfo newIntersection = new CollisionInfo(object, intersection);
                intersectionsArray.add(newIntersection);
            }
        }
        if (intersectionsArray.size() != 0) {
            Collections.sort(intersectionsArray);

            return intersectionsArray.get(0);
        }
        return null;
    }
}