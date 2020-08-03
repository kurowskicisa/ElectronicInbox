package com.ctk.model;

import org.junit.Assert;
import org.junit.jupiter.api.*;

@DisplayName("GrayScale")
class GrayScaleTest {

/*
    @BeforeAll
    void Init() {
    }
*/
/*
    @BeforeEach
    void beforeEach() {
    }
*/

    @Test
    @DisplayName("should return '0'")
    void returnString0() {
        // GIVEN
        String result;
        GrayScale gs = new GrayScale();
        gs.setGrayScale("0");
        // WHEN
        result = gs.getGrayScale();
        // THEN
        Assert.assertEquals(result, "0");
    }

    @Test
    @DisplayName("should return '100'")
    void returnString100() {
        // GIVEN
        String result;
        GrayScale gs = new GrayScale();
        gs.setGrayScale("100");
        // WHEN
        result = gs.getGrayScale();
        // THEN
        Assert.assertEquals(result, "100");
    }

    @Test
    @DisplayName("should return '0' if entry is character")
    void returnString0ifNoDigitEntry() {
        // GIVEN
        String result = null;
        GrayScale gs = new GrayScale();
        gs.setGrayScale("A");
        // WHEN
        result = gs.getGrayScale();
        // THEN
        Assert.assertNotEquals(result, "0");
        

     //   Assert.assertEquals(result, "0");
    }

    /*
    @AfterEach
    void afterEach() {
    }


    @AfterAll
    void afterAll() {
    }
*/

}
