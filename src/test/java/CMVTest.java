package com.example;

import com.example.core.CMV;
import com.example.core.Parameters;
import com.example.core.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CMVTest {

    @Test
    @DisplayName("LIC 0 Success")
    void lic0SuccessTest() {
        // lic0 should be true if points contains two consecutive points in
        // points, with a distance greater than LENGTH1 between them.
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1;

        // The distance between these points is sqrt(2) > LENGTH1.
        Point[] points = new Point[2];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);

        CMV cmv = new CMV(parameters, points);
        assertTrue(cmv.get(0));
    }

    @Test
    @DisplayName("LIC 0 Fail")
    void lic0FailTest() {
        // lic0 should be true if points contains two consecutive points in
        // points, with a distance greater than LENGTH1 between them.
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 2;

        // The distance between these points is sqrt(2) < LENGTH1.
        Point[] points = new Point[2];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);

        CMV cmv = new CMV(parameters, points);
        assertFalse(cmv.get(0));
    }

    @Test
    @DisplayName("LIC 1")
    void lic1Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 2")
    void lic2Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 3")
    void lic3Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 4")
    void lic4Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 5")
    void lic5Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 6")
    void lic6Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 7")
    void lic7Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 8")
    void lic8Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 9")
    void lic9Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 10")
    void lic10Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 11")
    void lic11Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 12")
    void lic12Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 13")
    void lic13Test() {
        assertTrue(true);
    }

    @Test
    @DisplayName("LIC 14")
    void lic14Test() {
        assertTrue(true);
    }
}
