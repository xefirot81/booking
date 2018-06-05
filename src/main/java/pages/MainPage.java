package pages;

import config.Context;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class MainPage extends BasePage {

    public MainPage(Context context) {
        super(context);
    }

    //WEB ELEMENTS
    @FindBy(id = "ss")
    private WebElement destinationTextField;

    @FindBy(css = ".sb-date-field[data-calendar2-title=\"Check-in\"]")
    private WebElement checkInButton;

    @FindBy(css = ".sb-date-field[data-calendar2-title=\"Check-out\"]")
    private WebElement checkOutButton;

    @FindBy(css = ".c2-month .c2-month-table")
    private List<WebElement> monthDropdown;

    @FindBy(id = "b2indexPage")
    private WebElement closeWelcomeButton;

    @FindBy(css = ".bicon-aclose")
    private WebElement closePersonalizedSettingsButton;

    @FindBy(id = "xp__guests__toggle")
    private WebElement menuChooseAdultsChildrenRoomDropdown;

    @FindBy(id = "no_rooms")
    private WebElement roomsDropdown;

    @FindBy(id = "group_adults")
    private WebElement adultsDropdown;

    @FindBy(id = "group_children")
    private WebElement childrenDropdown;

    @FindBy(css = "select[name=\"age\"]")
    private WebElement ageDropdown;

    @FindBy(css = ".sb-searchbox__button")
    private WebElement searchButton;



}
