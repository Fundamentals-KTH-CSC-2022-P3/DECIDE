package decide.core;

public class FUV {
    private static final int FUV_LENGTH = 15;
    private final boolean[] FUV = new boolean[FUV_LENGTH];

    /**
     * Compute the FUV vector
     * @param PUV a boolean vector of length 15
     * @param PUM a 2d boolean array of size 15x15
     */
    public FUV(boolean[] PUV, boolean[][] PUM){
        computeFUV(PUV, PUM);
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
    private void computeFUV(boolean[] PUV, boolean[][] PUM){

        for (int i = 0; i < PUV.length; i++){
            FUV[i] = !PUV[i] || checkRow(PUM[i], i);
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
}
