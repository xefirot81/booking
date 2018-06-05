package utils;

import config.Context;
import deserialization.WaitData;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaitUtils {

    @Autowired
    private WaitData waitData;

    @Autowired
    private Context context;

    private WebDriverWait wait;

    public WebDriverWait getWaitUtils() {

        if (wait == null) {
            wait = new WebDriverWait(context.getWebDriver(), Integer.parseInt(waitData.getDefaultTimeoutSeconds()));
        }
        return wait;
    }

    public WebDriverWait getWaitUtils(int timeout) {

        if (wait == null) {
            wait = new WebDriverWait(context.getWebDriver(), timeout);
        }
        return wait;
    }

    public WebElement waitForElementPresent(WebElement element) {

        try {
            return getWaitUtils(Integer.valueOf(waitData.getDefaultTimeoutSeconds()))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean elementNotPresent(WebElement element) {

        try {
            ExpectedConditions.visibilityOf(element).apply(context.getWebDriver());
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public WebElement waitForElementClickable(WebElement element) {

        try {
            return getWaitUtils(Integer.valueOf(waitData.getDefaultTimeoutSeconds()))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
