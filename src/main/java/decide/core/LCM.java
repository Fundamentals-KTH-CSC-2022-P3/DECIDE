package decide.core;

/**
 * Represents the Logical Connector Matrix (LCM) a 15x15 matrix where each element can be assigned three
 * different possible values: ANDD/ORR/NOTUSED.
 */
public class LCM {

    public static final int LCM_SIZE = 15;

    // The possible values for each element in the matrix.
    public enum Value {
        ANDD,
        ORR,
        NOTUSED
    }

    private Value[][] lcm = new Value[LCM_SIZE][LCM_SIZE];

    /**
     * Set the value of LCM[i][j] to ANDD, ORR or NOTUSED.
     * @param i the row.
     * @param j the column.
     * @param v the value.
     */
    public void set(int i, int j, Value v) {
        lcm[i][j] = v;
    }

    /**
     * Get the value of LCM[i][j].
     * @param i the row.
     * @param j the column.
     * @return a value.
     */
    public Value get(int i, int j) {
        return lcm[i][j];
    }
}
