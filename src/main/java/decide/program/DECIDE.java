package decide.program;

import decide.core.*;

import java.util.Arrays;

/**
 * DECIDE is the main class with the entry point to the program.
 * Here the final decision regarding the launch of the interceptor is made.
 */
public class DECIDE {
    private Parameters parameters;
    private Point[] points;
    private LCM lcm;
    private boolean[] puv;

    /**
     * Emulate the input.
     */
    void init() {
        // We emulate the input to the program.
        // Here we create input that does not lead to a launch.
        parameters = new Parameters();
        parameters.LENGTH1 = 1.0;
        parameters.RADIUS1 = 1.0;
        parameters.EPSILON = 1.0;
        parameters.AREA1 = 1.0;
        parameters.Q_PTS = 2;
        parameters.QUADS = 1;
        parameters.DIST = 1.0;
        parameters.N_PTS = 3;
        parameters.K_PTS = 1;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.G_PTS = 1;
        parameters.LENGTH2 = 1.0;
        parameters.RADIUS2 = 1.0;
        parameters.AREA2 = 1.0;

        points = new Point[100];
        Arrays.fill(points, new Point(0.0, 0.0));

        Parameters.verify(parameters, points);

        lcm = new LCM();
        for (int i = 0; i < LCM.LCM_SIZE; i++) {
            for (int j = 0; j < LCM.LCM_SIZE; j++) {
                lcm.set(i, j, LCM.Value.NOTUSED);
            }
        }

        puv = new boolean[PUM.PUM_SIZE];
    }

    /**
     * Calculate whether to launch the interceptor or not.
     */
    void evaluate() {
        CMV cmv = new CMV(parameters, points);
        PUM pum = new PUM(lcm, cmv);
        FUV fuv = new FUV(puv, pum);

        if (fuv.canLaunch()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        DECIDE decide = new DECIDE();
        decide.init();
        decide.evaluate();
    }
}
