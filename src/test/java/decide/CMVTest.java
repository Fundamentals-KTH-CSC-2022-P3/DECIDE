package decide;

import decide.core.CMV;
import decide.core.Parameters;
import decide.core.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

        // Lets test points[1] == points[2].
        points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 1);

        cmv = new CMV(parameters, points);

        assertFalse(cmv.get(2));
    }


    @Test
    @DisplayName("LIC 3")
    void lic3Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 4")
    void lic4Test() {
        assertTrue(true);
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
    @DisplayName("LIC 9")
    void lic9Test() {
        assertTrue(true);
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
