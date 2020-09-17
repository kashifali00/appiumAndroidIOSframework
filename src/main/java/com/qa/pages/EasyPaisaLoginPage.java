package com.qa.pages;
import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class EasyPaisaLoginPage {
    BaseTest objBaseTest;

    public EasyPaisaLoginPage(){
        objBaseTest = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(objBaseTest.getDriver()), this);
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`value == \"03XXXXXXXX\"`]") private MobileElement msisdn;

    @iOSXCUITFindBy(id ="Welcome to Easypaisa") private MobileElement welcomeText;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Proceed\"`]") private MobileElement proceedButton;

    @iOSXCUITFindBy(id = "Terms & Conditions") private MobileElement termsConditionLink;

    @iOSXCUITFindBy(id = "How it Works") private MobileElement howItWorksLink;


    public EasyPaisaLoginPage enterMSISDN (String phoneNumber){
        objBaseTest.waitForVisibility(msisdn);
        objBaseTest.SendKeys(msisdn, phoneNumber);
        return this;
    }

    public String checkWelcomeText(String attribute){
        objBaseTest.waitForVisibility(welcomeText);
        return objBaseTest.getAttribute(welcomeText, attribute);
    }

    public String checkTermsConditionLink(String attribute){
        objBaseTest.waitForVisibility(termsConditionLink);
        return  objBaseTest.getAttribute(termsConditionLink, attribute);
    }

    public EasyPaisaDashboardPage clickOnProceedButton(){
        objBaseTest.waitForVisibility(proceedButton);
        objBaseTest.Click(proceedButton);
        return new EasyPaisaDashboardPage();
    }



}
