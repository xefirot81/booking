package pages;

import config.Context;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BasePage {

    Context context;

    public BasePage(Context context) {
        this.context = context;
        PageFactory.initElements(context.getWebDriver(), this);
    }
}



