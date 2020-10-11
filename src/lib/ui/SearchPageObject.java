package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/fragment_search_results']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            SEARCH_EMPTY_RESULT_ELEMENT_IMAGE = "//*[@resource_id='org.wikipedia:id/search_empty_image']";

    public SearchPageObject initSearchInput()
    {
        waitForElementPresentAndClick(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find and click search init element",5);
        waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find search input after clicking search init element", 5);
        return this;
    }
    public SearchPageObject typeSearchLine(String searchLine)
    {
        waitForElementPresentByIdAndSendKeys(By.id(SEARCH_INPUT), searchLine,"Cannot find and type into search input",5);
        return this;
    }
    public SearchPageObject waitForSearchResult(String substring)
    {
        String searchResultXpath = getResultSearchElement(substring);
        waitForElementPresent(By.xpath(searchResultXpath), "Cannot find search result with substring : " + substring, 5);
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
        waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find X to cancel search",5);
        return this;
    }
    public SearchPageObject clickCancelSearch()
    {
        this.waitForElementPresentAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot click to X element", 5);
        return this;
    }
    public SearchPageObject waitForCancelButtonToDisapear()
    {
        waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Element X present on the page",5);
        return this;
    }
    public SearchPageObject clickByArticleWithSubstring(String substring)
    {
        String searchResultXpath = getResultSearchElement(substring);
        waitForElementPresentAndClick(By.xpath(searchResultXpath), "Cannot find and click result with substring : " + substring, 10);
        return this;
    }
    public int getAmmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElement(By.xpath(SEARCH_RESULT_ELEMENT));
    }
    public SearchPageObject waitForEmptyResultsLabel()
    {
        waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 5);
        return this;
    }
    public SearchPageObject assertThereIsNotResultsOfSearch()
    {
        waitForElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results", 5);
        return this;
    }
    public SearchPageObject assertThereIsNotResultsAfterCloseCancelOfSearch()
    {
        waitForElementNotPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT_IMAGE), "We supposed not to find any results", 5);
        return this;
    }
}
