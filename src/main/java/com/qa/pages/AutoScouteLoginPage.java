package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AutoScouteLoginPage {

    BaseTest objBaseTest;

    public AutoScouteLoginPage(){
        objBaseTest = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(objBaseTest.getDriver()), this);
    }

    @AndroidFindBy(id = "com.autoscout24:id/login_form_password")
    private MobileElement password;

    @AndroidFindBy(id = "com.autoscout24:id/login_form_user_name")
    private MobileElement username;

    @AndroidFindBy(id = "com.autoscout24:id/fragment_login_button_login")
    private MobileElement login;

    @AndroidFindBy(id = "com.autoscout24:id/drawer_item_login_text")
    private MobileElement loginButton;

    @AndroidFindBy(id = "com.autoscout24:id/drawer_item_login_mail")
    private MobileElement loggedUserName;


    public String getLoginButtonText(String text){
        return objBaseTest.getAttribute(loginButton, text);
    }

    public String getLoggedUserName(String text){
        return objBaseTest.getAttribute(loggedUserName, text);
    }

    public AutoScouteLogoutPage clickOnLoggedInUser(){
        objBaseTest.Click(loggedUserName);
        return new AutoScouteLogoutPage();
    }

    public AutoScouteLoginPage clickLogin(){
        objBaseTest.Click(loginButton);
        return new AutoScouteLoginPage();
    }


    public AutoScouteLoginPage sendPassword(String pass){
        objBaseTest.SendKeys(password,pass);
        return this;
    }

    public AutoScouteLoginPage sendUserName(String userName){
        objBaseTest.SendKeys(username,userName);
        return this;
    }

    public void clickLoginButton(){
        objBaseTest.Click(login);
    }

    public String getLoginText(String attr){
        return objBaseTest.getAttribute(username,attr);
    }
}
