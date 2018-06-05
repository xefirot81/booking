package config;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class Context {

    private WebDriver webDriver;
    private boolean cookiesAccepted = false;
}
