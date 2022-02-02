package decide;

import decide.core.CMV;
import decide.core.Parameters;
import decide.core.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    /**
     * Test that we get an exception if we try to run the program with more than 100 points.
     */
    @Test
    @DisplayName("The program should throw an exception if we run it with more than 100 points")
    void moreThan100PointsTest() {
        Parameters params = new Parameters();
        params.LENGTH1 = 1.0;
        params.RADIUS1 = 1.0;
        params.EPSILON = 1.0;
        params.AREA1 = 1.0;
        params.Q_PTS = 1;
        params.QUADS = 1;
        params.DIST = 1.0;
        params.N_PTS = 1;
        params.K_PTS = 1;
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.C_PTS = 1;
        params.D_PTS = 1;
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.G_PTS = 1;
        params.LENGTH2 = 1.0;
        params.RADIUS2 = 1.0;
        params.AREA2 = 1.0;

        Point[] points = new Point[101];
        Arrays.fill(points, new Point(0.0, 0.0));

        assertThrows(IllegalArgumentException.class, () -> Parameters.verify(params, points));
    }

    /**
     * Test that the launch with the right is set to true.
     */
    @Test
    @DisplayName("Launch True Test")
    void launchTrueTest() {

    }
}
