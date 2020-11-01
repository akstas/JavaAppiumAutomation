package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        String titleName = "Java (programming language)";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring(titleName);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();
        assertEquals("We see unxpected title" + titleName ,titleName, articleTitle);
    }
    @Test
    public void testSwipeArticle()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFindElement();
    }
    @Test
    public void testAddTwoArticleToOneFolderListEx5(){
        String searchFirstValue = "Java";
        String firstTitleText = "Java (programming language)";
        String searchSecondValue = "JavaScript";
        String secondTextElement = "Programming language";
        String folderName = "Learning programming";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchFirstValue)
                .clickByArticleWithSubstring(firstTitleText);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.addArticleToMyList(folderName, true);
        articlePageObject.closeArticle();
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchSecondValue)
                .clickByArticleWithSubstring(secondTextElement);
        articlePageObject.addArticleToMyList(folderName, false);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        myListPageObject
                .openFolderByName(folderName)
                .swipeByArticleToDelete(searchSecondValue);
        String articleTitle = articlePageObject.getArticleTitleInList();
        assertEquals("First document is not in the list",firstTitleText, articleTitle);
    }
}
