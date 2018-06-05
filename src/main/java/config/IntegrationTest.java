package config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;


@ContextConfiguration
@SpringBootTest
@EnableConfigurationProperties
@WebAppConfiguration
public class IntegrationTest {

    @Configuration
    @ComponentScan(basePackages = {"config", "pages", "utils", "deserialization"})
    public static class ComponentScanConfig {

    }
}


