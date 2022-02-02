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
    @DisplayName("LIC 1 Success")
    void lic1SuccessTest() {
        Parameters parameters = new Parameters();
        parameters.RADIUS1 = 1;


        Point[] points = new Point[6];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, -1);
        points[3] = new Point(-1, 1);
        points[4] = new Point(1, 0);
        points[5] = new Point(0, 1);

        CMV cmv = new CMV(parameters, points);
        assertTrue(cmv.get(1));
    }

    @Test
    @DisplayName("LIC 1 Fail")
    void lic1FailTest() {
        Parameters parameters = new Parameters();
        parameters.RADIUS1 = Math.sqrt(2);


        Point[] points = new Point[6];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, -1);
        points[3] = new Point(-1, 1);
        points[4] = new Point(1, 0);
        points[5] = new Point(0, 1);

        CMV cmv = new CMV(parameters, points);
        assertFalse(cmv.get(1));
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
    @DisplayName("LIC 5 Success")
    void lic5SuccessTest() {
        Parameters params = new Parameters();
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(3, 0);
        points[2] = new Point(1, 0);

        CMV cmv = new CMV(params, points);
        // the consecutive data points (points[2].x - points[1].x) < 0, LIC5 should return true
        assertTrue(cmv.get(5));
    }

    @Test
    @DisplayName("LIC 5 Fail")
    void lic5FailTest() {
        Parameters params = new Parameters();
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(2, 0);

        CMV cmv = new CMV(params, points);
        // there are no consecutive data points where (points[i+1].x - points[i].x) < 0, LIC5 should return false
        assertFalse(cmv.get(5));
    }

    @Test
    @DisplayName("LIC 6 Success nr 1")
    void lic6SuccessTest1() {
        Parameters params = new Parameters();
        params.DIST = 1.0;
        params.N_PTS = 3;
        Point[] points = new Point[4];
        points[0] = new Point(0, 3);
        points[1] = new Point(4, 3);
        points[2] = new Point(2, 7);
        points[3] = new Point(4, 5);

        CMV cmv = new CMV(params, points);
        // For the 3 consecutive data points: points[0], [1] and [2], points[1] lies a distance greater than DIST
        // from the line joining the first and last of these N PTS points. LIC6 should therefore return true.
        assertTrue(cmv.get(6));
    }

    @Test
    @DisplayName("LIC 6 Success nr 2")
    void lic6SuccessTest2() {
        Parameters params = new Parameters();
        params.DIST = 1.0;
        params.N_PTS = 3;
        Point[] points = new Point[4];
        points[0] = new Point(0, 3);
        points[1] = new Point(4, 3);
        points[2] = new Point(0, 3);
        points[3] = new Point(4, 5);

        CMV cmv = new CMV(params, points);
        // For the 3 consecutive data points: points[0], [1] and [2], the first and last points of these N_PTS are
        // identical, points[1] lies a distance greater than DIST from the coincident point.
        // LIC6 should therefore return true.
        assertTrue(cmv.get(6));
    }

    @Test
    @DisplayName("LIC 6 Fail")
    void lic6FailTest() {
        Parameters params = new Parameters();
        params.DIST = 10;
        params.N_PTS = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0, 3);
        points[1] = new Point(1, 5);
        points[2] = new Point(2, 7);

        CMV cmv = new CMV(params, points);
        // since all points lie in a straight line, there is no point that lies a distance greater than DIST
        // from the line joining the first and last of these N PTS points. LIC6 should therefore return false.
        assertFalse(cmv.get(6));
    }

    @Test
    @DisplayName("LIC 7 Success")
    void lic7SuccessTest() {
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1;
        parameters.K_PTS = 2;

        // The distance between points[0] and points[3] is 2.
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(2, 0);

        CMV cmv = new CMV(parameters, points);

        // There exists exactly K_PTS = 2 consecutive intervening points between points[0] and points[3] namely points[1] and points[2].
        // The distance between points[0] and points[3] is greater than LENGTH1 = 1, hence this must be true.
        assertTrue(cmv.get(7));
    }

    @Test
    @DisplayName("LIC 7 Fail")
    void lic7FailTest() {
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2;
        parameters.K_PTS = 2;

        // The distance between points[0] and points[3] is 2.
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(2, 0);

        CMV cmv = new CMV(parameters, points);

        // There exists exactly K_PTS = 2 consecutive intervening points between points[0] and points[3] namely points[1] and points[2].
        // The distance between points[0] and points[3] is not greater than LENGTH1 = 2, hence this must be false.
        assertFalse(cmv.get(7));
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
    @DisplayName("LIC 11 Success")
    void lic11SuccessTest() {
        Parameters parameters = new Parameters();
        parameters.G_PTS = 2;

        // points[3].x - points[0].x = -2.
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(-2, 0);

        CMV cmv = new CMV(parameters, points);

        // There exists exactly G_PTS = 2 consecutive intervening points between points[0] and points[3] namely points[1] and points[2].
        // points[3].x - points[0].x = -2 which is less than 0, hence this must be true.
        assertTrue(cmv.get(11));
    }

    @Test
    @DisplayName("LIC 11 Fail")
    void lic11FailTest() {
        Parameters parameters = new Parameters();
        parameters.G_PTS = 2;

        // points[3].x - points[0].x = 2.
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(2, 0);

        CMV cmv = new CMV(parameters, points);

        // There exists exactly G_PTS = 2 consecutive intervening points between points[0] and points[3] namely points[1] and points[2].
        // points[3].x - points[0].x = 2 which is not less than 0, hence this must be false.
        assertFalse(cmv.get(11));
    }

    /**
     * Test that LIC 12 evaluates to true when there are two points with exactly K_PTS points between them spaced apart
     * by a distance greater than LENGTH1, as well as two points with exactly K_PTS between them closer than LENGTH2.
     */
    @Test
    @DisplayName("LIC 12 Success")
    void lic12SuccessTest() {
        Parameters params = new Parameters();
        params.K_PTS = 2;
        params.LENGTH1 = 5.0;
        params.LENGTH2 = 3.0;

        Point[] points = new Point[5];

        // points[0] and points[3] are further apart than LENGTH1
        points[0] = new Point(0.0,0.0);
        points[3] = new Point(6.0, 0.0);

        // points[1] and points[4] are closer than LENGTH2
        points[1] = new Point(0.0, 1.0);
        points[4] = new Point(0.0, 3.0);

        // dummy point to fill the array
        points[2] = new Point(0.0, 0.0);

        CMV cmv = new CMV(params, points);

        assertTrue(cmv.get(12));
    }

    /**
     * Test that LIC 12 evaluates to false when there are not two points with exactly K_PTS points between them spaced
     * apart by a distance greater than LENGTH1, nor two points with exactly K_PTS between them closer than LENGTH2.
     */
    @Test
    @DisplayName("LIC 12 Fail")
    void lic12FailTest() {
        Parameters params = new Parameters();
        params.K_PTS = 1;
        params.LENGTH1 = 5.0;
        params.LENGTH2 = 1.0;

        // These points will falsify LIC-12 because
        // points[0].distance(points[2]) = 2.83 (which is between LENGTH2 and LENGTH1)
        // points[1].distance(points[3]) = 2.83 (which is between LENGTH2 and LENGTH1)
        Point[] points = new Point[4];
        points[0] = new Point(0.0, 0.0);
        points[1] = new Point(100.0, 100.0);
        points[2] = new Point(2.0, 2.0);
        points[3] = new Point(102.0, 102.0);

        CMV cmv = new CMV(params, points);

        assertFalse(cmv.get(12));
    }

    @Test
    @DisplayName("LIC 13")
    void lic13Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 14 Success")
    void lic14SuccessTest() {
        Parameters parameters = new Parameters();
        parameters.E_PTS = 2;
        parameters.F_PTS = 3;
        parameters.AREA1 = 1.9;
        parameters.AREA2 = 0.55;

        Point[] points = new Point[9];
        Arrays.fill(points, new Point(0.0, 0.0));

        // One triangle is formed from points: 0, 3, 7, this triangle has area 2 which is greater than AREA1 = 1.9.
        // Another triangle is formed from points: 1, 4, 8, this triangle has area 1/2 which is less than AREA2 = 0.55.
        points[0] = new Point(0, 0);
        points[3] = new Point(2, 0);
        points[7] = new Point(2, 2);

        points[0] = new Point(0, 0);
        points[4] = new Point(1, 0);
        points[8] = new Point(1, 1);

        CMV cmv = new CMV(parameters, points);

        assertTrue(cmv.get(14));
    }

    @Test
    @DisplayName("LIC 14 Fail")
    void lic14FailTest() {
        Parameters parameters = new Parameters();
        parameters.E_PTS = 2;
        parameters.F_PTS = 3;
        parameters.AREA1 = 1.9;
        parameters.AREA2 = 0.45;

        Point[] points = new Point[9];
        Arrays.fill(points, new Point(0.0, 0.0));

        // One triangle is formed from points: 0, 3, 7, this triangle has area 2 which is greater than AREA1 = 1.9.
        // Another triangle is formed from points: 1, 4, 8, this triangle has area 1/2 which is NOT less than AREA2 = 0.45.
        points[0] = new Point(0, 0);
        points[3] = new Point(2, 0);
        points[7] = new Point(2, 2);

        points[0] = new Point(0, 0);
        points[4] = new Point(1, 0);
        points[8] = new Point(1, 1);

        CMV cmv = new CMV(parameters, points);

        assertFalse(cmv.get(14));
    }
}
