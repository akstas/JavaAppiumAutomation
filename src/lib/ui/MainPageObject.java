package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSecounds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSecounds);
        return element.getAttribute(attribute);
    }
    public int getAmountOfElement(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void swipeElementToLeft(String locator, String errorMessage){
        WebElement element = waitForElementPresent(
                locator,
                errorMessage,
                10
        );
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(rightX, middleY))
                .waitAction(WaitOptions.waitOptions((Duration.ofMillis(300))))
                .moveTo(PointOption.point(leftX, middleY))
                .release()
                .perform();
    }
    public void swipeUpToElement(String locator, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).size() == 0){
            if (alreadySwiped > maxSwipes){
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }
    public void swipeUpQuick() {

        swipeUp(200);

    }
    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action
                .press(PointOption.point(x, startY))
                .waitAction(WaitOptions.waitOptions((Duration.ofMillis(timeOfSwipe))))
                .moveTo(PointOption.point(x, endY))
                .release()
                .perform();
    }
    public  WebElement waitForElementPresentAndClick(String locator, String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }
    public  WebElement waitForElementPresentByIdAndSendKeys(String locator, String value ,String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }
    public  WebElement waitForElementPresent(String locator, String errorMessage, long timeOutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeOutInMessage)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInMessage);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public WebElement waitForElementAndClear(String locator ,String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    private By getLocatorByString(String locatorWithType)
    {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")){return By.xpath(locator);}
        else if (byType.equals("id")){return By.id(locator);}
        else if (byType.equals("name")){return By.name(locator);}
        else{ throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);}
    }
}
