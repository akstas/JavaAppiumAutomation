package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;


abstract public class ArticlePageObject extends MainPageObject
{
    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            TITLE_IN_LIST,
            SYNC_YOUR_PREFERENCES_CLOSE,
            MY_LIST_FOLDER_NAME;

    public WebElement waitForTitleElement()
    {
        return waitForElementPresent(TITLE_IN_LIST, "Cannot find article title on page", 10);
    }
    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        if(Platform.getInstance().isAndroid())
        {
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }
    public void swipeToFindElement()
    {
        if(Platform.getInstance().isAndroid())
        {
            this.swipeUpToElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }else {
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }

    }
    public void addArticleToMyList(String folderName, boolean createNewFolder)
    {
        this.waitForElementPresentAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article option",
                5
        );
        this.waitForElementPresentAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
        if(createNewFolder)
        {
            this.waitForElementPresentAndClick(

                    ADD_TO_MY_LIST_OVERLAY,
                    "Cannot find 'Got it' tip overlay",
                    5
            );
            this.waitForElementAndClear(
                    MY_LIST_NAME_INPUT,
                    "Cannot find input to set name of articles folder",
                    5
            );
            this.waitForElementPresentByIdAndSendKeys(
                    MY_LIST_NAME_INPUT,
                    folderName,
                    "Cannot put text into articles folder input",
                    5
            );
            this.waitForElementPresentAndClick(
                    MY_LIST_OK_BUTTON,
                    "Cannot press OK button",
                    5
            );
        }else
        {
            waitForElementPresentAndClick(
                    MY_LIST_FOLDER_NAME,
                    "Cannot find titles id",
                    15
            );
        }
    }
    public void closeArticle()
    {
        this.waitForElementPresentAndClick(CLOSE_ARTICLE_BUTTON,"Cannot close article, cannot find X link",5);
    }
    public WebElement waitForTitleElementInList()
    {
        return waitForElementPresent(TITLE_IN_LIST, "Cannot find article title on page", 5);
    }
    public String getArticleTitleInList()
    {
        WebElement titleElement = waitForTitleElement();
        if(Platform.getInstance().isAndroid())
        {
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("value");
        }
    }
    public void addArticleToMySaved()
    {
        this.waitForElementPresentAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to read list", 5);
    }
    public boolean checkScreenSyncYourPreferences()
    {
        return isElementLocatedOnTheScreen(SYNC_YOUR_PREFERENCES_CLOSE);
    }
    public void clickCloseSyncYourPreferences()
    {
        this.waitForElementPresentAndClick(SYNC_YOUR_PREFERENCES_CLOSE,"Cannot click by preferences close",5);
    }
}
