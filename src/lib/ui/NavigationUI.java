package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    String MY_LISTS = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public void clickMyLists()
    {
        this.waitForElementPresentAndClick(
                MY_LISTS,
                "Cannot find navigation button to my list",
                5
        );
    }
}
