package helper;

import imp.BrowserImp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import utils.LocatorReader;

import java.time.Duration;

import static imp.BrowserImp.driver;

public class ScrollHelper {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public ScrollHelper() {
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.driver = DriverManager.getDriver();
    }


    public void scrollToElement(String elementName)  {
        try {
            String type = LocatorReader.getLocatorType(elementName);
            String value = LocatorReader.getLocatorValue(elementName);

            By by = getBy(type, value);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            js.executeScript("arguments[0].scrollIntoView()", element);
            // js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", element);
            // smooth ile daha hamar scroll ede bilerik, block center ile elementi düz ekranın ortasında saxlayır.

            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Elemente scroll uğursuz oldu: " + elementName, e);
        }


    }

    public  void scrollToElelemntAndClick(String elementName) {

        try {
            scrollToElement(elementName);

            String type = LocatorReader.getLocatorType(elementName);
            String value = LocatorReader.getLocatorValue(elementName);

            By by = getBy(type, value);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            element.click();


            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Elemente scroll ve klik uğursuz oldu: " + elementName, e);
        }


    }

    public  void  scrollToTop()  {
        try {

            js.executeScript("window.scrollTop({top:0, behavior:'smooth'});");
            Thread.sleep(500);
        }catch (Exception e){
            throw new RuntimeException("Sehifenin yuxarısına scroll uğursuz oldu: " + e);

        }
    }


    public  void scrollToBottom()  {
        try {

            js.executeScript("window.scrollTo({left: ' + ', behavior:'smooth'});");
            Thread.sleep(500);
        }catch (Exception e){
            throw new RuntimeException("Sehifenin yuxarısına scroll uğursuz oldu: " + e);

        }
    }


    public  void scrollHorizontallyByPixels(int pixels){
        try {

            js.executeScript("window.scrollBy({left:" + pixels + " , behavior:'smooth'});");
            Thread.sleep(500);
        }catch (Exception e){
            throw new RuntimeException("Horizontal scroll uğursuz oldu : " + pixels +" pixels" + e);

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