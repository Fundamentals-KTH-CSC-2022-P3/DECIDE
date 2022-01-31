package decide.core;

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
     * @param to the point we should calculate the distance to.
     * @return the euclidean distance
     */
    public double distance(Point to) {
        return Math.sqrt(Math.pow(x - to.x, 2) + Math.pow(y - to.y, 2));
    }
}
