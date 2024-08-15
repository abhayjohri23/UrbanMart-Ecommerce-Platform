package com.springDataJPA.Assignment3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

@SpringBootTest
public class TestRandomFunctionality {
    @Test
    public void testTheSummationOfTwoNumbers(){
        assertTrue(59+42 == 101);
    }

    @Test
    public void testAssertAllAnotation(){
        int[] arr1 = {-1,-2,-3};
        int[] arr2 = {-1,-2,-3};

        assertAll("Assert all working for below positive scenarios",
                () -> assertEquals(0,1+(-1)),
                () -> assertTrue(true ==true),
                () -> assertEquals("Hey, I am a test","Hey, I am a test"),
                () -> assertNotEquals("===","equals()"),
                () -> assertArrayEquals(arr1,arr2));
    }

    @Test
    @DisplayName("AssumptionScenario")
    public void testAssumptionsWithAssertions(){
        assumeFalse(2 == 7);
        assertFalse(7+7 == 9);
    }
}
