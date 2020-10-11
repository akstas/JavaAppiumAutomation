package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject{

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']" ;//"//*[@text='Kotlin (programming language)']"

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String folderName)
    {
        String FOLDER_NAME = getFolderXpathByName(folderName);
        this.waitForElementPresentAndClick(
                By.xpath(FOLDER_NAME),
                "Cannot find created folder",
                10
        );
    }
    private static String getFolderXpathByName(String folderName)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    public void swipeByArticleToDelete(String articleTitle)
    {
        waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getFolderXpathByName(articleTitle);
        this.swipeElementToLeft(
                By.xpath(articleXpath), //("//*[@text='Kotlin (programming language)']"),
                "Cannot find saved article"
        );
        waitForArticleToDisappearByTitle(articleTitle);
    }
    private static String getArticleXpathByName(String articleTitle)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_NAME}", articleTitle);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle)
    {
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementNotPresent(By.xpath(articleXpath), "Saved article still present with title " + articleTitle, 15);
    }
    public void waitForArticleToAppearByTitle(String articleTitle)
    {
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementPresent(By.xpath(articleXpath), "Saved article still not present with title " + articleTitle, 15);
    }
}