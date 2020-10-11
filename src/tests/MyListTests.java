package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase
{
    @Test
    public void testsaveFirstArticleToMyList()
    {
        String folderName = "Learning programming";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Kotlin")
                .clickByArticleWithSubstring("Programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyList(folderName);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openFolderByName(folderName);
        myListPageObject.swipeByArticleToDelete(articleTitle);
    }
}
