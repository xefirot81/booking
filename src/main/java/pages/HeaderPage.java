package pages;

import config.Context;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Getter
@Component
public class HeaderPage extends BasePage {

    public HeaderPage(Context context) {
        super(context);
    }

    @FindBy(css = ".currency_va_middle")
    private WebElement currencyButton;

    @FindBy(css = ".uc_language .popover_trigger")
    private WebElement languageButton;


}



