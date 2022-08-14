package geometry;

/**
 * @author sivan Jhirad, ID: 209193481
 * geometry.Point
 */
public class Point implements Comparable<Point> {
    private static final int POW = 2;
    private double x;
    private double y;
    private Point startPointOriginal;

    /**
     * constructor.
     * @param x number x
     * @param y number y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculation of distance between two points.
     * @param other point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double distancePow = Math.pow(this.x - other.x , POW) +  Math.pow(this.y - other.y , POW);
        return Math.sqrt(distancePow);
    }

    /**
     * Check whether the resulting point is the same as the existing point.
     * @param other other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -2);
        return Math.abs(this.getX() - other.getX()) < epsilon  && Math.abs(this.getY() - other.getY()) < epsilon;
    }

    /**
     * Check if x values are the same.
     * @param other point
     * @return true or false
     */
    public boolean equalsX(Point other) {
        double epsilon = Math.pow(10, -2);
        return Math.abs(this.getX() - other.getX()) < epsilon;
    }

    /**
     * Check if y values are the same.
     * @param other point
     * @return true or false
     */
    public boolean equalsY(Point other) {
        double epsilon = Math.pow(10, -2);
        return Math.abs(this.getY() - other.getY()) < epsilon;
    }

    /**
     * @return x value of this point
     */
    public double getX() {
        return this.x; }

    /**
     * @return y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * The method set x of point.
     * @param newX x
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * The method set x of point.
     * @param newY y
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     *  Setting the initial value of the ball trajectory at a particular moment.
     * @param startOriginal startPointOriginal
     */
    public void setBallCenter(Point startOriginal) {
        this.startPointOriginal = startOriginal;
    }

    /**
     * @return get sprites.Ball Center - Beginning of the ball trajectory.
     */
    public Point getBallCenter() {
        return  this.startPointOriginal;
    }

    /**
     * sort.
     * @param point Object
     * @return 1,0 or -1
     */
    public int compareTo(Point point) {
        if (this.distance(this.startPointOriginal) < point.distance(startPointOriginal)) {
            return -1;
        } else if (this.distance(this.startPointOriginal) > point.distance(startPointOriginal)) {
            return 1;
        } else {
            return 0;
        }
    }
}