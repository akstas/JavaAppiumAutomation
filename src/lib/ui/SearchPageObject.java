package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject
{
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/fragment_search_results']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
            SEARCH_EMPTY_RESULT_ELEMENT_IMAGE = "xpath://*[@resource_id='org.wikipedia:id/search_empty_image']";

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
