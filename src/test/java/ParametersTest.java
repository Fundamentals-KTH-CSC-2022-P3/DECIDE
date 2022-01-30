package com.example;

import com.example.core.Parameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParametersTest {

    @Test
    @DisplayName("Valid parameters")
    void validParametersTest() {
        // Create parameters with arbitrary but valid values.
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
        validParams.NUMPOINTS = 5;

        assertDoesNotThrow(validParams::verify);
    }

    @Test
    @DisplayName("Invalid parameters")
    void invalidParametersTest() {
        Parameters params = new Parameters();
        // This should fail to verify because of several reasons. For instance,
        // N_PTS must be at least 3, and it will be set to 0.
        assertThrows(IllegalArgumentException.class, params::verify);
    }
}
