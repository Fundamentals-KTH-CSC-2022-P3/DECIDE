package decide.core;

/**
 * Represents the Conditions Met Vector (CMV) that stores the result from each Launch Interceptor Condition (LIC).
 */
public class CMV {

    // Size of the CMV vector.
    public static final int CMV_SIZE = 15;

    // The CMV vector contains true/false values for each LIC.
    private boolean[] cmv = new boolean[CMV_SIZE];

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
            if (points[i].distance(points[i + 1]) > parameters.LENGTH1) {
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
            // then we cannot allow two points or more to coincide.
            if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
                continue;

            // Heron's Formula to calculate the area of a triangle.
            // This formula only requires the side lengths of the triangle which we can easily retrieve.

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
        return false;
    }

    /**
     * Returns true if there exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
     * that X[j] - X[i] < 0. (where i = j-1)
     */
    private boolean lic5() {
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
    private boolean lic7() {
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
        return false;
    }
}
