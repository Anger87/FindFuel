package tests.stations;

import Utils.DriverProvider;
import Utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTest {
    WebDriver driver;

    @BeforeSuite
    @Parameters({"systemPropertiesFile"})
    public void init( String systemPropertiesFile) {
        systemPropertiesFile = PropertiesReader.setVariable("systemPropertiesFile", systemPropertiesFile);
        driver = DriverProvider.initializeChromeDriver(systemPropertiesFile);
    }


}
