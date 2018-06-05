package deserialization;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties //no prefix, find root level values
@Getter
@Setter
public class UrlData {

    private String url;
}
