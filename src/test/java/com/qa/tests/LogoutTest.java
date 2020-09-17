package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.AutoScoutDashboardPage;
import com.qa.pages.AutoScouteLoginPage;
import com.qa.pages.AutoScouteLogoutPage;
import com.qa.pages.AutoScouteSettingPage;
import com.qa.utils.TestUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.*;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;

public class LogoutTest {
    AutoScouteLoginPage loginPage;
    AutoScouteLogoutPage logoutPage;
    AutoScouteSettingPage settingPage;
    AutoScoutDashboardPage dashboardPage;
    BaseTest objBaseTest;
    JSONObject jsonObject;
    InputStream datafile;
    InputStream stringIS;
    HashMap<String , String> stringHashMap;
    TestUtils utils;

    @Parameters({"emulator","udid","platformName","platformVersion", "deviceName"})
    @BeforeClass
    public void beforeClass(String emulator,String udid, String platformName, String platformVersion, String deviceName) throws Exception {
        try {
            objBaseTest = new BaseTest();
            objBaseTest.driverSetup(emulator,udid,platformName, platformVersion, deviceName);
            String dataFilePath = "data/loginUser.json";
            datafile = getClass().getClassLoader().getResourceAsStream(dataFilePath);
            JSONTokener tokener = new JSONTokener(datafile);
            jsonObject = new JSONObject(tokener);
            String xmlFilePath = "strings/string.xml";
            stringIS = getClass().getClassLoader().getResourceAsStream(xmlFilePath);
            utils = new TestUtils();
            stringHashMap = utils.parseStringXML(stringIS);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if(datafile != null){
                datafile.close();

            }

            if(stringIS != null){
                stringIS.close();

            }
        }
    }

    @AfterClass
    public void afterClass() throws Exception
    {
        objBaseTest.driverTearDown();
    }

    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("Executing --->"+m.getName());
        logoutPage = new AutoScouteLogoutPage();
        settingPage = new AutoScouteSettingPage();
        dashboardPage = new AutoScoutDashboardPage();
        loginPage = new AutoScouteLoginPage();

    }

    @Test
    public void logout() throws Exception{
        loginPage = dashboardPage.clickOnNavigateUpButton();
        loginPage.clickLogin();
        loginPage.sendUserName(jsonObject.getJSONObject("validUser").getString("username"));
        loginPage.sendPassword(jsonObject.getJSONObject("validUser").getString("password"));
        loginPage.clickLoginButton();
        Thread.sleep(4000);
        dashboardPage.clickOnNavigateUpButton();
        logoutPage = loginPage.clickOnLoggedInUser();
        logoutPage.scrollDown();
        logoutPage.clikOnLogoutButton();
        dashboardPage.clickOnDiscover();
        loginPage = dashboardPage.clickOnNavigateUpButton();
        objBaseTest.softAssert.assertEquals(loginPage.getLoginButtonText("text"),stringHashMap.get("login_button_text"));
        objBaseTest.softAssert.assertAll();
    }
}
