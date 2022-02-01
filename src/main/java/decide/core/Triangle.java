package decide.core;

/**
 * Contains methods that are relevant for triangles.
 */
public class Triangle {

    /**
     * Calculates the area of a triangle.
     * @param p1 - one vertex of the triangle.
     * @param p2 - one vertex of the triangle.
     * @param p3 - one vertex of the triangle.
     * @return the area.
     */
    public static double area(Point p1, Point p2, Point p3) {
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
            throw new IllegalArgumentException("This is not a triangle! The 3 vertices are not allowed to coincide");

        // We will use Heron's formula to calculate the area of the triangle.
        // This formula only requires the side lengths of the triangle which we can easily retrieve:
        double a = p1.distance(p2);
        double b = p1.distance(p3);
        double c = p2.distance(p3);

        // Heron's Formula to get the area:
        double area = 0.25 * Math.sqrt((a + b + c) * (-a + b + c) * (a - b + c) * (a + b - c));

        return area;
    }
}
