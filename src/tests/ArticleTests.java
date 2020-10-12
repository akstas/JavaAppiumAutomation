package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Android")
                .clickByArticleWithSubstring("Open-source operating system for mobile devices created by Google");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String articleTitle = articlePageObject.getArticleTitle();
        assertEquals("We see unxpected title","Android (operating system)", articleTitle);
    }
    @Test
    public void testSwipeArticle()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Appium")
                .clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.swipeToFindElement();
    }
    @Test
    public void testAddTwoArticleToOneFolderListEx5(){
        String searchFirstValue = "Java";
        String firstTitleText = "Java (programming language)";
        String searchSecondValue = "JavaScript";
        String secondTextElement = "Programming language";
        String folderName = "Learning programming";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchFirstValue)
                .clickByArticleWithSubstring(firstTitleText);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.addArticleToMyList(folderName, true);
        articlePageObject.closeArticle();
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchSecondValue)
                .clickByArticleWithSubstring(secondTextElement);
        articlePageObject.addArticleToMyList(folderName, false);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject
                .openFolderByName(folderName)
                .swipeByArticleToDelete(searchSecondValue);
        String articleTitle = articlePageObject.getArticleTitleInList();
        assertEquals("First document is not in the list",firstTitleText, articleTitle);
    }
}
