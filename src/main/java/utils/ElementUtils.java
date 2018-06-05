package utils;

import config.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class ElementUtils {
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    WaitUtils wailUtils;
    @Autowired
    TextUtils textUtils;
    @Autowired
    Context context;

    public void clickOnElement(WebElement element) {

        wailUtils.waitForElementPresent(element).click();
    }

    public void clickOnElementJavaScript(WebElement element) {
        wailUtils.waitForElementPresent(element);
        JavascriptExecutor executor = (JavascriptExecutor) context.getWebDriver();
        executor.executeScript("arguments[0].click();", element);
    }
    
    public void clickOnMenuItem(String menu, String item, String pageName) {

        clickOnElement(menu, pageName, ElementType.MENU);
        clickOnElement(item, pageName, ElementType.MENU_ITEM);
    }

    public void sendkeysOnElementJavaScript(WebElement element, String keys) {
        JavascriptExecutor executor = (JavascriptExecutor) context.getWebDriver();
        executor.executeScript("arguments[0].setAttribute('value', '" + keys +"')", element);
    }

    public void clickOnElement(String webElement, String pageName, ElementType elementType) {

        invokeAction(Action.CLICK,
                textUtils.transformToCamelCase(webElement),
                textUtils.transformToLowerCamelCase(pageName) +
                        textUtils.transformToCamelCase(ElementType.PAGE.toString()), elementType, Constants.EMPTY);

    }

    public void clickOnElementJavaScript(String webElement, String pageName, ElementType elementType) {

        invokeAction(Action.JAVASCRIPT_CLICK,
                textUtils.transformToCamelCase(webElement),
                textUtils.transformToLowerCamelCase(pageName) +
                        textUtils.transformToCamelCase(ElementType.PAGE.toString()), elementType, Constants.EMPTY);

    }

    public void sendkeysOnElement(WebElement element, String keys) {

        wailUtils.waitForElementPresent(element).sendKeys(keys);
//        wailUtils.waitForElementPresent((WebElement) By.cssSelector("[" + keys + "]")).click();
        wailUtils.waitForElementPresent(element).sendKeys(Keys.ALT);
    }

    public void fillTextField(String text, String element, String pageName) {

        invokeAction(Action.SEND_KEYS,
                textUtils.transformToCamelCase(element),
                textUtils.transformToLowerCamelCase(pageName) +
                        textUtils.transformToCamelCase(ElementType.PAGE.toString()),
                ElementType.TEXT_FIELD,
                text);
    }

    public void selectElementFromDropdownWithText(String text, String element, String pageName) {

        invokeAction(Action.CLICK,
                textUtils.transformToCamelCase(element),
                textUtils.transformToLowerCamelCase(pageName) + textUtils.transformToCamelCase(ElementType.PAGE.toString()),
                ElementType.DROPDOWN,
                text);

        fillTextField(text, element, pageName);
    }

    public void clickOnCheckboxItem(String option, String element, String pageName) {

        invokeAction(Action.CLICK,
                textUtils.transformToCamelCase(option.concat(Constants.UNDERSCORE + element)),
                textUtils.transformToLowerCamelCase(pageName) + textUtils.transformToCamelCase(ElementType.PAGE.toString()),
                ElementType.CHECKBOX,
                Constants.EMPTY);
    }

    public WebElement isElementPresent(String element, String pageName, ElementType elementType) {

        return invokeAction(Action.WAIT_FOR_ELEMENT,
                textUtils.transformToCamelCase(element),
                textUtils.transformToLowerCamelCase(pageName) + textUtils.transformToCamelCase(ElementType.PAGE.toString()),
                elementType,
                Constants.EMPTY);
    }

    public boolean isElementDisabled(String element, String pageName, ElementType elementType) {

        return !invokeAction(Action.WAIT_FOR_ELEMENT,
                textUtils.transformToCamelCase(element),
                textUtils.transformToLowerCamelCase(pageName) + textUtils.transformToCamelCase(ElementType.PAGE.toString())
                , elementType,
                Constants.EMPTY).isEnabled();
    }

    public boolean isElementChoosed(String element, String pageName, ElementType elementType, String attribute, String optionChoosed) {
        return invokeAction(Action.WAIT_FOR_ELEMENT,
                textUtils.transformToCamelCase(element),
                textUtils.transformToLowerCamelCase(pageName) + textUtils.transformToCamelCase(ElementType.PAGE.toString()),
                elementType,
                Constants.EMPTY).getAttribute(attribute).contains(optionChoosed);
    }

    private WebElement invokeAction(Action action, String webElementName, String pageName, ElementType elementType, String text) {

        WebElement element = null;

        try {
            Object bean = appContext.getBean(pageName);
            Method method = bean.getClass().getMethod(Constants.GET_PREFIX + webElementName +
                    textUtils.transformToCamelCase(elementType.toString()));
            switch (action) {
                case CLICK:
                    clickOnElement((WebElement) method.invoke(bean));
                    break;
                case JAVASCRIPT_CLICK:
                    clickOnElementJavaScript((WebElement) method.invoke(bean));
                    break;
                case SEND_KEYS:
                    sendkeysOnElement((WebElement) method.invoke(bean), text);
                    break;
                case WAIT_FOR_ELEMENT:
                    element = wailUtils.waitForElementPresent((WebElement) method.invoke(bean));
                    break;
            }

        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | SecurityException
                | IllegalArgumentException e) {
        }

        return element;
    }

    public void selectNumberWithArrows(WebElement element, String number) {
        if(!number.isEmpty()) {
            while (!element.findElement(By.cssSelector("option[selected]")).getText().trim().split(" ")[0].equals(number)) {
                String text = element.findElement(By.cssSelector("option[selected]")).getText().trim().split(" ")[0];
                try {
                    if (Integer.valueOf(text) < Integer.valueOf(number)) {
                        element.click();
                        element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
                    }

                    if (Integer.valueOf(text) > Integer.valueOf(number)) {
                        element.click();
                        element.sendKeys(Keys.ARROW_UP, Keys.ENTER);
                    }
                } catch (NumberFormatException e) {
                    element.sendKeys(number);
                }
            }
        }
    }
}