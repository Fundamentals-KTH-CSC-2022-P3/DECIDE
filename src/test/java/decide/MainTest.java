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

    /**
     * Check that when some LICs are true and when some are false, it is possible to trigger a launch by setting
     * some entries in the LCM to ANDD/ORR and disabling some LICs by setting the corresponding PUV entries to false.
     */
    @Test
    @DisplayName("Launch True Test 3")
    void launchTrueTest3() {
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

        points[0] = new Point(0, 0);
        points[1] = new Point(0.2, 0);
        points[2] = new Point(0.4, 0);
        points[3] = new Point(0.6, 0);

        assertDoesNotThrow(() -> Parameters.verify(params, points));

        CMV cmv = new CMV(params, points);

        // LIC 2, 5, 9, 11 will be true with the current configuration, the remaining LIC:s will be false.
        assertFalse(cmv.get(0));
        assertFalse(cmv.get(1));
        assertTrue(cmv.get(2));
        assertFalse(cmv.get(3));
        assertFalse(cmv.get(4));
        assertTrue(cmv.get(5));
        assertFalse(cmv.get(6));
        assertFalse(cmv.get(7));
        assertFalse(cmv.get(8));
        assertTrue(cmv.get(9));
        assertFalse(cmv.get(10));
        assertTrue(cmv.get(11));
        assertFalse(cmv.get(12));
        assertFalse(cmv.get(13));
        assertFalse(cmv.get(14));

        LCM lcm = new LCM();

        // Set all LCM entries to NOTUSED to begin with.
        for (int i = 0; i < LCM.LCM_SIZE; i++)
            for (int j = 0; j < LCM.LCM_SIZE; j++)
                lcm.set(i, j, LCM.Value.NOTUSED);

        // Should fill the row PUM[2] with true because CMV[2] is true, and we use the ORR operator.
        for (int i = 0; i < LCM.LCM_SIZE; i++) {
            lcm.set(2, i, LCM.Value.ORR);
            lcm.set(i, 2, LCM.Value.ORR);
        }

        // PUM[2][5] and PUM[5][2] should still be true because both CMV[2] AND CMV[5] are true.
        lcm.set(2, 5, LCM.Value.ANDD);
        lcm.set(5, 2, LCM.Value.ANDD);

        // PUM[9][10] and PUM[10][9] will be set to false because CMV[9] is true AND CMV[10] is false.
        lcm.set(9, 10, LCM.Value.ANDD);
        lcm.set(10, 9, LCM.Value.ANDD);

        PUM pum = new PUM(lcm, cmv);

        // Everything in the PUM matrix should be true except for the entries PUM[9][10] and PUM[10][9].
        for (int i = 0; i < PUM.PUM_SIZE; i++) {
            for (int j = 0; j < PUM.PUM_SIZE; j++) {
                if ((i == 9 && j == 10) || (j == 9 && i == 10))
                    continue;
                assertTrue(pum.get(i, j));
            }
        }

        assertFalse(pum.get(9, 10));
        assertFalse(pum.get(10, 9));

        // All LICs except for LIC 9 and 10 should be considered as a factor in signaling interceptor launch.
        // We must disable LIC 9 and 10 if we want a launch because both the rows PUM[9] and PUM[10] contain
        // one false entry at PUM[9][10] and PUM[10][9], respectively.
        boolean[] puv = new boolean[PUM.PUM_SIZE];
        Arrays.fill(puv, true);

        // Need to disable both these if we want a launch.
        puv[9] = false;
        puv[10] = false;

        FUV fuv = new FUV(puv, pum);

        assertTrue(fuv.canLaunch());
    }

    /**
     * Check that if all LICs are false, and all the entries in the LCM are ORR, and all PUV entries are true
     * then we should never have a launch.
     */
    @Test
    @DisplayName("Launch False Test 1")
    void launchFalseTest() {
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

        // We set all the LCM entries to ORR which means that the PUM will contain only false entries.
        for (int i = 0; i < LCM.LCM_SIZE; i++)
            for (int j = 0; j < LCM.LCM_SIZE; j++)
                lcm.set(i, j, LCM.Value.ORR);

        PUM pum = new PUM(lcm, cmv);

        // The PUM should contain only false entries.
        for (int i = 0; i < PUM.PUM_SIZE; i++)
            for (int j = 0; j < PUM.PUM_SIZE; j++)
                assertFalse(pum.get(i, j));

        // All LICs should be considered as a factor in signaling interceptor launch.
        boolean[] puv = new boolean[PUM.PUM_SIZE];
        Arrays.fill(puv, true);

        FUV fuv = new FUV(puv, pum);

        // Should be false because all rows in the PUM contain only false.
        assertFalse(fuv.canLaunch());
    }

    /**
     * Check that when some LICs are true and when some are false, it is possible to avoid a launch by setting
     * some entries in the LCM to ANDD/ORR to make one row in the PUM contain false.
     */
    @Test
    @DisplayName("Launch False Test 2")
    void launchFalseTest2() {
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

        points[0] = new Point(0, 0);
        points[1] = new Point(0.2, 0);
        points[2] = new Point(0.4, 0);
        points[3] = new Point(0.6, 0);

        assertDoesNotThrow(() -> Parameters.verify(params, points));

        CMV cmv = new CMV(params, points);

        // LIC 2, 5, 9, 11 will be true with the current configuration, the remaining LIC:s will be false.
        assertFalse(cmv.get(0));
        assertFalse(cmv.get(1));
        assertTrue(cmv.get(2));
        assertFalse(cmv.get(3));
        assertFalse(cmv.get(4));
        assertTrue(cmv.get(5));
        assertFalse(cmv.get(6));
        assertFalse(cmv.get(7));
        assertFalse(cmv.get(8));
        assertTrue(cmv.get(9));
        assertFalse(cmv.get(10));
        assertTrue(cmv.get(11));
        assertFalse(cmv.get(12));
        assertFalse(cmv.get(13));
        assertFalse(cmv.get(14));

        LCM lcm = new LCM();

        // Set all LCM entries to NOTUSED to begin with.
        for (int i = 0; i < LCM.LCM_SIZE; i++)
            for (int j = 0; j < LCM.LCM_SIZE; j++)
                lcm.set(i, j, LCM.Value.NOTUSED);

        // Should fill the row PUM[2] with true because CMV[2] is true, and we use the ORR operator.
        for (int i = 0; i < LCM.LCM_SIZE; i++) {
            lcm.set(2, i, LCM.Value.ORR);
            lcm.set(i, 2, LCM.Value.ORR);
        }

        // PUM[2][5] and PUM[5][2] should still be true because both CMV[2] AND CMV[5] are true.
        lcm.set(2, 5, LCM.Value.ANDD);
        lcm.set(5, 2, LCM.Value.ANDD);

        // PUM[9][10] and PUM[10][9] will be set to false because CMV[9] is true AND CMV[10] is false.
        lcm.set(9, 10, LCM.Value.ANDD);
        lcm.set(10, 9, LCM.Value.ANDD);

        PUM pum = new PUM(lcm, cmv);

        // Everything in the PUM matrix should be true except for the entries PUM[9][10] and PUM[10][9].
        for (int i = 0; i < PUM.PUM_SIZE; i++) {
            for (int j = 0; j < PUM.PUM_SIZE; j++) {
                if ((i == 9 && j == 10) || (j == 9 && i == 10))
                    continue;
                assertTrue(pum.get(i, j));
            }
        }

        assertFalse(pum.get(9, 10));
        assertFalse(pum.get(10, 9));

        // We enable PUV[9] which contains a false entry at PUM[9][10] which ensures that we have no launch.
        boolean[] puv = new boolean[PUM.PUM_SIZE];
        Arrays.fill(puv, false);

        // Enable PUV[9] to ensure we have no launch.
        puv[9] = true;

        FUV fuv = new FUV(puv, pum);

        assertFalse(fuv.canLaunch());
    }
}
