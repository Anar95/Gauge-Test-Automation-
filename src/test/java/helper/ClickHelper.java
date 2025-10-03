package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import utils.LocatorReader;

import java.sql.Driver;
import java.time.Duration;


public class ClickHelper {

    WebDriver driver;
    WebDriverWait wait;


    public ClickHelper() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    public  void ClickElement (String jsonKey) {
        try {
            String type = LocatorReader.getLocatorType(jsonKey);
            String value = LocatorReader.getLocatorValue(jsonKey);
            By by = getBy(type, value);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.click();
        } catch (Exception e){
            throw new RuntimeException("Element click fail oldu" + jsonKey,e);
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
