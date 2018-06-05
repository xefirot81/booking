package deserialization;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wait")
@Getter
@Setter
public class WaitData {

    private String defaultTimeoutSeconds;
    private String shortTimeoutSeconds;
    private String longTimeoutSeconds;
}
