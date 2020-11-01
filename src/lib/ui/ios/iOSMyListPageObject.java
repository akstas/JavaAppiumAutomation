package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject {
    public iOSMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    static
    {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        SWIPE_ACTION_DELETE = "xpath://XCUIElementTypeButton[contains(@name,'swipe action delete')]";//"id:swipe action delete";
    }
}
