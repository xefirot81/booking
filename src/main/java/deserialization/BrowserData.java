package deserialization;

import io.github.bonigarcia.wdm.DriverManagerType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "browser")
@Getter
@Setter
public class BrowserData {

    @Setter
    private DriverManagerType name;
}
