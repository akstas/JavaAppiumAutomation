package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    public AndroidNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    static { MY_LISTS = "xpath://android.widget.FrameLayout[@content-desc='My lists']";}

}
