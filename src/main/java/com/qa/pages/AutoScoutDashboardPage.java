package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AutoScoutDashboardPage {
    BaseTest objBaseTest;

    public AutoScoutDashboardPage(){
        objBaseTest = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(objBaseTest.getDriver()), this);
    }

    @AndroidFindBy(accessibility = "Navigate up")
    private MobileElement navigateUp;

    @AndroidFindBy(accessibility = "com.autoscout24:id/home_header_search_button")
    private MobileElement searchBar;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc=\"HOMESCREEN\"]/android.widget.TextView")
    private MobileElement discoverMenu;

    public AutoScoutDashboardPage clickOnDiscover(){
        objBaseTest.Click(discoverMenu);
        return this;
    }

    public AutoScouteLoginPage clickOnNavigateUpButton(){
        System.out.println("*****  Executing AutoScoutNavigateUpButton *****");
        objBaseTest.Click(navigateUp);
        return new AutoScouteLoginPage();
    }

    public String isNavigateButtonVisible(String text){
        return objBaseTest.getAttribute(navigateUp,text);
    }

    public void waitForSearchBarVisiblity(){
        objBaseTest.waitForVisibility(searchBar);

    }

}
