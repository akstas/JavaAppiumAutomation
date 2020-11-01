package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid()){return;}
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
