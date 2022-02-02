package decide.core;

public class FUV {
    private static final int FUV_LENGTH = 15;
    private final boolean[] FUV = new boolean[FUV_LENGTH];

    /**
     * Compute the FUV vector
     * @param puv a boolean vector of length 15
     * @param pum a 2d boolean array of size 15x15
     */
    public FUV(boolean[] puv, PUM pum){
        computeFUV(puv, pum);
    }

    /**
     * Returns the ith element of the FUV vector.
     * @return ith element of the FUV vector.
     */
    public boolean get(int i) {
        return FUV[i];
    }

    /**
     * FUV[i] = true if PUV[i] = false OR PUM[i][j] = true for j != i, 0 <= j <= 14
     */
    private void computeFUV(boolean[] puv, PUM pum){

        for (int i = 0; i < puv.length; i++){
            FUV[i] = !puv[i] || checkRow(pum.get(i), i);
        }
    }

    /**
     * check if arr[i] = true for i != index, 0 <= i <= 14
     */
    private boolean checkRow(boolean[] arr, int index){

        for(int i = 0; i < arr.length; i++){
            if(i == index){
                continue;
            }
            if(!arr[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * The final launch/no launch decision is based on the FUV. The decision to launch requires that all
     * elements in the FUV be true.
     * @return true if all elements in FUV are true
     */
    public boolean canLaunch() {
        boolean launch = true;
        for (int i = 0; i < FUV.length; i++) {
            if(!FUV[i]) {
                launch = false;
                break;
            }
        }
        return launch;
    }
}
