package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.AutoScoutDashboardPage;
import com.qa.pages.AutoScouteLoginPage;
import com.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;

public class AutoScouteDashboardTest  {

    AutoScoutDashboardPage dashBoard;
    AutoScouteLoginPage loginPage;
    BaseTest objBaseClass;
    HashMap<String , String > stringHashMap;
    InputStream stringsIS;
    TestUtils utils;

    @Parameters({"emulator","udid","platformName","platformVersion", "deviceName"})
    @BeforeClass
    public void beforeClass(String emulator,String udid,String platformName, String platformVersion, String deviceName) throws Exception{
        try {
            objBaseClass = new BaseTest();
            objBaseClass.driverSetup(emulator,udid,platformName, platformVersion, deviceName);
            String xmlFilePath = "strings/string.xml";
            stringsIS = getClass().getClassLoader().getResourceAsStream(xmlFilePath);
            utils = new TestUtils();
            stringHashMap = utils.parseStringXML(stringsIS);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if(stringsIS != null){
                stringsIS.close();
            }
        }
    }

    @AfterClass
    public void afterClass() throws Exception{
        objBaseClass.driverTearDown();
    }

    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("***** Executing BeforeMethod "+m.getName()+"*****");
        dashBoard = new AutoScoutDashboardPage();
        loginPage = new AutoScouteLoginPage();

    }

    @Test
    public void clickOnNavigateButtonOnDashboard(){
            objBaseClass.softAssert.assertEquals(dashBoard.isNavigateButtonVisible("content-desc"), "Navigate up");
            loginPage = dashBoard.clickOnNavigateUpButton();
            objBaseClass.softAssert.assertEquals(loginPage.getLoginButtonText("text"), stringHashMap.get("login_button_text"));
            objBaseClass.softAssert.assertAll();
    }
}
