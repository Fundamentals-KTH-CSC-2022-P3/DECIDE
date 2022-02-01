package decide.core;

import java.util.Objects;

/**
 * Represents a point in two-dimensional space.
 */
public class Point {
    public final double x;
    public final double y;

    public enum Quadrant {
        I, II, III, IV
    }

    /**
     * Creates a new point.
     *
     * @param x the x-coordinate.
     * @param y the y-coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Quadrant getQuadrant() {
        if (x >= 0 && y >= 0) {
            return Quadrant.I;
        } else if (x < 0 && y >= 0) {
            return Quadrant.II;
        } else if (x <= 0 && y < 0) {
            return Quadrant.III;
        } else if (x > 0 && y < 0) {
            return Quadrant.IV;
        } else {
            // Should be unreachable
            throw new ArithmeticException("Non-planar point");
        }
    }

    /**
     * Calculates the euclidean distance from this point to another point.
     *
     * @param to the point we should calculate the distance to.
     * @return the euclidean distance
     */
    public double distance(Point to) {
        return Math.sqrt(Math.pow(x - to.x, 2) + Math.pow(y - to.y, 2));
    }

    /**
     * Checks wether a point p fits within a circle with a given radius and
     * the origio in this point.
     *
     * If the point p is on the circle it is considered within the circle.
     *
     * @param p         Point to check
     * @param radius    Radius of the circle
     * @return
     */
    public boolean pointFitsInCircleWithRadius(Point p, double radius) {
        return distance(p) <= radius;
    }

    /**
     * Compares this object to another object to check if they are equal.
     * Two {@code Point} objects are equal if they have the same x-value and y-value.
     *
     * @param o the object to compare with.
     * @return true if the two objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    /**
     * Computes the hash code for this object.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Calculates the angle between the two lines (or sides) that are formed by connecting the endpoints p1 and vertex (to get one line)
     * and the endpoints p2 and vertex (to get another line). These two lines (or sides) have one endpoint in common namely the vertex point.
     * <p>
     * To solve this problem we create a triangle with three sides: p1-vertex, p2-vertex and p1-p2.
     * We are interested in the angle between the sides p1-vertex and p2-vertex, we can call this desirable angle for 'C'.
     * <p>
     * The length of the side p1-vertex can we denote as 'a'. Furthermore, the length of the side p2-vertex can we denote as 'b',
     * and lastly the length of the side p1-p2 can we denote as 'c'.
     * <p>
     * Now we will use the Law of Cosines: c^2 = a^2 + b^2 - 2abcos(C) to find the angle C which is the angle between
     * the sides p1-vertex and p2-vertex. By rearranging the terms in the Law of Cosines we can solve for C and get:
     * C = arccos((a^2 + b^2 - c^2) / (2ab))
     *
     * @param p1     an endpoint to create a line (or side) between itself and the vertex.
     * @param vertex the endpoint which the two lines will have in common.
     * @param p2     an endpoint to create a line (or side) between itself and the vertex.
     * @return the angle between the sides p1-vertex and p2-vertex.
     */
    public static double vertexAngle(Point p1, Point vertex, Point p2) {
        double a = p1.distance(vertex);
        double b = p2.distance(vertex);
        double c = p1.distance(p2);

        double angle = Math.acos((a * a + b * b - c * c) / (2 * a * b));

        return angle;
    }
}
