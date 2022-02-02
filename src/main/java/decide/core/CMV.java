package decide.core;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.PI;

/**
 * Represents the Conditions Met Vector (CMV) that stores the result from each Launch Interceptor Condition (LIC).
 */
public class CMV {

    // Size of the CMV vector.
    public static final int CMV_SIZE = 15;

    // The CMV vector contains true/false values for each LIC.
    private final boolean[] cmv = new boolean[CMV_SIZE];

    private Parameters parameters;
    private Point[] points;

    /**
     * Creates a new Conditions Met Vector (CMV) and computes all the Launch Interceptor Conditions (LICs).
     * @param parameters the parameter values that will be used when computing the LICs.
     * @param points a vector of 2D points.
     */
    public CMV(Parameters parameters, Point[] points) {
        this.parameters = parameters;
        this.points = points;
        computeLICs();
    }

    /**
     * Returns the ith element of the CMV vector.
     * @return ith element of the CMV vector.
     */
    public boolean get(int i) {
        return cmv[i];
    }

    /**
     * Calls a method for each LIC and sets the corresponding entry in the CMV vector.
     */
    private void computeLICs() {
        cmv[0] = lic0();
        cmv[1] = lic1();
        cmv[2] = lic2();
        cmv[3] = lic3();
        cmv[4] = lic4();
        cmv[5] = lic5();
        cmv[6] = lic6();
        cmv[7] = lic7();
        cmv[8] = lic8();
        cmv[9] = lic9();
        cmv[10] = lic10();
        cmv[11] = lic11();
        cmv[12] = lic12();
        cmv[13] = lic13();
        cmv[14] = lic14();
    }

