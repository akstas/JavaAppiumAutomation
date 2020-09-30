import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        waitForElementAndClear(
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
    public void testSwipeArticle()
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
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Android Studio']"),
                "Connot find 'General-purpose programming language' topic searching by 'Android Kotlin'",
                5
        );
        swipeUpToElement(
                By.xpath("//*[@content-desc='View article in browser']"),
                "Cannot the end by article",
                10
        );
    }

    @Test
    public void saveFirstArticleToMyList()
    {
        String folderName = "Learning programming";
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
                5
        );
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Kotlin",
                "Cannot find search input",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Kotlin (programming language)']"),
                "Connot find 'Kotlin (programming language)' topic searching by 'Android Kotlin'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='Kotlin (programming language)']"),
                "Cannot find titles id",
                15
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
               "Cannot find button to open article option",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        waitForElementPresentAndClick(

                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        waitForElementAndClear(
               By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot put text into articles folder input",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot press OK button",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@text='"+ folderName +"']"),
                "Cannot find created folder",
                10
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Kotlin (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Kotlin (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
                5
        );
        String searchValue = "Linkin Park Diskography";
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchValue,
                "Cannot find search input",
                5
        );
        String searchResultLocator = "org.wikipedia:id/page_list_item_title";
        waitForElementPresent(
                By.id(searchResultLocator),
                "Cannot find anythig by the request : " + searchValue,
                15
        );
        int amountOfSearchResults = getAmountOfElement(By.id(searchResultLocator));
        Assert.assertTrue("We found too few result",amountOfSearchResults > 0);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResult()
    {
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
                5
        );
        String searchValue = "Java";
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchValue,
                "Cannot find search input : " + searchValue ,
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Connot find 'Java (programming language)' topic searching by :" + searchValue,
                5
        );
        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals("Article title have been changed after screen rotation",
                                                        titleBeforeRotation, titleAfterRotation);
        driver.rotate(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals("Article title have been changed after screen rotation",
                titleBeforeRotation, titleAfterSecondRotation);

    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
                5
        );
        String searchValue = "Java";
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchValue,
                "Cannot find search input : " + searchValue ,
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Connot find 'Java (programming language)' topic searching by Object-oriented programming language",
                5
        );

        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Connot find Artical after returned background",
                5
        );
    }

    @Test
    public void Ex5(){
        String searchValue = "Java";
        String folderName = "Learning programming";
        String firstTitleText = "Java (programming language)";
        String firstXpathTextElement = "Object-oriented programming language";
        String secondTitleText = "JavaScript";
        String secondXpathTextElement = "Programming language";
        clickSerchFildAndEnterValue(searchValue);
        clickByToSearchResult(firstXpathTextElement, firstTitleText);
        addArticleToList(folderName, true);
        clickSerchFildAndEnterValue(searchValue);
        clickByToSearchResult(secondXpathTextElement, secondTitleText);
        addArticleToList(folderName, false);
        goToMyList(folderName);
        openMyListFolder(folderName);
        validateBeforeDelete(firstTitleText, secondTitleText);
        delArticleToList(secondTitleText);
        validateAfterDelete(firstTitleText, secondTitleText);
        openArticle(firstTitleText);
        validateOpenArticleTitle(firstTitleText);

    }

    private void validateOpenArticleTitle(String firstTitleText) {
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='"+ firstTitleText +"']"),
                "Article does not match expected : " + firstTitleText,
                15
        );
    }

    private void openArticle(String firstTitleText){
        waitForElementPresentAndClick(
                By.xpath("//*[@text='" + firstTitleText + "']"),
                "Cannot open article : " + firstTitleText,
                5
        );

    }
    private void validateAfterDelete(String firstTitleText, String secondTitleText) {
        waitForElementPresent(
                By.xpath("//*[@text='" + firstTitleText + "']"),
                "Not present article : " + firstTitleText,
                5
        );
                waitForElementNotPresent(
                By.xpath("//*[@text='" + secondTitleText + "']"),
                "Cannot delete saved article : " + secondTitleText,
                5
        );
    }

    private void validateBeforeDelete(String firstTitleText, String secondTitleText) {
        waitForElementPresent(
                By.xpath("//*[@text='" + firstTitleText + "']"),
                "Not present article: " + firstTitleText,
                5
        );
        waitForElementPresent(
                By.xpath("//*[@text='" + secondTitleText + "']"),
                "Not present article : " + secondTitleText,
                5
        );
    }

    private void clickSerchFildAndEnterValue(String searchValue) {
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
                5
        );
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchValue,
                "Cannot find search input : " + searchValue ,
                5
        );
    }
    private void openMyListFolder(String folderName) {
        waitForElementPresentAndClick(
                By.xpath("//*[@text='" + folderName + "']"),
                "Cannot find created folder",
                10
        );
    }
    private void delArticleToList(String itemTitleText){

        swipeElementToLeft(
                By.xpath("//*[@text='" + itemTitleText + "']"),
                "Cannot find saved article"
        );
    }
    private void clickByToSearchResult(String subtitelText, String xpathTextElement){
        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='" + subtitelText + "']"),
                "Connot find : " + subtitelText ,
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='"+ xpathTextElement +"']"),
                "Cannot find titles id",
                15
        );
    }
    private void addArticleToList(String folderName, boolean createNewFolder){

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article option",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        if(createNewFolder)
        {
            createNewListFolder();

        }
        else
            {
                waitForElementPresentAndClick(
                        By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName + "']"),
                        "Cannot find titles id",
                        15
                );
            }

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );
    }

    private void goToMyList(String folderName){
        waitForElementPresentAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot press OK button",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@text='"+ folderName +"']"),
                "Cannot find created folder",
                10
        );
    }
    private void createNewListFolder() {

        waitForElementPresentAndClick(

                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot put text into articles folder input",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

    }




    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSecounds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSecounds);
        return element.getAttribute(attribute);
    }

    private int getAmountOfElement(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void swipeElementToLeft(By by, String errorMessage){
        WebElement element = waitForElementPresent(
                by,
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
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    private void swipeUpToElement(By by, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0){
            if (alreadySwiped > maxSwipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }
    private void swipeUpQuick() {

        swipeUp(200);

    }
    private void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
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
    private  WebElement waitForElementPresent(By by, String errorMessage, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private boolean waitForElementNotPresent(By by, String errorMessage, long timeOutInMessage)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInMessage);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    private WebElement waitForElementAndClear(By by ,String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

}
