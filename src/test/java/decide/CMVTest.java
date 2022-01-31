package decide;

import decide.core.CMV;
import decide.core.Parameters;
import decide.core.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("LIC 1")
    void lic1Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 2")
    void lic2Test() {
        assertTrue(true);
    }

    @Test
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
