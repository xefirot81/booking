package config;

import deserialization.BrowserData;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@DependsOn(value={"frontendContext", "browserData"})
public class BrowserDriver {

    private Context context;
    private BrowserData browserData;

    public BrowserDriver(Context context, BrowserData browserData) {
        this.context=context;
        this.browserData = browserData;
        switch (browserData.getName()) {
            case CHROME:
                getChromeDriver();
                break;
            case FIREFOX:
                getFirefoxDriver();
                break;
            default:
                break;
        }
    }

    public WebDriver getChromeDriver() {
        try {
            ChromeDriverManager.chromedriver().setup(); //TODO: should be in a @BeforeAll
            ChromeOptions chromeOptions = new ChromeOptions();
            //TODO: uncomment to work in headless mode
//            chromeOptions.addArguments("headless");
//            chromeOptions.addArguments("window-size=1920x1080");
            context.setWebDriver(new ChromeDriver(chromeOptions));


        } finally {
            Runtime.getRuntime().addShutdownHook(
                    new Thread(new BrowserCleanup(context)));
        }
        return context.getWebDriver();
    }

    public WebDriver getFirefoxDriver() {
        try {
            FirefoxDriverManager.firefoxdriver().setup(); //TODO: should be in a @BeforeAll
            //TODO new FirefoxDriver with options

        } finally {
            Runtime.getRuntime().addShutdownHook(
                    new Thread(new BrowserCleanup(context)));
        }
        return context.getWebDriver();
    }
}

