package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject welcomePage = new WelcomePageObject(driver);
        welcomePage.waitForLearnMoreLink();
        welcomePage.clickForNextButton();
        welcomePage.waitForNewTextNewWaysToExploreText();
        welcomePage.clickForNextButton();
        welcomePage.waitForNewLinkAddOrEditPreferredLangText();
        welcomePage.clickForNextButton();
        welcomePage.waitForNewLinkLearnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();
    }
}
