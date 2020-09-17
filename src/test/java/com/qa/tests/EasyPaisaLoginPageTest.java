package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.AutoScoutDashboardPage;
import com.qa.pages.AutoScouteLoginPage;
import com.qa.pages.EasyPaisaDashboardPage;
import com.qa.pages.EasyPaisaLoginPage;
import com.qa.utils.TestUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.*;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;

public class EasyPaisaLoginPageTest {

    EasyPaisaDashboardPage dashboardPage;
    EasyPaisaLoginPage loginPage;
    BaseTest objBaseTest;
    JSONObject msisdn;
    InputStream datafile;
    InputStream stringIS;
    HashMap<String , String> stringHashMap;
    TestUtils utils;

    @Parameters({"emulator","udid","platformName","platformVersion","deviceName"})
    @BeforeClass
    public void beforeClass(String emulator,String udid, String platformName, String platformVersion, String deviceName) throws Exception{
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
            msisdn = new JSONObject(tokener);
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
        dashboardPage = new EasyPaisaDashboardPage();
        loginPage = new EasyPaisaLoginPage();
    }

    @Test
    public void enterMsisdn(){
        objBaseTest.softAssert.assertEquals(loginPage.checkWelcomeText("label"), stringHashMap.get("welcome Text"));
        loginPage.enterMSISDN(msisdn.getJSONObject("validMSISDN").getString("msisdn"));
        dashboardPage = loginPage.clickOnProceedButton();
        objBaseTest.softAssert.assertAll();
        System.out.println("");
    }
}
