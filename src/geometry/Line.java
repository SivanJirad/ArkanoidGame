package geometry;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author sivan Jhirad, ID: 209193481
 * geometry.Line
 */
public class Line {
    private static final double GARBAGE_VARIABLE = 0;
    private static final double IS_INTERSECTING = 1;
    private static final double IS_INTERSECTING_ONE = 2;
    private static final double EQUALS = 3;
    private static final double INFINITY = Double.POSITIVE_INFINITY;
    private double epsilon = Math.pow(10, -1);
    private Point start;
    private Point end;
    private Point originalStart;

    /**
     * helped method  to the constructor.
     *
     * @param startGet start point
     * @param endGet   end point
     */
    private void helpTOConstructor(Point startGet, Point endGet) {
        this.start = startGet;
        this.end = endGet;
    }

    /**
     * constructor 1.
     *
     * @param start point
     * @param end   point
     */
    public Line(Point start, Point end) {
        this.originalStart = start;
        if (start.getX() == end.getX()) { //if the line from type: x = "parameter"
            if (start.getY() <= end.getY()) {
                helpTOConstructor(start, end);
            } else {
                helpTOConstructor(end, start);
            }
        } else if (start.getX() < end.getX()) { //The starting point is the point at which x is smaller
            helpTOConstructor(start, end);
        } else {
            helpTOConstructor(end, start);
        }
    }

    /**
     * constructor 2.
     *
     * @param x1 parameter x of point
     * @param y1 parameter y of point
     * @param x2 parameter x of point
     * @param y2 parameter y of point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startGet = new Point(x1, y1);
        this.originalStart = startGet;
        Point endGet = new Point(x2, y2);
        if (x1 == x2) { //// if the line from type: x = "parameter"
            if (y1 <= y2) {
                helpTOConstructor(startGet, endGet);
            } else {
                helpTOConstructor(endGet, startGet);
            }
        } else if (x1 < x2) { //The starting point is the point at which x is smaller
            helpTOConstructor(startGet, endGet);
        } else {
            helpTOConstructor(endGet, startGet);
        }
    }

    /**
     * The method calculates the length of the line.
     *
     * @return length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * The method calculates the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((end.getX() + start.getX()) / 2, (end.getY() + start.getY()) / 2);
    }

    /**
     * The method returns the start point of the line.
     *
     * @return the start point
     */
    public Point start() {
        return start;
    }

    /**
     * The method returns the end point of the line.
     *
     * @return the end point
     */
    public Point end() {
        return end;
    }

    /**
     * The method checks if the y value of a point is on the line.
     *
     * @param line line
     * @param y    parameter
     * @return true if y in range, otherwise false.
     */
    private boolean isInRangeOfY(Line line, double y) {
        double min = Math.min(line.start.getY(), line.end.getY());
        double max = Math.max(line.start.getY(), line.end.getY());
        return min <= y + epsilon && y - epsilon <= max;
    }

    /**
     * The method checks if the x value of a point is on the line.
     *
     * @param line line
     * @param x    parameter
     * @return true if x in range, otherwise false.
     */
    public boolean isInRangeOfX(Line line, double x) {
        return line.start.getX() <= x + epsilon && x - epsilon <= line.end.getX();
    }

    /**
     * The method returns an array of data: status[0]= The ratio of two straight lines,
     * If the lines intersect: status[1]= x of point, status[2]=y of point, else status[1]=status[2]=GARBAGE_VARIABLE.
     *
     * @param type lines intersect, intersection point, if the lines are the same.
     * @param x    intersection x of point or GARBAGE_VARIABLE.
     * @param y    intersection y of point or GARBAGE_VARIABLE.
     * @return status
     */
    private double[] arrData(double type, double x, double y) {
        double[] status = new double[3];
        status[0] = type;
        status[1] = x;
        status[2] = y;
        return status;
    }

    /**
     * The method returns the slope of the line.
     *
     * @param line line
     * @return slop of line
     */
    private double calculateSlope(Line line) {
        if (line.start.getX() == line.end.getX()) {
            return INFINITY;
        } else {
            return (line.end.getY() - line.start.getY()) / (line.end.getX() - line.start.getX());
        }
    }

    /**
     * The method examines the point of intersection between two lines (with slop).
     *
     * @param other     line
     * @param slopThis  slop of this line
     * @param slopOther slop of other line
     * @return @return arr so that if the lines are intersection: arr[0] = IS_INTERSECTING_ONE, arr[1] = x, arr[y] = y,
     * otherwise null
     */
    private double[] slopExists(Line other, double slopThis, double slopOther) {
        double freeVariableOfThis = this.start.getY() - slopThis * this.start.getX();
        double freeVariableOfOther = other.start.getY() - slopOther * other.start.getX();
        // if slops are different
        if (slopThis != slopOther) {
            double x = (freeVariableOfOther - freeVariableOfThis) / (slopThis - slopOther);
            double y = slopThis * x + freeVariableOfThis;
            return slopDifferent(other, x, y);
            //if slops and free variable same
        } else if (freeVariableOfThis == freeVariableOfOther) {
            return slopSame(other);
        }
        // if lines are parallel.
        return null;
    }

    /**
     * The method checks if the point of intersection of the lines is in the range of the two sections.
     *
     * @param other line
     * @param x     x at the point of intersection
     * @param y     y at the point of intersection
     * @return arr so that if the lines are intersection: arr[0] = IS_INTERSECTING_ONE, arr[1] = x, arr[y] = y,
     * otherwise null
     */
    private double[] slopDifferent(Line other, double x, double y) {
        if (isInRangeOfX(this, x) && isInRangeOfX(other, x) && isInRangeOfY(this, y)
                && isInRangeOfY(other, y)) {
            return arrData(IS_INTERSECTING_ONE, x, y);
        }
        return null;
    }

