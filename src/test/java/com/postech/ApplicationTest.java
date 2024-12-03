package com.postech;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ApplicationTest {

    @Test
    void contextLoads() {
    }

//    @Test
//    void mainRuns() {
//        Application.main(new String[]{});
//    }

}