package steps;

import config.ContextContainer;
import cucumber.api.java.en.Then;
import deserialization.LogData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.Assertion;
import pages.ResultsPage;
import utils.ElementUtils;
import utils.WaitUtils;
import utils.WebUtils;

import javax.validation.constraints.AssertTrue;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class ResultsSteps extends ContextContainer {

    @Autowired
    private ResultsPage resultsPage;

    @Autowired
    private WaitUtils waitUtils;

    @Autowired
    private ElementUtils elementUtils;

    @Autowired
    private WebUtils webUtils;

    @Autowired
    private LogData logData;

    //TODO this step should be refactor to split in several methods to make simpler and more reusable
    //TODO element found should be shared by context
    @Then("^There is a property with a review mark of higher than '(.*)' and price under '(.*)' EUR$")
    public void assertPropertyEvaluationAndPrice(Float evaluation, Integer price) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(logData.getPath() + logData.getName(), "UTF-8");
        waitUtils.waitForElementPresent(resultsPage.getPropertyMenu().get(0));
        Integer size = resultsPage.getPropertyMenu().size();
        Float elementEvaluation;
        Integer elementPrice;
        boolean elementFound = false;
        WebElement element;
        for(Integer i=0;i<size && elementFound == false;i++) {
            element = resultsPage.getPropertyMenu().get(i);
            elementEvaluation = Float.parseFloat(element
                    .findElement(By.cssSelector(".review-score-badge")).getText().replace(',','.'));
            if(!waitUtils.elementNotPresent(element.findElement(By.cssSelector(".totalPrice")))) {
                elementPrice = Integer.parseInt(element
                        .findElement(By.cssSelector(".totalPrice")).getText().trim().split("€ ")[1]);
            } else {
                elementPrice = -1;
            }
            if(elementPrice >=0 && elementEvaluation > evaluation && elementPrice < price) {
                elementFound = true;
                String tittle = element.findElement(By.cssSelector(".sr-hotel__name")).getText().trim();
                writer.println("property name: " + tittle + ", price = " + elementPrice + ", evaluation = " + elementEvaluation);
                writer.close();
            }
        }
        if(!elementFound) {
            Assert.fail("There is no properties with required values");
        }
    }
}
