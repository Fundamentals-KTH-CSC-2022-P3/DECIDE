package decide.core;

/**
 * Represents the Preliminary Unlocking Matrix (PUM).
 */
public class PUM {

    public static final int PUM_SIZE = 15;

    private boolean[][] pum = new boolean[PUM_SIZE][PUM_SIZE];

    /**
     * Creates the Preliminary Unlocking Matrix (PUM) from the LCM and the CMV.
     * @param lcm the LCM.
     * @param cmv the CMV.
     */
    public PUM(LCM lcm, CMV cmv) {
        computePUM(lcm, cmv);
    }

    /**
     * Get the boolean value of PUM[i][j].
     * @param i the row.
     * @param j the column.
     * @return a boolean value.
     */
    public boolean get(int i, int j) {
        return pum[i][j];
    }

    /**
     * Get the whole row of PUM[i].
     * @param i the row.
     * @return a boolean array.
     */
    public boolean[] get(int i) {
        return pum[i];
    }

    // Computes the PUM according to the rules specified in section 2.2
    private void computePUM(LCM lcm, CMV cmv) {
        for (int i = 0; i < PUM_SIZE; i++) {
            for (int j = 0; j < PUM_SIZE; j++) {
                switch (lcm.get(i, j)) {
                    case NOTUSED:
                        // If LCM[i,j] is NOTUSED, then PUM[i,j] should be set to true.
                        pum[i][j] = true;
                        break;
                    case ANDD:
                        // If LCM[i,j] is ANDD, PUM[i,j] should be set to true only if (CMV[i] AND CMV[j]) is true.
                        pum[i][j] = cmv.get(i) && cmv.get(j);
                        break;
                    case ORR:
                        // If LCM[i,j] is ORR, PUM[i,j] should be set to true if (CMV[i] OR CMV[j]) is true.
                        pum[i][j] = cmv.get(i) || cmv.get(j);
                        break;
                }
            }
        }
    }
}
