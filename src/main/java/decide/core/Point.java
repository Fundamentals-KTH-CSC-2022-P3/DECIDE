package decide.core;

/**
 * Represents a point in two-dimensional space.
 */
public class Point {
    public final double x;
    public final double y;
    public final int quadrant;

    /**
     * Creates a new point.
     * @param x the x-coordinate.
     * @param y the y-coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;

        if (x >= 0 && y >= 0) {
            quadrant = 0;
        } else if (x < 0 && y >= 0) {
            quadrant = 1;
        } else if (x < 0 && y < 0) {
            quadrant = 2;
        } else {
            // x < 0 && y >= 0, but we need to end in a catch-all "else" for quadrant to be final.
            quadrant = 3;
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
