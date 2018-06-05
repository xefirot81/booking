package utils;

import config.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class WebUtils {

    @Autowired
    private Context context;

    public void checkWebLoaded() {
        context.getWebDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
}
