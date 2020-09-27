import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirstTest extends BaseTest{

    @Test
    public void firstTest()
    {
        waitForElementPresentAndClick(
            By.xpath("//*[contains(@text, 'SKIP')]"),
            "Cannot find SKIP button",
            5
        );
        waitForElementPresentAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
            5
        );
        waitForElementPresentByIdAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            "Android Kotlin",
            "Cannot find search input",
            5
        );
        waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='General-purpose programming language']"),
            "Connot find 'General-purpose programming language' topic searching by 'Android Kotlin'",
            5
        );
    }
    @Test
    public void testCancelSearch()
    {
        waitForElementPresentAndClick(
            By.xpath("//*[contains(@text, 'SKIP')]"),
            "Cannot find SKIP button",
            5
        );
        waitForElementPresentAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia fild",
            5
        );
        waitForElementPresentByIdAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            " ",
            "Cannot find search input",
            5
        );
        waitForElementPresent(
            By.id("org.wikipedia:id/search_close_btn"),
            "Element X not present on the page",
            5
        );
        waitAndClearFildSearch(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot find search input",
            5
        );
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                " ",
                "Cannot find search input",
                5
        );
        waitForElementPresentAndClick(
            By.id("org.wikipedia:id/search_close_btn"),
            "",
            5
        );
        waitForElementNotPresent(
            By.id("org.wikipedia:id/search_close_btn"),
            "Element X present on the page",
            5
        );
    }
    @Test
    public void testCompareArticleTitle()
    {
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP button",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
                5
        );
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android Kotlin",
                "Cannot find search input",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='General-purpose programming language']"),
                "Connot find 'General-purpose programming language' topic searching by 'Android Kotlin'",
                5
        );

        WebElement titleElement = waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find titles id",
                15
        );

        String articleTitle = titleElement.getAttribute("contentDescription");
        String s = articleTitle;

        Assert.assertEquals(
                "We see unxpected title",
                "General-purpose programming language",
                articleTitle
        );
    }
    
    @Test
    public void Ex2()
    {
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP button",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
                5
        );
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android Kotlin",
                "Cannot find search input",
                5
        );

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Android Kotlin",
                "The search string does not contain the expected text "
        );
    }
    private void assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        String actualTitle = element.getAttribute("text");
        Assert.assertEquals(errorMessage, expectedText, actualTitle);
    }
    private  WebElement waitForElementPresent(By by, String errorMessage, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private  WebElement waitForElementPresentAndClick(By by, String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }
    private  WebElement waitForElementPresentByIdAndSendKeys(By by, String value ,String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeOutInMessage)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInMessage);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    private WebElement waitAndClearFildSearch(By by ,String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

}
