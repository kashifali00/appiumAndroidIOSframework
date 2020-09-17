package com.qa.tests;

import com.google.gson.JsonObject;
import com.qa.BaseTest;
import com.qa.pages.AutoScoutDashboardPage;
import com.qa.pages.AutoScouteLoginPage;
import com.qa.utils.TestUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;

public class LoginPageTest {
    AutoScouteLoginPage loginPage;
    AutoScoutDashboardPage dashboardPage;
    BaseTest objBaseTest;
    JSONObject userLogins;
    InputStream datafile;
    InputStream stringIS;
    HashMap<String , String> stringHashMap;
    TestUtils utils;

    @Parameters({"emulator","udid","platformName","platformVersion","deviceName"})
    @BeforeClass
    public void beforeClass(String emulator,String udid,String platformName, String platformVersion, String deviceName) throws Exception{
        try{
            objBaseTest = new BaseTest();
            objBaseTest.driverSetup(emulator,udid,platformName,platformVersion,deviceName);
            String xmlFileName = "strings/string.xml";
            stringIS = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            utils = new TestUtils();
            stringHashMap = utils.parseStringXML(stringIS);
            String dataFilepath = "data/loginUser.json";
            datafile = getClass().getClassLoader().getResourceAsStream(dataFilepath);
            JSONTokener tokener = new JSONTokener(datafile);
            userLogins = new JSONObject(tokener);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if((datafile != null) && (stringIS !=null) ){
                datafile.close();
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
        System.out.println("***** Executing BeforeMethod "+m.getName()+"*****");
        loginPage = new AutoScouteLoginPage();
        dashboardPage = new AutoScoutDashboardPage();
        loginPage = new AutoScouteLoginPage();
    }

    @Test
    public void loginWithValidCredentials() throws Exception{
        loginPage = dashboardPage.clickOnNavigateUpButton();
        loginPage.clickLogin();
        loginPage.sendUserName(userLogins.getJSONObject("validUser").getString("username"));
        loginPage.sendPassword(userLogins.getJSONObject("validUser").getString("password"));
        loginPage.clickLoginButton();
        Thread.sleep(4000);
        dashboardPage.clickOnNavigateUpButton();
        objBaseTest.softAssert.assertEquals(loginPage.getLoggedUserName("text"), stringHashMap.get("logged_user_name"));
        objBaseTest.softAssert.assertAll();
    }
}


