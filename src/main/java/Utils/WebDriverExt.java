package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class WebDriverExt {

    private Logger log = LogManager.getLogger(WebDriverExt.class);

    ///=====Work with Web============================================================================

    public void clearField(WebDriver driver, By framelocator, int timeout) {
        WebElement webElement = (new WebDriverWait(driver, timeout))
                .until(visibilityOfElementLocated(framelocator));
        webElement.clear();

    }


    public void typeStringIntoField(WebDriver driver, By framelocator, String text, int timeout) {

        WebElement webElement = (new WebDriverWait(driver, timeout))
                .until(visibilityOfElementLocated(framelocator));
        webElement.sendKeys(text);

    }


    public void clickByButton(WebDriver driver, By framelocator, int timeout) {
        WebElement webElement = (new WebDriverWait(driver, timeout))
                .until((elementToBeClickable(framelocator)));
        webElement.click();
    }

    public void clickByElement(WebDriver driver, By framelocator, int timeout) {
        WebElement webElement = (new WebDriverWait(driver, timeout))
                .until(visibilityOfElementLocated(framelocator));
        webElement.click();
    }

    public WebElement getElement(WebDriver driver, By framelocator,
                                 int timeout) {
        WebElement findWebElement;

        findWebElement = (new WebDriverWait(driver, timeout))
                .until(visibilityOfElementLocated(framelocator));

        if (findWebElement != null) {
            return findWebElement;
        } else {
            throw new NoSuchElementException(" can't find target element with locator: " + framelocator);
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String ElementName, By locator, int timeout) {
        log.info("Checking if " + ElementName + " is visible");
        try {
            new WebDriverWait(driver, timeout).until((visibilityOfElementLocated(locator)));
        } catch (Exception e) {
            log.info(ElementName + " not displayed by locator " + locator);
            return false;
        }
        log.info(ElementName + " is displayed");
        return true;
    }

    public void scrollToElement(WebDriver driver, String ElementName, By locator) {
        WebElement elem = getElement(driver, locator, 30);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elem);
        log.info("scrolling to " + ElementName + " is DONE");
    }

    public List<WebElement> getElements(WebDriver driver, By framelocator, int timeout) {
        List<WebElement> findWebElements;

        findWebElements = (new WebDriverWait(driver, timeout))
                .until(visibilityOfAllElementsLocatedBy(framelocator));

        if (findWebElements != null) {
            return findWebElements;
        } else {
            throw new NoSuchElementException(" can't find target elements list with locator: " + framelocator);
        }

    }

    public void selectValue(WebDriver driver, By framelocator, String text, int timeout) {
        WebElement webElement = (new WebDriverWait(driver, timeout))
                .until(visibilityOfElementLocated(framelocator));
        Select selector = new Select(webElement);
        selector.selectByValue(text);
    }

    public Date stringToDate(String str, String strFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(strFormat);
        return format.parse(str);
    }

    public String getMonthFromDate(String date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        return format1.format(stringToDate(date, "yyyy-MM-dd").getTime());
    }

    public String getDayFromDate(String date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("d");
        return format1.format(stringToDate(date, "yyyy-MM-dd").getTime());
    }
}
