package config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {
    private YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

    @Bean
    public Context frontendContext() {
        return new Context();
    }
}