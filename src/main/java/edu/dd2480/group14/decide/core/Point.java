package edu.dd2480.group14.decide.core;

/**
 * Represents a point in two-dimensional space.
 */
public class Point {
    public double x;
    public double y;

    /**
     * Creates a new point.
     * @param x the x-coordinate.
     * @param y the y-coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the euclidean distance from this point to another point.
     * @param to the point we should calculate the distance to.
     * @return the euclidean distance
     */
    public double distance(Point to) {
        return Math.sqrt(Math.pow(x - to.x, 2) + Math.pow(y - to.y, 2));
    }
}
