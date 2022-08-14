package geometry;

import java.util.ArrayList;
/**
 * @author sivan Jhirad, ID: 209193481
 * geometry.Rectangle
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;
    private Point bottomRight;
    private Point bottomLeft;

    /**
     * constructor - create a new rectangle with location, width and height.
     * @param upperLeft upper left point
     * @param width of rectangle
     * @param height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft =  upperLeft;
        this.width = width;
        this.height = height;
        upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * Crossroads between the trajectory of the ball and the rectangle.
     * @param line other line
     * @return  array of intersections between the line and the rectangle
     */
    private Point[] intersections(Line line) {
        Point[] arrPoints = new Point[4];
        arrPoints[0] = line.intersectionWith(this.getUpperWidth());
        arrPoints[1] = line.intersectionWith(this.getBottomWidth());
        arrPoints[2] = line.intersectionWith(this.getLeftHeight());
        arrPoints[3] = line.intersectionWith(this.getRightHeight());
        return arrPoints;
    }

    /**
     * @param line line of trajectory
     * @return List of intersection points
     */
    public ArrayList<Point> intersectionPoints(Line line) {
        Point[] arrPoints = this.intersections(line);
        ArrayList<Point> intersection = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (arrPoints[i] != null) {
                intersection.add(arrPoints[i]);
            }
        }
        return intersection;
    }

    /**
     * get width.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get height.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * get upper left point.
     * @return upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * get upper right point.
     * @return upper right point of the rectangle
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * get bottom left point.
     * @return bottom left point of the rectangle
     */
    public Point getBottomLeft() {
        return bottomLeft;
    }

    /**
     * get bottom right point.
     * @return bottom right point of the rectangle
     */
    public Point getBottomRight() {
        return bottomRight;
    }

    /**
     * get upper width.
     * @return line of upper width of the rectangle
     */
    public Line getUpperWidth() {
        return new Line(this.upperLeft, this.upperRight);
    }

    /**
     * get bottom width.
     * @return line of bottom width of the rectangle
     */
    public Line getBottomWidth() {
        return new Line(this.bottomLeft, this.bottomRight);
    }

    /**
     * get left height.
     * @return line of left height of the rectangle
     */
    public Line getLeftHeight() {
        return new Line(this.upperLeft, this.bottomLeft);
    }

    /**
     * get right height.
     * @return line of right height of the rectangle
     */
    public Line getRightHeight() {
        return new Line(this.upperRight, this.bottomRight);
    }

}
