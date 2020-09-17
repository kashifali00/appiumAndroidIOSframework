package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AutoScouteLogoutPage {

    BaseTest objBaseTest;

    public AutoScouteLogoutPage(){
        objBaseTest = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(objBaseTest.getDriver()), this);
    }

    @AndroidFindBy(id = "com.autoscout24:id/fragment_fullregistration_button_logout")
    private MobileElement logoutButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\" Log out\")")
    private MobileElement logout;

    public AutoScouteLogoutPage scrollDown(){
        objBaseTest.Scroll(logout);
        return this;
    }
    public AutoScoutDashboardPage clikOnLogoutButton(){

        objBaseTest.Click(logoutButton);
        return new AutoScoutDashboardPage();
    }
}
