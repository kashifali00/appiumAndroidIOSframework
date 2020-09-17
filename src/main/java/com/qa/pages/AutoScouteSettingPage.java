package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AutoScouteSettingPage {

    BaseTest objBaseTest;

    public AutoScouteSettingPage(){
        objBaseTest = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(objBaseTest.getDriver()), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\" Log in\")")
    private MobileElement login;

    public AutoScouteSettingPage login(){
        objBaseTest.Click(login);
        return this;
    }

    public String getLoginText(String text){
        return objBaseTest.getAttribute(login, text);
    }


}
