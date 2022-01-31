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
    @DisplayName("LIC 3")
    void lic3Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 4 Success")
    void lic4SuccessTest() {
        Parameters params = new Parameters();
        params.Q_PTS = 4;
        params.QUADS = 3;

        // At least Q_PTS successive points in QUADS different quadrants should ensure success
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
        params.QUADS = 2;

        // Less than Q_PTS successive points in QUADS different quadrants should ensure failure
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, 0);
        points[4] = new Point(0, 0);

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
