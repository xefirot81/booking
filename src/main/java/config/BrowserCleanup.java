package config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.UnreachableBrowserException;

@Slf4j
public class BrowserCleanup implements Runnable {
    private Context context;

    public BrowserCleanup(Context frontendContext) {
        this.context = frontendContext;
    }

    public void run() {
        log.info("Closing the browser");
        close();
    }

    public void close() {
        try {
            context.getWebDriver().quit();
            context.setWebDriver(null);
            log.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            log.info("cannot close browser: unreachable browser");
        }
    }
}
