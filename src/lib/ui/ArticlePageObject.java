package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ArticlePageObject extends MainPageObject{
    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    String TITLE = "org.wikipedia:id/view_page_title_text",
           FOOTER_ELEMENT = "//*[@text='View page in browser']",
           OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
           OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
           ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
           MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
           MY_LIST_OK_BUTTON = "//*[@text='OK']",
           CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
           TITLE_IN_LIST = "org.wikipedia:id/page_list_item_title";


    public WebElement waitForTitleElement()
    {
        return waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 5);
    }
    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }
    public void swipeToFindElement()
    {
        this.swipeUpToElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);
    }
    public void addArticleToMyList(String folderName, boolean createNewFolder)
    {
        this.waitForElementPresentAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article option",
                5
        );
        this.waitForElementPresentAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );
        if(createNewFolder)
        {
            this.waitForElementPresentAndClick(

                    By.id(ADD_TO_MY_LIST_OVERLAY),
                    "Cannot find 'Got it' tip overlay",
                    5
            );
            this.waitForElementAndClear(
                    By.id(MY_LIST_NAME_INPUT),
                    "Cannot find input to set name of articles folder",
                    5
            );
            this.waitForElementPresentByIdAndSendKeys(
                    By.id(MY_LIST_NAME_INPUT),
                    folderName,
                    "Cannot put text into articles folder input",
                    5
            );
            this.waitForElementPresentAndClick(
                    By.xpath(MY_LIST_OK_BUTTON),
                    "Cannot press OK button",
                    5
            );
        }else
        {
            waitForElementPresentAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName + "']"),
                    "Cannot find titles id",
                    15
            );
        }
    }
    public void closeArticle()
    {
        this.waitForElementPresentAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5
        );
    }
    public WebElement waitForTitleElementInList()
    {
        return waitForElementPresent(By.id(TITLE_IN_LIST), "Cannot find article title on page", 5);
    }
    public String getArticleTitleInList()
    {
        WebElement titleElement = waitForTitleElementInList();
        return titleElement.getAttribute("text");
    }
}
