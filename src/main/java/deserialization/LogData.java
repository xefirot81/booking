package deserialization;

import io.github.bonigarcia.wdm.DriverManagerType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "log")
@Getter
@Setter
public class LogData {

    private String path;
    private String name;

}

