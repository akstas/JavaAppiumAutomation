import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;


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
}
