package game;

import geometry.Point;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.Velocity specifies the change in position on the `x` and the `y` axes
 */
public class Velocity {
    private static final double PI = Math.PI;
    private static final int HORIZONTAL = 1;
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx  change in position on the `x`
     * @param dy  change in position on the `y`
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return change in position on the `x`
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return change in position on the `y`
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The method gets speed and angle and returns speed according to dx and dy.
     * @param angle angle
     * @param speed speed
     * @return game.Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newAngle = angle / 180;
        double angleR = PI * newAngle;
        double dx = speed * Math.sin(angleR);
        double dy = -speed * Math.cos(angleR);
        return new Velocity(dx, dy);

    }

    /**
     * The function returns the velocity angle.
     * @return angle
     */
    public double getAngle() {
        if (dx >= 0 && dy <= 0) {
            return -Math.toDegrees(Math.atan(dy / dx));
        } else if (dx >= 0 && dy >= 0) {
            return 90 + Math.toDegrees(Math.atan(dy / dx));
        } else if (dx <= 0 && dy >= 0) {
            return 180 - Math.toDegrees(Math.atan(dy / dx));
        } else  {
            return 270 + Math.toDegrees(Math.atan(dy / dx));
        }
    }

    /**
     * The method Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p point
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Change speed according to the impact of the ball.
     * @param type Horizontal or vertical
     * @return velocity after the change.
     */
    public Velocity changeVelocityHelp(int type) {
        if (type == HORIZONTAL) {
            dx = -dx;
        } else {
            dy = -dy;
        }
        return new Velocity(dx, dy);
    }
}

