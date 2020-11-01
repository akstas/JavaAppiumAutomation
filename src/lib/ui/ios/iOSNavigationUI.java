package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {

    public iOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    static { MY_LISTS = "id:Saved"; }

}
