package com.infiniteanalytics.assignment.generics;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getMethod());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod());
    }

    @Override
    public void onTestFailure(ITestResult result) {
      String methodName =result.getMethod().getMethodName();
      failed(methodName);
    }

}

