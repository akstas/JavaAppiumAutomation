package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        String folderName = "Learning programming";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid())
        {
            articlePageObject.addArticleToMyList(folderName, true);
        }else {
            articlePageObject.addArticleToMySaved();
            if(articlePageObject.checkScreenSyncYourPreferences())
            {
                articlePageObject.clickCloseSyncYourPreferences();
            }
        }
        articlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(folderName);
        } else{
            myListPageObject.swipeByArticleToDelete(articleTitle);
        }
    }
}
