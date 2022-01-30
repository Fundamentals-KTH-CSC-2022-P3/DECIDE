package com.example.core;

/**
 * Represents a set of different parameters that are used when implementing each LIC.
 */
public class Parameters {
    public double LENGTH1;      // Length in LICs 0, 7, 12
    public double RADIUS1;      // Radius in LICs 1, 8, 13
    public double EPSILON;      // Deviation from PI in LICs 2, 9
    public double AREA1;        // Area in LICs 3, 10, 14
    public int Q_PTS;           // Number of consecutive points in LIC 4
    public int QUADS;           // Number of quadrants in LIC 4
    public double DIST;         // Distance in LIC 6
    public int N_PTS;           // Number of consecutive points in LIC 6
    public int K_PTS;           // Number of intervening points in LICs 7, 12
    public int A_PTS;           // Number of intervening points in LICs 8, 13
    public int B_PTS;           // Number of intervening points in LICs 8, 13
    public int C_PTS;           // Number of intervening points in LICs 9
    public int D_PTS;           // Number of intervening points in LICs 9
    public int E_PTS;           // Number of intervening points in LICs 10, 14
    public int F_PTS;           // Number of intervening points in LICs 10, 14
    public int G_PTS;           // Number of intervening points in LICs 11
    public double LENGTH2;      // Maximum length in LIC 12
    public double RADIUS2;      // Maximum radius in LIC 13
    public double AREA2;        // Maximum area in LIC 14
    public int NUMPOINTS;       // Total number of input points

    /**
     * Verifies that all properties are conforming to the requirements specification (RS).
     * @throws Exception if any property is invalid.
     */
    public void verify() {

        // According to RS 2.1.0
        if (LENGTH1 < 0) {
            throw new IllegalArgumentException("LENGTH1 must be non-negative.");
        }

        // According to RS 2.1.1
        if (RADIUS1 < 0) {
            throw new IllegalArgumentException("RADIUS1 must be non-negative.");
        }

        // According to RS 2.1.2
        if (EPSILON < 0 || EPSILON >= Math.PI) {
            throw new IllegalArgumentException("EPSILON must be in the range [0, pi)");
        }

        // According to RS 2.1.3
        if (AREA1 < 0) {
            throw new IllegalArgumentException("AREA must be non-negative");
        }

        // According to RS 2.1.4
        if (Q_PTS < 2 || Q_PTS > NUMPOINTS) {
            throw new IllegalArgumentException("Q_PTS must be in the range [2, NUMPOINTS]");
        }

        // According to RS 2.1.4
        if (QUADS < 1 || QUADS > 3) {
            throw new IllegalArgumentException("QUADS must be in the range [1, 3]");
        }

        // According to RS 2.1.6
        if (N_PTS < 3 || N_PTS > NUMPOINTS) {
            throw new IllegalArgumentException("N_PTS must be in the range [3, NUMPOINTS]");
        }

        // According to RS 2.1.6
        if (DIST < 0) {
            throw new IllegalArgumentException("DIST must be non-negative");
        }

        // According to RS 2.1.7
        if (K_PTS < 1 || K_PTS > NUMPOINTS - 2) {
            throw new IllegalArgumentException("K_PTS must be in the range [1, NUMPOINTS-2]");
        }

        // According to RS 2.1.8
        if (A_PTS < 1) {
            throw new IllegalArgumentException("A_PTS must be positive");
        }

        // According to RS 2.1.8
        if (B_PTS < 1) {
            throw new IllegalArgumentException("B_PTS must be positive");
        }

        // According to RS 2.1.8
        if (A_PTS + B_PTS > NUMPOINTS - 3) {
            throw new IllegalArgumentException("A_PTS + B_PTS must not be greater than NUMPOINTS - 3");
        }

        // According to RS 2.1.9
        if (C_PTS < 1) {
            throw new IllegalArgumentException("C_PTS must be positive");
        }

        // According to RS 2.1.9
        if (D_PTS < 1) {
            throw new IllegalArgumentException("D_PTS must be positive");
        }

        // According to RS 2.1.9
        if (C_PTS + D_PTS > NUMPOINTS - 3) {
            throw new IllegalArgumentException("C_PTS + D_PTS must not be greater than NUMPOINTS - 3");
        }

        // According to RS 2.1.10
        if (E_PTS < 1) {
            throw new IllegalArgumentException("E_PTS must be positive");
        }

        // According to RS 2.1.10
        if (F_PTS < 1) {
            throw new IllegalArgumentException("F_PTS must be positive");
        }

        // According to RS 2.1.10
        if (E_PTS + F_PTS > NUMPOINTS - 3) {
            throw new IllegalArgumentException("E_PTS + F_PTS must not be greater than NUMPOINTS - 3");
        }

        // According to RS 2.1.11
        if (G_PTS < 1 || G_PTS > NUMPOINTS - 2) {
            throw new IllegalArgumentException("G_PTS must be in the range [1, NUMPOINTS - 2]");
        }

        // According to RS 2.1.12
        if (LENGTH2 < 0) {
            throw new IllegalArgumentException("LENGTH2 must be non-negative");
        }

        // According to RS 2.1.13
        if (RADIUS2 < 0) {
            throw new IllegalArgumentException("RADIUS2 must be non-negative");
        }

        // According to RS 2.1.14
        if (AREA2 < 0) {
            throw new IllegalArgumentException("AREA2 must be non-negative");
        }
    }
}
