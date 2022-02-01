package decide;

import decide.core.CMV;
import decide.core.Parameters;
import decide.core.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CMVTest {

    private static Point[] createArrayWithPointsAtOrigo(int numberOfPoints) {
        Point[] points = new Point[numberOfPoints];
        Arrays.fill(points, new Point(0.0, 0.0));
        return points;
    }

    private static Point[] createArrayWithPointsFarAwayFromEachOther(int numberOfPoints) {
        Point[] points = new Point[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            points[i] = new Point(i * i, i * i);
        }
        return points;
    }

    @Test
    @DisplayName("LIC 0 Success")
    void lic0SuccessTest() {
        // lic0 should be true if points contains two consecutive points in
        // points, with a distance greater than LENGTH1 between them.
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1;

        // The distance between these points is sqrt(2) > LENGTH1.
        Point[] points = new Point[2];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);

        CMV cmv = new CMV(parameters, points);
        assertTrue(cmv.get(0));
    }

    @Test
    @DisplayName("LIC 0 Fail")
    void lic0FailTest() {
        // lic0 should be true if points contains two consecutive points in
        // points, with a distance greater than LENGTH1 between them.
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2;

        // The distance between these points is sqrt(2) < LENGTH1.
        Point[] points = new Point[2];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);

        CMV cmv = new CMV(parameters, points);
        assertFalse(cmv.get(0));
    }

    @Test
    @DisplayName("LIC 1")
    void lic1Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 2 Success")
    void lic2SuccessTest() {
        Parameters parameters = new Parameters();
        parameters.EPSILON = Math.PI / 2;

        // This creates a right-angled triangle with side lengths: 1, 1 and sqrt(2).
        // The angles will be PI/2 (90 degrees), PI/4 (45 degrees) and PI/4 (45 degrees).
        // So the angle with respect to the vertex should be PI/4 (45 degrees).
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 0);

        CMV cmv = new CMV(parameters, points);

        // PI/4 is less than PI - EPSILON = PI/2, hence this must be true.
        assertTrue(cmv.get(2));
    }

    @Test
    @DisplayName("LIC 2 Fail")
    void lic2FailTest() {
        Parameters parameters = new Parameters();
        parameters.EPSILON = 4 * Math.PI / 5;

        // This creates a right-angled triangle with side lengths: 1, 1 and sqrt(2).
        // The angles will be PI/2 (90 degrees), PI/4 (45 degrees) and PI/4 (45 degrees).
        // So the angle with respect to the vertex should be PI/4 (45 degrees).
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 0);

        CMV cmv = new CMV(parameters, points);

        // PI/4 is not less than PI - EPSILON = PI/5
        // Nor is PI/4 greater than PI + EPSILON = 9*PI/5
        // Hence this must be false.
        assertFalse(cmv.get(2));
    }

    @Test
    @DisplayName("LIC 2 Undefined angle test")
    void lic2UndefinedAngleTest() {
        Parameters parameters = new Parameters();
        parameters.EPSILON = 4 * Math.PI / 5;

        // If points[0] == points[1] or points[1] == points[2]
        // then the angle should be undefined and the LIC should return false.

        // Lets test points[0] == points[1].
        Point[] points = new Point[3];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 1);
        points[2] = new Point(0, 0);

        CMV cmv = new CMV(parameters, points);

        assertFalse(cmv.get(2));
    }

    @DisplayName("LIC 3 Success")
    void lic3SuccessTest() {
        Parameters parameters = new Parameters();
        parameters.AREA1 = 0.45;

        // This creates a right-angled triangle with the area 1/2 = 0.5.
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 0);

        CMV cmv = new CMV(parameters, points);

        // 0.5 > 0.45, hence this must be true.
        assertTrue(cmv.get(3));
    }

    @Test
    @DisplayName("LIC 3 Fail")
    void lic3FailTest() {
        Parameters parameters = new Parameters();
        parameters.AREA1 = 0.55;

        // This creates a right-angled triangle with the area 1/2 = 0.5.
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 0);

        CMV cmv = new CMV(parameters, points);

        // 0.5 is not greater than 0.55, hence this must be false.
        assertFalse(cmv.get(3));
    }

    @Test
    @DisplayName("LIC 3 Illegal triangle")
    void lic3IllegalTriangleTest() {
        Parameters parameters = new Parameters();
        parameters.AREA1 = 1;

        // These points cannot create a valid triangle because points[1] == points[2].
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 1);

        CMV cmv = new CMV(parameters, points);

        // Must be false because the points cannot create a triangle.
        assertFalse(cmv.get(3));
    }

    @Test
    @DisplayName("LIC 4 Success")
    void lic4SuccessTest() {
        Parameters params = new Parameters();
        params.Q_PTS = 4;
        params.QUADS = 3;

        // At least Q_PTS successive points in more than QUADS different quadrants should ensure success
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(-1, 1);
        points[3] = new Point(1, -1);
        points[4] = new Point(-1, -1);

        CMV cmv = new CMV(params, points);

        assertTrue(cmv.get(4));
    }

    @Test
    @DisplayName("LIC 4 Fail")
    void lic4FailTest() {
        Parameters params = new Parameters();
        params.Q_PTS = 4;
        params.QUADS = 3;

        // If there aren't at least Q_PTS successive points in more than QUADS different quadrants, LIC 4 should be false.
        // Below we create five points in three quadrants, and since !(3 > 3) we should fail.
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(1, -1);
        points[4] = new Point(-1, -1);

        CMV cmv = new CMV(params, points);

        assertFalse(cmv.get(4));
    }

    @Test
    @DisplayName("LIC 5")
    void lic5Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 6")
    void lic6Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 7")
    void lic7Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 8")
    void lic8Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 9 Success")
    void lic9SuccessTest() {
        Parameters params = new Parameters();
        params.EPSILON = Math.PI/4;
        params.C_PTS = 3;
        params.D_PTS = 4;

        Point[] points = new Point[10];
        // Fill with spacer points
        Arrays.fill(points, new Point(0.0, 0.0));

        // The set of three points forming the angle.
        // The middle one is the vertex. They form a right angle, i.e. PI/2 rad.
        // Since PI/2 < PI - PI/4, EPSILON being PI/4, this succeeds.
        points[0] = new Point(1.0, 2.0);
        points[params.C_PTS + 1] = new Point(1.0, 1.0);
        points[params.C_PTS + params.D_PTS + 2] = new Point(2.0, 1.0);

        CMV cmv = new CMV(params, points);

        assertTrue(cmv.get(9));
    }

    @Test
    @DisplayName("LIC 9 Fail")
    void lic9FailTest() {
        Parameters params = new Parameters();
        params.EPSILON = 3*Math.PI/4;
        params.C_PTS = 3;
        params.D_PTS = 4;

        Point[] points = new Point[10];
        // Fill with spacer points
        Arrays.fill(points, new Point(0.0, 0.0));

        // The set of three points forming the angle.
        // The middle one is the vertex. They form a right angle, i.e. PI/2 rad.
        // Since neither PI/2 < PI - 3*PI/4 nor PI/2 > PI + 3*PI/4, EPSILON being PI/4, this fails.
        points[0] = new Point(1.0, 2.0);
        points[params.C_PTS + 1] = new Point(1.0, 1.0);
        points[params.C_PTS + params.D_PTS + 2] = new Point(2.0, 1.0);

        CMV cmv = new CMV(params, points);

        assertFalse(cmv.get(9));
    }

    @Test
    @DisplayName("LIC 9 Invalid input")
    void lic9InvalidInputTest() {
        Parameters params = new Parameters();
        params.C_PTS = 3;
        params.D_PTS = 4;

        // This violates the condition that C_PTS + D_PTS <= NUMPOINTS - 3
        Point[] points = new Point[5];
        Arrays.fill(points, new Point(0.0, 0.0));

        CMV cmv = new CMV(params, points);

        assertFalse(cmv.get(9));
    }

    @Test
    @DisplayName("LIC 10")
    void lic10Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 11")
    void lic11Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 12")
    void lic12Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 13")
    void lic13Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 14")
    void lic14Test() {
        assertTrue(true);
    }
}
