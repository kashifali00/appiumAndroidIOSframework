package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    // Make all the global variables threadlocal

    protected  static AppiumDriver appiumDrv;
    protected  DesiredCapabilities cap;
    protected  static Properties props;
    public SoftAssert softAssert = new SoftAssert();
    InputStream inputStream;
    URL appiumURL;
    File androidAppURL,iOSAppURL;


    // Constructor used here to initialize the page factory class
    public BaseTest(){
    }

    public void setDriver(AppiumDriver appiumDrv){
        this.appiumDrv = appiumDrv;

    }

    public AppiumDriver getDriver(){
        return appiumDrv;
    }

    public void driverSetup(String emulator,String udid, String platformName, String platformVersion, String deviceName) throws Exception{
            System.out.println("***** Executing BeforeTest method inside BaseTest Class *****");
            System.out.println("platformName "+"["+platformName+"]");
            System.out.println("platformVersion "+"["+platformVersion+"]");
            System.out.println("deviceName "+"["+deviceName+"]");
            System.out.println("udid "+"["+udid+"]");
            System.out.println("emulator "+"["+emulator+"]");

            cap = new DesiredCapabilities();
            props = new Properties();
            String propsFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
            props.load(inputStream);
            androidAppURL = new File(props.getProperty("androidAppLocation"));
            iOSAppURL = new File(props.getProperty("iOSAppLocation"));
            cap.setCapability("platformName", platformName);
            cap.setCapability("platformVersion", platformVersion);
            cap.setCapability("deviceName",deviceName);

            switch (platformName){
                case "Android":
                    cap.setCapability("automationName", props.getProperty("androidAutomationName"));
                    cap.setCapability("app", androidAppURL.getAbsolutePath());
                    cap.setCapability("appPackage",props.getProperty("appPackage"));
                    cap.setCapability("appActivity",props.getProperty("appActivity"));
                    if(emulator.equalsIgnoreCase("true")){
                        cap.setCapability("avd", deviceName);
                    }
                    cap.setCapability("newCommandTimeout", TestUtils.newCommandTimeoutWait);
                    cap.setCapability("appWaitDuration", TestUtils.appDurationTimeoutWait);
                    cap.setCapability("fullReset","true");
                    appiumURL = new URL(props.getProperty("appiumServerURL"));
                    appiumDrv = new AndroidDriver<MobileElement>(appiumURL, cap);
                    break;

                case "iOS":
                    cap.setCapability("automationName", props.getProperty("iOSAutomationName"));
                    cap.setCapability("app", iOSAppURL.getAbsolutePath());
                    cap.setCapability("bundleId",props.getProperty("appBundleId"));
                    cap.setCapability("udid",udid);
                    appiumURL = new URL(props.getProperty("appiumServerURL"));
                    appiumDrv = new IOSDriver<MobileElement>(appiumURL, cap);
                    break;

                default:
                    throw new Exception("Invalid platformName - "+platformName);

            }
            appiumDrv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // web driver wait method

    public void waitForVisibility(MobileElement el){
            System.out.println("*****  Executing waitForVisibility method  *****");
            WebDriverWait wait = new WebDriverWait(appiumDrv, TestUtils.WAIT);
            wait.until(ExpectedConditions.visibilityOf(el)).isDisplayed();
    }

    public void waitForInvisibility(MobileElement el){
            WebDriverWait wait = new WebDriverWait(appiumDrv, TestUtils.WAIT);
            wait.until(ExpectedConditions.invisibilityOf(el));


    }

    public void waitForAlert(){
            WebDriverWait wait = new WebDriverWait(appiumDrv, TestUtils.WAIT);
            wait.until(ExpectedConditions.alertIsPresent());
    }

    // web driver actions methods
    public void Click(MobileElement el){
        System.out.println("*****  Executing Click method  *****");
            el.click();
    }

    public void clear(MobileElement el){
        waitForVisibility(el);
        el.clear();
    }

    public void Scroll(MobileElement el){
        try {
            System.out.println("Executing scroll method");
            //((FindsByAndroidUIAutomator)appiumDrv).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+el+"\"))");
            appiumDrv.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                    ".scrollIntoView(new UiSelector().textContains(\"" + el + "\"))"));
        }catch (Exception e){
        }
    }

    public void closeApp(){
        ((InteractsWithApps)appiumDrv).closeApp();
    }

    public void launchApp(){
        ((InteractsWithApps)appiumDrv).launchApp();
    }

    public void SendKeys(MobileElement el, String str){
        waitForVisibility(el);
        el.sendKeys(str);
    }

    public String getText(MobileElement el){
        waitForVisibility(el);
        return el.getAttribute("text");
    }

    public String resourceId(MobileElement el){
        waitForVisibility(el);
        return el.getAttribute("resource-id");
    }

    public String getAttribute(MobileElement el, String attribute){
        waitForVisibility(el);
        return el.getAttribute(attribute);
    }

    public void driverTearDown () throws Exception{
            System.out.println("***** Executing AfterTest method inside BaseTest Class *****");
            appiumDrv.quit();
            inputStream.close();

    }
}
