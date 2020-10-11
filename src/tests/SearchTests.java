package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testAmountOfNotEmptySearch()
    {
        String searchLineValue = "Linkin Park Diskography";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchLineValue);
        int amountOfSearchResults = searchPageObject.getAmmountOfFoundArticles();
        assertTrue("We found too few result",amountOfSearchResults > 0);
    }
    @Test
    public void testAmountOfEmptySearch()
    {
        String searchLineValue = "Android Kotlin";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchLineValue)
                .waitForEmptyResultsLabel()
                .assertThereIsNotResultsOfSearch();
    }
    @Test
    public void testCancelSearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .waitForCancelButtonToAppear()
                .clickCancelSearch()
                .waitForCancelButtonToDisapear();
    }
    @Test
    public void testSerchListEx3()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Android");
        int searchResultAmmountBefore =  searchPageObject.getAmmountOfFoundArticles();
        assertTrue("Articles not still on the list", searchResultAmmountBefore > 0);
        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNotResultsAfterCloseCancelOfSearch();
    }
}
