package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject
{
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT_IMAGE;

    public SearchPageObject initSearchInput()
    {
        waitForElementPresentAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element",5);
        waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element", 5);
        return this;
    }
    public SearchPageObject typeSearchLine(String searchLine)
    {
        waitForElementPresentByIdAndSendKeys(SEARCH_INPUT, searchLine,"Cannot find and type into search input",5);
        return this;
    }
    public SearchPageObject waitForSearchResult(String substring)
    {
        String searchResultXpath = getResultSearchElement(substring);
        waitForElementPresent(searchResultXpath, "Cannot find search result with substring : " + substring, 5);
        return this;
    }
    /*TEMPLATES METHOD*/
    private String getResultSearchElement(String substring)
    {
          return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHOD*/
    public SearchPageObject waitForCancelButtonToAppear()
    {
        waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find X to cancel search",5);
        return this;
    }
    public SearchPageObject clickCancelSearch()
    {
        this.waitForElementPresentAndClick(SEARCH_CANCEL_BUTTON, "Cannot click to X element", 5);
        return this;
    }
    public SearchPageObject waitForCancelButtonToDisapear()
    {
        waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Element X present on the page",5);
        return this;
    }
    public SearchPageObject clickByArticleWithSubstring(String substring)
    {
        String searchResultXpath = getResultSearchElement(substring);
        waitForElementPresentAndClick(searchResultXpath, "Cannot find and click result with substring : " + substring, 10);
        return this;
    }
    public int getAmmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElement(SEARCH_RESULT_ELEMENT);
    }
    public SearchPageObject waitForEmptyResultsLabel()
    {
        waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 5);
        return this;
    }
    public SearchPageObject assertThereIsNotResultsOfSearch()
    {
        waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results", 5);
        return this;
    }
    public SearchPageObject assertThereIsNotResultsAfterCloseCancelOfSearch()
    {
        waitForElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT_IMAGE, "We supposed not to find any results", 5);
        return this;
    }
}
