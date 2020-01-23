package com.apinasa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.apinasa.helpers.Loggable;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoggableTests {

    @Loggable
    private Logger log;

    @Test
    void isLoggerClass() {
        assertTrue(log instanceof Logger);
    }

}