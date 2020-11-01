import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;


public class FirstTest extends CoreTestCase {

    @Test
    public void testfirstTest()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Kotlin")
                .waitForSearchResult("Programming language");
    }
}
