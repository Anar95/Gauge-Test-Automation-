package imp;

import com.thoughtworks.gauge.Step;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class BrowserImp {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Step("<browserType> brauzeri aç ve keçid et <url>")

    public void openBrowser(String browserType, String url) {
        if (DriverManager.getDriver() == null) {
            driver = creatDriver(browserType);
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
    public void closeBrowser() {
        DriverManager.getDriver().quit();
    }

    private WebDriver creatDriver(String browserType) {
        WebDriver driver;

        switch (browserType.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--disable-notifications");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-notifications");
                driver = new EdgeDriver(edgeOptions);
                break;

            case "safari":
                // Safari driver binary yüklemeye ehtiyac yoxdur(macos built in)
                driver = new SafariDriver();
                break;

            //CI/CD serverinde meselen Jenkins de ve s serverlerde istifade ucun lazımdır
            // Headless rejimde brauser gorunmur lakin fonda işleyir

            case "chrome-headless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions headlessOptions = new ChromeOptions();
                headlessOptions.addArguments("--headless");
                headlessOptions.addArguments("--disable-gpu");
                headlessOptions.addArguments("--window-size-1920,1080");

                driver = new ChromeDriver(headlessOptions);
                break;

            default:
                throw new IllegalArgumentException(
                        "Desdeklenmeyen browser: " + browserType +
                                "\nMövcud seçimler: chrome, firefox, edge, safari, chrome-headless"
                );


        }

        return driver;

    }




}
