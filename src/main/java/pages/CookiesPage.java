package pages;

import config.Context;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CookiesPage extends BasePage {

    public CookiesPage(Context context) {
        super(context);
    }

    //WEB ELEMENTS
    @FindBy(css = "a.close_warning")
    private WebElement acceptCookiesButton;
}
