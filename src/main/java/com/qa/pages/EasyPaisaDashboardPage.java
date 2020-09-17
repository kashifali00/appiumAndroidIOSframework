package com.qa.pages;

import com.qa.BaseTest;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.*;

public class EasyPaisaDashboardPage {
    BaseTest objBaseTest;
    public EasyPaisaDashboardPage(){
        objBaseTest = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(objBaseTest.getDriver()), this);
    }
}
