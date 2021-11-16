package com.weathershopper.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.weathershopper.constants.FrameworkConstants;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Perform initialisation and termination of ExtentReports
 * After creating an instance for ExtentTest it is delegated to ThreadLocal
 * variable for providing thread safety.
 */
public final class ExtentReport {

	private ExtentReport() {}

	private static ExtentReports extent;

	/**
	 * Set the initial configuration for the Extent Reports and decides the report generation path.
	 */
	public static void initReports() {
		if(Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("WeatherShopper Automation");
			spark.config().setReportName("Flink Assignment");
		}
	}

	/**
	 * Flushing the reports ensures extent logs are reflected properly. 
	 * Opens the report in the default desktop browser.
	 * Sets the ThreadLocal variable to default value
	 */
	public static void flushReports(){
		if(Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a test node in the extent report. Delegates to ExtentManager for providing thread safety
	 * @param testcasename Test Name that needs to be reflected in the report
	 */
	public static void createTest(String testcasename) {
		ExtentManager.setExtentTest(extent.createTest(testcasename));
	}
	





}
