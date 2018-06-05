package steps;

import config.ContextContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pages.HeaderPage;
import utils.ElementType;
import utils.ElementUtils;
import utils.WaitUtils;
import utils.WebUtils;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Slf4j
public class HeaderSteps extends ContextContainer {

    @Autowired
    private HeaderPage headerPage;

    @Autowired
    private WaitUtils waitUtils;

    @Autowired
    private ElementUtils elementUtils;

    @Autowired
    private WebUtils webUtils;

    @When("I choose '(CURRENCY|LANGUAGE)': '(.*)'")
    public void chooseOption(String button, String option) {
        if(!elementUtils.isElementChoosed(button, "HEADER", ElementType.BUTTON, "aria-label", option)) {
            elementUtils.clickOnElement(button, "HEADER", ElementType.BUTTON);
            elementUtils.clickOnElement(option, button, ElementType.BUTTON);
        }
    }
}
