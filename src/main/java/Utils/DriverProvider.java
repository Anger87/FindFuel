package Utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverProvider {

    public static WebDriver initializeChromeDriver(String systemPropertiesFile) {
        System.setProperty("webdriver.chrome.driver", PropertiesReader.getPropertyValue("pathToChromeDriver", systemPropertiesFile));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driverChrome = new ChromeDriver(chromeOptions);
        driverChrome.manage().window().setSize(new Dimension(1920, 1080));
        return driverChrome;
    }

}
