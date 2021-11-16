package com.weathershopper.listeners;


import com.weathershopper.reports.ExtentReport;
import org.testng.*;

import java.util.Arrays;

import static com.weathershopper.enums.LogType.*;
import static com.weathershopper.reports.FrameworkLogger.log;


/**
 * Implements {@link org.testng.ITestListener} and {@link org.testng.ISuiteListener} to leverage the abstract methods
 * Mostly used to help in extent report generation
 *
 * <pre>Please make sure to add the listener details in the testng.xml file</pre>
 */
public class ListenerClass implements ITestListener, ISuiteListener {

    /**
     * Initialise the reports with the file name
     */
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    /**
     * Terminate the reports
     */
    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();

    }

    /**
     * Starts a test node for each testng test
     */
    @Override
    public void onTestStart(ITestResult result) {

        ExtentReport.createTest(result.getMethod().getDescription());
    }

    /**
     * Marks the test as pass and logs it in the report
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        //ExtentLogger.pass(result.getMethod().getMethodName() +" is passed");
        log(PASS, result.getMethod().getMethodName() + " is passed");
    }

    /**
     * Marks the test as fail,append base64 screenshot and logs it in the report
     */
    @Override
    public void onTestFailure(ITestResult result) {
        log(FAIL, result.getMethod().getMethodName() + " is failed");
        log(FAIL, result.getThrowable().toString());
        log(FAIL, Arrays.toString(result.getThrowable().getStackTrace()));
    }

    /**
     * Marks the test as skip and logs it in the report
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        //ExtentLogger.skip(result.getMethod().getMethodName() +" is skipped");
        log(SKIP, result.getMethod().getMethodName() + " is skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        /*
         * For now, we are not using this.
         */
    }

    @Override
    public void onStart(ITestContext context) {
        /*
         * We are having just one test in our suite. So we dont have any special implementation as of now
         */
    }

    @Override
    public void onFinish(ITestContext context) {
        /*
         * We are having just one test in our suite. So we dont have any special implementation as of now
         */

    }

}
