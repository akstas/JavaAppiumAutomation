package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;


public class ArticlePageObject extends MainPageObject{
    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    String TITLE = "id:org.wikipedia:id/view_page_title_text",
           FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
           OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
           OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
           ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
           MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
           MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
           CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
           TITLE_IN_LIST = "id:org.wikipedia:id/page_list_item_title";

    public WebElement waitForTitleElement()
    {
        return waitForElementPresent(TITLE, "Cannot find article title on page", 5);
    }
    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }
    public void swipeToFindElement()
    {
        this.swipeUpToElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
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
                    "//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName + "']",
                    "Cannot find titles id",
                    15
            );
        }
    }
    public void closeArticle()
    {
        this.waitForElementPresentAndClick(
                 CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }
    public WebElement waitForTitleElementInList()
    {
        return waitForElementPresent(TITLE_IN_LIST, "Cannot find article title on page", 5);
    }
    public String getArticleTitleInList()
    {
        WebElement titleElement = waitForTitleElementInList();
        return titleElement.getAttribute("text");
    }
}
