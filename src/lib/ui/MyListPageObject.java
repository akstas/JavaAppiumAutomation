package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListPageObject extends MainPageObject{

    public static final String
            FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']" ;

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public MyListPageObject openFolderByName(String folderName)
    {
        String FOLDER_NAME = getFolderXpathByName(folderName);
        this.waitForElementPresentAndClick(
                FOLDER_NAME,
                "Cannot find created folder",
                10);
        return this;
    }
    private static String getFolderXpathByName(String folderName)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    public MyListPageObject swipeByArticleToDelete(String articleTitle)
    {
        waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getFolderXpathByName(articleTitle);
        this.swipeElementToLeft(
                articleXpath,
                "Cannot find saved article");
        waitForArticleToDisappearByTitle(articleTitle);
        return this;
    }
    private static String getArticleXpathByName(String articleTitle)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_NAME}", articleTitle);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle)
    {
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementNotPresent(articleXpath, "Saved article still present with title " + articleTitle, 15);
    }
    public void waitForArticleToAppearByTitle(String articleTitle)
    {
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementPresent(articleXpath, "Saved article still not present with title " + articleTitle, 15);
    }
}
