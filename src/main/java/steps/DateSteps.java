package steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import pages.MainPage;
import utils.ElementNumber;
import utils.ElementType;
import utils.ElementUtils;
import utils.MonthNumber;

import java.util.List;

public class DateSteps {

    private final String selector = ".c2-day:not(.c2-day-s-disabled)";

    @Autowired
    private MainPage mainPage;

    @Autowired
    private ElementUtils elementUtils;

    //TODO: this step should be more generic to make more reusable, right now don't contemplate all combinations
    @When("^I select '(FIRST|LAST)' day of '(CURRENT|NEXT)' month")
    public void selectDay(ElementNumber elementNumber, MonthNumber monthNumber) {
        List<WebElement> webElements = mainPage.getMonthDropdown().get(monthNumber.getNumberOfMonth()).
                findElements(By.cssSelector(selector));
        switch (elementNumber) {
            case LAST:
                Integer elementsNumber = webElements.size();
                webElements.get(elementsNumber-1).click();
                break;
            case FIRST:
                webElements.get(0).click();
                break;
        }

    }
}
