package pages;

import config.Context;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CurrencyPage extends BasePage {

    public CurrencyPage(Context context) {
        super(context);
    }

    @FindBy(css = ".currency_EUR")
    private WebElement euroButton;

    @FindBy(css = ".currency_USD")
    private WebElement dollarButton;


}
