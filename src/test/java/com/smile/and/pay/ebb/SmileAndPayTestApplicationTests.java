package com.smile.and.pay.ebb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
/*@SpringBootTest(classes = {ServletWebServerFactoryAutoConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.cloud.config.enabled=false"})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc*/
class SmileAndPayTestApplicationTests {

    @Test
    void contextLoads() {}

}
