package decide;

import decide.core.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    /**
     * Test that we get an exception if we try to run the program with more than 100 points.
     */
    @Test
    @DisplayName("More than 100 points test")
    void moreThan100PointsTest() {
        Parameters validParams = new Parameters();
        validParams.LENGTH1 = 1.0;
        validParams.RADIUS1 = 1.0;
        validParams.EPSILON = 1.0;
        validParams.AREA1 = 1.0;
        validParams.Q_PTS = 2;
        validParams.QUADS = 1;
        validParams.DIST = 1.0;
        validParams.N_PTS = 3;
        validParams.K_PTS = 1;
        validParams.A_PTS = 1;
        validParams.B_PTS = 1;
        validParams.C_PTS = 1;
        validParams.D_PTS = 1;
        validParams.E_PTS = 1;
        validParams.F_PTS = 1;
        validParams.G_PTS = 1;
        validParams.LENGTH2 = 1.0;
        validParams.RADIUS2 = 1.0;
        validParams.AREA2 = 1.0;

        Point[] points = new Point[101];
        Arrays.fill(points, new Point(0.0, 0.0));

        assertThrows(IllegalArgumentException.class, () -> Parameters.verify(validParams, points));
    }

    /**
     * Check that even though all LICs are false it is possible to trigger a launch by setting each entry in the LCM to NOTUSED.
     */
    @Test
    @DisplayName("Launch True Test 1")
    void launchTrueTest() {
        Parameters params = new Parameters();
        params.LENGTH1 = 1.0;
        params.RADIUS1 = 1.0;
        params.EPSILON = 1.0;
        params.AREA1 = 1.0;
        params.Q_PTS = 2;
        params.QUADS = 1;
        params.DIST = 1.0;
        params.N_PTS = 3;
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

        Point[] points = new Point[100];
        Arrays.fill(points, new Point(0, 0));

        assertDoesNotThrow(() -> Parameters.verify(params, points));

        CMV cmv = new CMV(params, points);

        // All LICs should be false with the current configuration.
        for (int i = 0; i < CMV.CMV_SIZE; i++)
            assertFalse(cmv.get(i));

        LCM lcm = new LCM();

        // We set all the LCM entries to NOTUSED which means that the PUM will contain only true entries.
        for (int i = 0; i < LCM.LCM_SIZE; i++)
            for (int j = 0; j < LCM.LCM_SIZE; j++)
                lcm.set(i, j, LCM.Value.NOTUSED);

        PUM pum = new PUM(lcm, cmv);

        // The PUM should contain only true entries.
        for (int i = 0; i < PUM.PUM_SIZE; i++)
            for (int j = 0; j < PUM.PUM_SIZE; j++)
                assertTrue(pum.get(i, j));

        // All LICs should be considered as a factor in signaling interceptor launch.
        boolean[] puv = new boolean[PUM.PUM_SIZE];
        Arrays.fill(puv, true);

        FUV fuv = new FUV(puv, pum);

        // Should be true because all rows in the PUM contain only true.
        assertTrue(fuv.canLaunch());
    }

    /**
     * Check that even though all LICs are false it is possible to trigger a launch by setting all entries in the PUV to false,
     * which indicates that not a single LIC should be considered as a factor in signaling interceptor launch.
     */
    @Test
    @DisplayName("Launch True Test 2")
    void launchTrueTest2() {
        Parameters params = new Parameters();
        params.LENGTH1 = 1.0;
        params.RADIUS1 = 1.0;
        params.EPSILON = 1.0;
        params.AREA1 = 1.0;
        params.Q_PTS = 2;
        params.QUADS = 1;
        params.DIST = 1.0;
        params.N_PTS = 3;
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

        Point[] points = new Point[100];
        Arrays.fill(points, new Point(0, 0));

        assertDoesNotThrow(() -> Parameters.verify(params, points));

        CMV cmv = new CMV(params, points);

        // All LICs should be false with the current configuration.
        for (int i = 0; i < CMV.CMV_SIZE; i++)
            assertFalse(cmv.get(i));

        LCM lcm = new LCM();

        // We set all the LCM entries to ORR which means that PUM[i][j] is set to true if CMV[i] or CMV[j] is true.
        // But because all CMV entries are false the PUM will only contain false entries as well.
        for (int i = 0; i < LCM.LCM_SIZE; i++)
            for (int j = 0; j < LCM.LCM_SIZE; j++)
                lcm.set(i, j, LCM.Value.ORR);

        PUM pum = new PUM(lcm, cmv);

        // The PUM should contain only false entries.
        for (int i = 0; i < PUM.PUM_SIZE; i++)
            for (int j = 0; j < PUM.PUM_SIZE; j++)
                assertFalse(pum.get(i, j));

        // Not a single LIC should be considered as a factor in signaling interceptor launch.
        boolean[] puv = new boolean[PUM.PUM_SIZE];
        Arrays.fill(puv, false);

        FUV fuv = new FUV(puv, pum);

        // Should be true because all entries in the PUV are false.
        assertTrue(fuv.canLaunch());
    }
}
