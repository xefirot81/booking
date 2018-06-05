package steps;

import config.ContextContainer;
import cucumber.api.java.en.Given;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import pages.CookiesPage;
import utils.WaitUtils;

@Log4j
public class CookiesSteps extends ContextContainer {

    @Autowired
    private CookiesPage cookiesPage;

    @Autowired
    WaitUtils wailUtils;

    @Given("I accept cookies")
    public void acceptCookies() {

        if (!cookiesPage.getContext().isCookiesAccepted()) {
            wailUtils.waitForElementPresent(cookiesPage.getAcceptCookiesButton());
            JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
            executor.executeScript("arguments[0].click();", cookiesPage.getAcceptCookiesButton());
            cookiesPage.getContext().setCookiesAccepted(true);
        }
    }
}