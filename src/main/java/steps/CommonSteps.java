package steps;

import config.ContextContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import deserialization.UrlData;
import deserialization.WaitData;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import pages.HeaderPage;
import pages.MainPage;
import utils.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j
public class CommonSteps extends ContextContainer {

    @Autowired
    WaitData waitData;

    @Autowired
    ElementUtils elementUtils;

    @Autowired
    UrlData urlData;

    @Autowired
    TextUtils textUtils;

    @Autowired
    DateUtils dateUtils;

    @Autowired
    WaitUtils wailUtils;

    @Autowired
    MainPage mainPage;

    @Given("^I go to (.*)")
    public void openMainPage(String url) {
        log.info("Opening: " + url);
        getWebDriver().manage().window().maximize(); //TODO: should be in hooks
        getWebDriver().get(url);
    }

    @When("^I resize the browser to ([0-9]+)x([0-9]+) pixels")
    public void resizeBrowser(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        getWebDriver().manage().window().setSize(dimension);
    }

    @Then("^I should see the web correctly charged")
    public void checkWebLoaded() {

        getWebDriver().manage().timeouts().pageLoadTimeout(Long.valueOf(waitData.getDefaultTimeoutSeconds()), TimeUnit.SECONDS);
        wailUtils.waitForElementClickable(mainPage.getCloseWelcomeButton());
    }

    @When("^I click on (.*) (BUTTON|CHECKBOX|MENU) on (.*) page")
    public void clickOnElementStep(String element, ElementType elementType, String page) {
        elementUtils.clickOnElement(element, page, elementType);
    }

    @When("^I click on close (WELCOME|PERSONALIZED_SETTINGS) button")
    public void clickOnCloseButton(String element) {
        WebElement button = null;
        switch (element) {
            case "WELCOME":
                button = mainPage.getCloseWelcomeButton();
                break;
            case "PERSONALIZED_SETTINGS":
                button = mainPage.getClosePersonalizedSettingsButton();
                break;
        }
        wailUtils.waitForElementPresent(button);
        JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
        executor.executeScript("arguments[0].click();", button);

    }

    @When("^I check (.*) (BUTTON|CHECKBOX|MENU) is disabled on (.*) page")
    public void isDisabledButton(String buttonName,ElementType elementType, String page) {

        Assert.assertTrue("Button " + buttonName + " is not disabled", elementUtils.isElementDisabled(buttonName, page,
                elementType));
    }

    @When("^I check the text (.*) is present on (.*) page")
    public void checkElementIsPresentStep(String element, String page) {


        elementUtils.isElementPresent(element, page, ElementType.TEXT_FIELD);
    }

    @When("^I fill in '(.*)' text in '(.*)' field on '(.*)' page")
    public void fillInTextFieldStep(String text, String element, String page) {

        elementUtils.fillTextField(text, element, page);
    }

    @When("^I select (.*) option from (.*) dropdown with text on (.*) page")
    public void selectMenuItemFromMenuWithTextStep(String text, String element, String page) {

        elementUtils.selectElementFromDropdownWithText(text, element, page);
    }

    @When("^I select (.*) option in (.*) checkbox on (.*) page")
    public void selectOptionFromCheckboxStep(String option, String element, String page) {

        elementUtils.clickOnCheckboxItem(option, element, page);
    }

    @When("^I fill in (.*) field on (.*) page with random email")
    public void fillInRandomEmailStep(String field, String page) {
        elementUtils.fillTextField(textUtils.generateRandomEmail(dateUtils.getDateTime()), field, page);
    }

    @When("^I fill in the elements in (.*) page")
    public void fillInElement(String page, List<ElementActionItem> items){
       items.forEach(item->{
            switch (item.getType()){
                case TEXT_FIELD: fillInTextFieldStep(item.getValue(), item.getElement(), page);
                    break;
                case CHECKBOX: selectOptionFromCheckboxStep(item.getValue(), item.getElement(), page);
                    break;
                case DROPDOWN: selectMenuItemFromMenuWithTextStep(item.getValue(), item.getElement(), page);
                    break;
            }
       });

    }

    @When("^I select '([0-9]*)' adult, '([0-9]*)' child '([0-9]*)' years old and '([0-9]*)' rooms$")
    public void selectAdultsChildrenRoom(String adult, String children, String age, String room) {
        if(!wailUtils.elementNotPresent(mainPage.getMenuChooseAdultsChildrenRoomDropdown())) {
            mainPage.getMenuChooseAdultsChildrenRoomDropdown().click();
        }
        elementUtils.selectNumberWithArrows(mainPage.getChildrenDropdown(), children);
        elementUtils.sendkeysOnElement(mainPage.getAgeDropdown(),age);
        elementUtils.selectNumberWithArrows(mainPage.getAdultsDropdown(),adult);
        elementUtils.selectNumberWithArrows(mainPage.getRoomsDropdown(),room);
    }

}
