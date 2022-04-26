package ru.senchenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlConverterApplicationTests {

    @Test
    void checkValidUrlConvertingTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    void checkInvalidUrlConvertingTest() {
        Assertions.assertNotEquals(1, 2);
    }

}