    /**
     * The method is called when the slops lines are equal and checks whether the lines are intersection,
     * contained, same or without a point of intersection.
     *
     * @param other line
     * @return arr with data if about the ratio of two straight lines or null.
     */
    private double[] slopSame(Line other) {
        if (this.end.equals(other.start)) {   //continue
            return arrData(IS_INTERSECTING_ONE, this.end.getX(), this.end.getY());
        } else if (this.start.equals(other.end)) {
            return arrData(IS_INTERSECTING_ONE, this.start.getX(), this.start.getY());
        } else if (this.start.equals(other.start) && this.end.equals(other.end)) { //same line
            return arrData(EQUALS, GARBAGE_VARIABLE, GARBAGE_VARIABLE);
        } else if (this.start.getX() <= other.start.getX() && other.start.getX() <= this.end.getX()) {
            //more from one point
            return arrData(IS_INTERSECTING, GARBAGE_VARIABLE, GARBAGE_VARIABLE);
        } else if (other.start.getX() <= this.start.getX() && this.start.getX() <= other.end.getX()) {
            return arrData(IS_INTERSECTING, GARBAGE_VARIABLE, GARBAGE_VARIABLE);
        }
        return null;
    }

    /**
     * The method is called when the the slope of the lines is infinite.
     *
     * @param other line
     * @return arr with data if about the ratio of two straight lines or null.
     */
    private double[] slopeDoesNotExist(Line other) {
        boolean isEqualsOfTHis = this.start.equals(this.end);
        boolean isEqualsOfOther = other.start.equals(other.end);
        if (isEqualsOfTHis || isEqualsOfOther) {
            if (isEqualsOfTHis && isEqualsOfOther && this.start.equals(other.start)) { // 2 points
                return arrData(IS_INTERSECTING_ONE, this.start.getX(), this.start.getY());
            } else if (isEqualsOfTHis && isInRangeOfY(other, this.start.getY())) { //point and line
                return arrData(IS_INTERSECTING_ONE, this.start.getX(), this.start.getY());
            } else if (isEqualsOfOther && isInRangeOfY(this, other.start.getY())) {
                return arrData(IS_INTERSECTING_ONE, other.start.getX(), other.start.getY());
            }
        } else if (this.end.equals(other.start)) { // //continue
            return arrData(IS_INTERSECTING_ONE, this.end.getX(), this.end.getY());
        } else if (this.start.equals(other.end)) {
            return arrData(IS_INTERSECTING_ONE, this.start.getX(), this.start.getY());
        } else if (this.start.equals(other.start) && this.end.equals(other.end)) { //same line
            return arrData(EQUALS, GARBAGE_VARIABLE, GARBAGE_VARIABLE);
        } else if (isInRangeOfY(this, other.start.getY()) || isInRangeOfY(other, this.start.getY())) {
            //more one point
            return arrData(IS_INTERSECTING, GARBAGE_VARIABLE, GARBAGE_VARIABLE);
        }
        return null;
    }

    /**
     * The method is called when the the slope of the lines is infinite.
     *
     * @param lineWithoutSlop line without slop
     * @param slop            slop of other line
     * @param line            line with slop
     * @return arr with data if about the ratio of two straight lines or null.
     */
    private double[] slopOneInfinity(Line lineWithoutSlop, double slop, Line line) {
        double freeVariableOfThis = line.start.getY() - slop * line.start.getX();
        double x = lineWithoutSlop.start.getX();
        double y;
        if (line.start.getX() <= x && x <= line.end.getX()) {
            y = slop * x + freeVariableOfThis;
            if (isInRangeOfY(line, y) && isInRangeOfY(lineWithoutSlop, y)) {
                return arrData(IS_INTERSECTING_ONE, x, y);
            }
        }
        return null;
    }

    /**
     * The method returns the ratio between two lines and if they intersect returns the intersection point.
     *
     * @param other line
     * @return arr with data if about the ratio of two straight lines or null.
     */
    private double[] status(Line other) {
        double slopThis = calculateSlope(this);
        double slopOther = calculateSlope(other);
        //If both slopes exist
        if (slopThis != INFINITY && slopOther != INFINITY) {
            return slopExists(other, slopThis, slopOther);
        } else if (slopThis == INFINITY && slopOther == INFINITY) {
            if (this.start.getX() == other.start.getX()) {
                return slopeDoesNotExist(other);
            }
        } else {
            if (slopThis == INFINITY) {
                return slopOneInfinity(this, slopOther, other);
            } else {
                return slopOneInfinity(other, slopThis, this);
            }
        }
        return null;
    }

    /**
     * The method checks if the lines intersect at least once.
     *
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double[] status = status(other);
        return status != null;
    }

    /**
     * The method checks if the lines intersect once.
     *
     * @param other line
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double[] status = status(other);
        if (status == null || status[0] != IS_INTERSECTING_ONE) {
            return null;
        }
        return new Point(status[1], status[2]);
    }

    /**
     * The method checks if two lines are equal.
     *
     * @param other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        double[] status = status(other);
        return status != null && status[0] == EQUALS;
    }

    /**
     * The method checks if the line intersect the sides of the rectangle.
     * @param rect rectangle
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> intersections = rect.intersectionPoints(this);
        for (Point intersection: intersections) {
            intersection.setBallCenter(this.originalStart);
        }
        if (intersections.size() != 0) {
            Collections.sort(intersections);
            return intersections.get(0);
        }
        return null;
    }

}
