package decide;
import decide.core.FUV;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FUVTest {

    @Test
    @DisplayName("FUV Test")
    void fuvTest() {
        boolean[] PUV1 = new boolean[15];
        boolean[] PUV0 = new boolean[15];
        boolean[][] PUM0 = new boolean[15][15];
        boolean[][] PUM1 = new boolean[15][15];

        for (int i = 0; i < 15; i++){
            PUV1[i] = true;
            PUV0[i] = false;
            for (int j = 0; j < 15; j++){
                PUM0[i][j] = false;
                PUM1[i][j] = true;
            }
        }
        FUV fuv1 = new FUV(PUV0, PUM0);
        FUV fuv2 = new FUV(PUV1, PUM1);
        FUV fuv3 = new FUV(PUV1, PUM0);

        for(int i = 0; i < 15; i++){
            assertTrue(fuv1.get(i));
            assertTrue(fuv2.get(i));
            assertFalse(fuv3.get(i));
        }

    }
}
