package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    protected static String MY_LISTS;

    public void clickMyLists()
    {
        this.waitForElementPresentAndClick(
                MY_LISTS,
                "Cannot find navigation button to my list",
                5
        );
    }
}
