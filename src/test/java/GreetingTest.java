package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GreetingTests {

    @Test
    @DisplayName("Our first test")
    void dummyTest() {
        assertTrue(true);
    }

    @Test
    @DisplayName("Another test test")
    void anotherDummyTest() {
        assertEquals(1, 1);
    }
}
