package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationOnSearchResult()
    {
        String searchValue = "Java";
        String attributeTitle = "Java (programming language)";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchValue)
                .clickByArticleWithSubstring(attributeTitle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String titleBeforeRotation = ArticlePageObject.getArticleTitle();
        rotateScreenLandscape();
        String titleAfterLandscapeRotation = ArticlePageObject.getArticleTitle();
        assertEquals("Article title have been changed after screen rotation",
                titleBeforeRotation, titleAfterLandscapeRotation);
        rotateScreenPortrait();
        String titleAfterPortraitRotation = ArticlePageObject.getArticleTitle();
        assertEquals("Article title have been changed after screen rotation",
                titleBeforeRotation, titleAfterPortraitRotation);
    }
    @Test
    public void testCheckSearchArticleInBackground()
    {
        String searchValue = "Java";
        String attributeTitle = "Object-oriented programming language";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchValue)
                .waitForSearchResult(attributeTitle);
        backgroundApp(2);
        searchPageObject.waitForSearchResult(attributeTitle);
    }
}