    /**
     * Returns true if there exists at least one set of two consecutive data points that are a distance greater than
     * the length, LENGTH1, apart.
     * (0 ≤ LENGTH1)
     */
    private boolean lic0() {
        for (int i = 0; i < points.length - 1; i++) {
            if (Point.euclidianDistanceBetween(points[i], points[i + 1]) > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there exists at least one set of three consecutive data points that cannot all be contained
     * within or on a circle of radius RADIUS1.
     * (0 ≤ RADIUS1)
     */
    private boolean lic1() {
        for(int i = 0; i < points.length-2; i++) {
            // Translate the points to understandable mathematical variables
            Point x = points[i];
            Point y = points[i + 1];
            Point z = points[i + 2];
            if(!MathTools.pointsAreCoveredByCircle(x, y, z, parameters.RADIUS1)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there exists at least one set of three consecutive data points which form an angle such that:
     * angle < (PI−EPSILON)
     * or
     * angle > (PI+EPSILON)
     * The second of the three consecutive points is always the vertex of the angle. If either the first
     * point or the last point (or both) coincides with the vertex, the angle is undefined and the LIC
     * is not satisfied by those three points.
     * (0 ≤ EPSILON < PI)
     */
    private boolean lic2() {
        for (int i = 0; i < points.length - 2; i++) {
            Point p1 = points[i];
            Point vertex = points[i + 1];
            Point p2 = points[i + 2];

            // A special case where the angle is undefined.
            // These points are not allowed to satisfy the LIC, hence we should skip them.
            if (p1.equals(vertex) || p2.equals(vertex))
                continue;

            double angle = Point.vertexAngle(p1, vertex, p2);

            if (angle < PI - parameters.EPSILON || angle > PI + parameters.EPSILON)
                return true;
        }

        return false;
    }

    /**
     * Returns true if there exists at least one set of three consecutive data points that are the vertices of a triangle
     * with area greater than AREA1.
     * (0 ≤ AREA1)
     */
    private boolean lic3() {
        for (int i = 0; i < points.length - 2; i++) {
            Point p1 = points[i];
            Point p2 = points[i + 1];
            Point p3 = points[i + 2];

            // We need to ensure that the three vertices can form a triangle,
            // hence we cannot allow two points or more to coincide.
            if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
                continue;

            double area = Triangle.area(p1, p2, p3);

            if (area > parameters.AREA1)
                return true;
        }

        return false;
    }

    /**
     * Returns true if there exists at least one set of Q_PTS consecutive data points that lie in more than QUADS
     * quadrants. Where there is ambiguity as to which quadrant contains a given point, priority
     * of decision will be by quadrant number, i.e., I, II, III, IV. For example, the data point (0,0)
     * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
     * (0,1) is in quadrant I and the point (1,0) is in quadrant I.
     * (2 ≤ Q_PTS ≤ NUMPOINTS), (1 ≤ QUADS ≤ 3)
     */
    private boolean lic4() {
        for (int i = 0; i <= points.length - parameters.Q_PTS; i++) {
            Set<Point.Quadrant> quadsWithConsecutivePoints = new HashSet<>();
            for (int j = i; j < i + parameters.Q_PTS; j++) {
                Point.Quadrant quadrant = points[j].getQuadrant();
                quadsWithConsecutivePoints.add(quadrant);
            }
            if (quadsWithConsecutivePoints.size() > parameters.QUADS) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
     * that X[j] - X[i] < 0. (where i = j-1)
     */
    private boolean lic5() {
        for (int i = 0; i < points.length - 1; i++){
            if ((points[i+1].x - points[i].x) < 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there exists at least one set of N_PTS consecutive data points such that at least one of the
     * points lies a distance greater than DIST from the line joining the first and last of these N_PTS
     * points. If the first and last points of these N_PTS are identical, then the calculated distance
     * to compare with DIST will be the distance from the coincident point to all other points of
     * the N_PTS consecutive points. The condition is not met when NUMPOINTS < 3.
     * (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)
     */
    private boolean lic6() {
        return false;
    }

    /**
     * Returns true if there exists at least one set of two data points separated by exactly K_PTS consecutive intervening points
     * that are a distance greater than the length, LENGTH1, apart. The condition is not met when NUMPOINTS < 3.
     * 1 ≤ K_PTS ≤ (NUMPOINTS−2)
     */
    public boolean lic7() {
        if (points.length < 3)
            return false;

        for (int i = 0; i < points.length - parameters.K_PTS - 1; i++) {
            // The consecutive intervening points are those points that are between the start point and the end point in the array.
            Point start = points[i];
            Point end = points[i + parameters.K_PTS + 1];

            // Check if the start point and the end point have a distance greater than LENGTH1 between them.
            if (start.distance(end) > parameters.LENGTH1)
                return true;
        }

        return false;
    }

    /**
     * Returns true if there exists at least one set of three data points separated by exactly A_PTS and B_PTS
     * consecutive intervening points, respectively, that cannot be contained within or on a circle of
     * radius RADIUS1. The condition is not met when NUMPOINTS < 5.
     * 1 ≤ A_PTS, 1 ≤ B_PTS
     * A_PTS+B_PTS ≤ (NUMPOINTS−3)
     */
    private boolean lic8() {
        return false;
    }

    /**
     * Returns true if there exists at least one set of three data points separated by exactly C_PTS and D_PTS
     * consecutive intervening points, respectively, that form an angle such that:
     * angle < (PI−EPSILON)
     * or
     * angle > (PI+EPSILON)
     * The second point of the set of three points is always the vertex of the angle. If either the first
     * point or the last point (or both) coincide with the vertex, the angle is undefined and the LIC
     * is not satisfied by those three points. When NUMPOINTS < 5, the condition is not met.
     * 1 ≤ C_PTS, 1 ≤ D_PTS
     * C_PTS+D_PTS ≤ NUMPOINTS−3
     */
    private boolean lic9() {
        for (int i = 0; i < points.length - parameters.C_PTS - parameters.D_PTS - 2; i++) {
            double angle = Point.vertexAngle(points[i],
                    points[i+parameters.C_PTS+1],
                    points[i+parameters.C_PTS+parameters.D_PTS+2]);

            if (angle < PI - parameters.EPSILON || angle > PI + parameters.EPSILON) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there exists at least one set of three data points separated by exactly E_PTS and
     * F_PTS consecutive intervening points, respectively, that are the vertices of a triangle with area greater
     * than AREA1. The condition is not met when NUMPOINTS < 5.
     * 1 ≤ E_PTS, 1 ≤ F_PTS
     * E_PTS+F_PTS ≤ NUMPOINTS−3
     */
    private boolean lic10() {
        return false;
    }

    /**
     * Returns true if there exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
     * exactly G_PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j) The
     * condition is not met when NUMPOINTS < 3.
     * 1 ≤ G_PTS ≤ NUMPOINTS−2
     */
    private boolean lic11() {
        if (points.length < 3)
            return false;

        for (int i = 0; i < points.length - parameters.G_PTS - 1; i++) {
            // The consecutive intervening points are the points with indices in the open interval (i, j).
            int j = i + parameters.G_PTS + 1;

            // Check if X[j] - X[i] < 0.
            if (points[j].x - points[i].x < 0)
                return true;
        }

        return false;
    }

    /**
     * Returns true if there exists at least one set of two data points, separated by exactly K_PTS consecutive
     * intervening points, which are a distance greater than the length, LENGTH1, apart.
     * In addition, there exists at least one set of two data points (which can be the same or different from
     * the two data points just mentioned), separated by exactly K_PTS consecutive intervening
     * points, that are a distance less than the length, LENGTH2, apart. Both parts must be true
     * for the LIC to be true. The condition is not met when NUMPOINTS < 3.
     * 0 ≤ LENGTH2
     */
    private boolean lic12() {
        if (points.length < 3) {
            return false;
        }

        boolean hasTwoPointsSpacedApartByLENGTH1 = false;
        boolean hasTwoPointsCloserThanLENGTH2 = false;
        for (int i = 0; i < points.length - parameters.K_PTS - 1; i++) {
            double distance = points[i].distance(points[i + parameters.K_PTS + 1]);
            if (distance > parameters.LENGTH1) {
                hasTwoPointsSpacedApartByLENGTH1 = true;
            }

            if (distance < parameters.LENGTH2) {
                hasTwoPointsCloserThanLENGTH2 = true;
            }

            if (hasTwoPointsSpacedApartByLENGTH1 && hasTwoPointsCloserThanLENGTH2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there exists at least one set of three data points, separated by exactly A_PTS and B_PTS
     * consecutive intervening points, respectively, that cannot be contained within or on a circle of
     * radius RADIUS1. In addition, there exists at least one set of three data points (which can be
     * the same or different from the three data points just mentioned) separated by exactly A_PTS
     * and B_PTS consecutive intervening points, respectively, that can be contained in or on a
     * circle of radius RADIUS2. Both parts must be true for the LIC to be true. The condition is
     * not met when NUMPOINTS < 5.
     * 0 ≤ RADIUS2
     */
    private boolean lic13() {
        return false;
    }

    /**
     * Returns true if there exists at least one set of three data points, separated by exactly E_PTS and F_PTS
     * consecutive intervening points, respectively, that are the vertices of a triangle with area greater than AREA1.
     * In addition, there exist three data points (which can be the same or different
     * from the three data points just mentioned) separated by exactly E_PTS and F_PTS
     * consecutive intervening points, respectively, that are the vertices of a triangle with area less than
     * AREA2. Both parts must be true for the LIC to be true. The condition is not met when
     * NUMPOINTS < 5.
     * 0 ≤ AREA2
     */
    private boolean lic14() {
        if (points.length < 5)
            return false;

        boolean hasTriangleAreaLargerThanAREA1 = false;
        boolean hasTriangleAreaLessThanAREA2 = false;

        for (int i = 0; i < points.length - parameters.E_PTS - parameters.F_PTS - 2; i++) {
            Point p1 = points[i];
            Point p2 = points[i + parameters.E_PTS + 1];
            Point p3 = points[i + parameters.E_PTS + parameters.F_PTS + 2];

            // We need to ensure that the three vertices can form a triangle,
            // hence we cannot allow two points or more to coincide.
            if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
                continue;

            double area = Triangle.area(p1, p2, p3);

            if (area > parameters.AREA1) {
                hasTriangleAreaLargerThanAREA1 = true;
            }

            if (area < parameters.AREA2) {
                hasTriangleAreaLessThanAREA2 = true;
            }

            if (hasTriangleAreaLargerThanAREA1 && hasTriangleAreaLessThanAREA2) {
                return true;
            }
        }

        return false;
    }
}
