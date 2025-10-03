package imp;

import com.thoughtworks.gauge.Step;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class BrowserImp {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Step("Brauzeri aç ve keçid et <url>")

    public  void  openBrowser(String url){
        if(DriverManager.getDriver()==null){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            DriverManager.setDriver(driver); // Globalda saxlayırıq
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            driver.manage().window().maximize();
        } else {
            driver = DriverManager.getDriver(); //Movcud drıverı goturmek ucun
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }

        driver.get(url);
    }


    @Step("Brauzeri bağla")
    public void  closeBrowser(){
        DriverManager.getDriver().quit();
    }
}
