package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.LocatorReader;

import static imp.BrowserImp.wait;

public class TypeHelper {

public  void typeAndPressKey(String elementName,String text, String keyName){

    try {
        String type = LocatorReader.getLocatorType(elementName);
        String value = LocatorReader.getLocatorValue(elementName);
        By by = getBy(type,value);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));


        if (keyName.equalsIgnoreCase("ENTER")){
            element.sendKeys(Keys.ENTER);
        } else if (keyName.equalsIgnoreCase("TAB")) {
            element.sendKeys(Keys.TAB);

        } // burdan sonra else if deyerek diger buttonları yaza bilerik


    } catch (Exception e) {
        throw new RuntimeException("Error while typing into: " + elementName, e);

    }

}

    private By getBy(String type, String value) throws IllegalAccessException {
        switch (type.toUpperCase()) {

            case "ID": return By.id(value);
            case "XPATH": return By.xpath(value);
            case "CSS_SELECTOR": return By.cssSelector(value);
            case "NAME": return By.name(value);
            case "LINK_TEXT": return By.linkText(value);
            case "PARTIAL_LINK_TEXT": return By.partialLinkText(value);
            case "CLASS_NAME": return By.className(value);
            case "TAGNAME": return By.tagName(value);
            default: throw  new IllegalAccessException("Invalid locator type " + type);
        }
    }

}
