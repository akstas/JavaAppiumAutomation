import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class FirstTest extends CoreTestCase {

    @Test

    public void testfirstTest()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Kotlin")
                .waitForSearchResult("Programming language");
    }

    @Test
    public void testSerchListEx3()
    {
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP button",
                5
        );
        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia fild",
                5
        );
        waitForElementPresentByIdAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android",
                "Cannot find search input",
                5
        );

        By elementId = By.id("org.wikipedia:id/search_results_list");
        Assert.assertTrue("Item not in the list",searchResult(elementId) > 0);
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "",
                5
        );

        Assert.assertTrue("After canceling the search, articles are still on the list", searchResult(elementId) == 0);
    }
    public int searchResult(By by)
    {
        List<WebElement> elementsList1 = driver.findElements(by);
        return elementsList1.size();
    }
}
