package com.example;

import com.example.core.Parameters;
import com.example.core.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParametersTest {

    /**
     * Test that we can create and validate parameters and points that adhere to the specification.
     * There are many constraints on the different parameters, so refer to the requirements specification for
     * explanation of the different numeric values.
     */
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

        // Create arbitrary points array. 10 points should be valid.
        com.example.core.Point[] points = new Point[10];
        Arrays.fill(points, new Point(0.0, 0.0));

        assertDoesNotThrow(() -> Parameters.verify(validParams, points));
    }

    /**
     * Test that invalid parameters lead to an exception being thrown. The parameters created fail to fulfil the
     * requirements stated in the requirements specification.
     */
    @Test
    @DisplayName("Invalid parameters")
    void invalidParametersTest() {
        // This should fail to verify because of several reasons. For instance,
        // N_PTS must be at least 3, and it will be set to 0.
        Parameters invalidParams = new Parameters();

        // Create a point just so we have something non-null to pass along to verify
        Point[] points = new Point[1];
        points[0] = new Point(0.0, 0.0);
        assertThrows(IllegalArgumentException.class, () -> Parameters.verify(invalidParams, points));
    }
}
