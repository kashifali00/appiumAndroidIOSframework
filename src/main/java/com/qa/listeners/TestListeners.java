package com.qa.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

    public  void onTestFailure(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            result.getThrowable().printStackTrace();
        }
        else if(result.getStatus() == ITestResult.SUCCESS){

        }

    }
}
