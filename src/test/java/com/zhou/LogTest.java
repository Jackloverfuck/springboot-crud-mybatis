package com.zhou;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogTest {

    Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void logTest01(){
        logger.trace("trace");
        logger.debug("debug");
        //springboot默认级别
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

    }
}
