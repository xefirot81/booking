package pages;

import config.Context;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Getter
@Component
public class LanguagePage extends BasePage {

    public LanguagePage(Context context) {
        super(context);
    }

    @FindBy(css = ".currency_EUR")
    private WebElement euroButton;

    @FindBy(css = ".lang_en-us")
    private WebElement englishUsButton;


}
