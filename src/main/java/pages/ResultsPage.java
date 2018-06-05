package pages;

import config.Context;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class ResultsPage extends BasePage {

    public ResultsPage(Context context) {
        super(context);
    }

    //WEB ELEMENTS
    @FindBy(css = "input[name=\"sb_travel_purpose\"]")
    private WebElement travelByWorkCheckbox;

    @FindBy(css = ".sr_item")
    private List<WebElement> propertyMenu;

    @FindBy(css = ".sr-hotel__name")
    private List<WebElement> propertyNameTittle;

}
