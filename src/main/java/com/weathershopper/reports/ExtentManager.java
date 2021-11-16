package com.weathershopper.reports;

import com.aventstack.extentreports.ExtentTest;

import java.util.Objects;

/**
 * ExtentManager class helps to achieve thread safety for the ExtentTest instance.
 */
public class ExtentManager {

	private ExtentManager() {}

	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>() ;

	/**
	 * Returns the thread safe ExtentTest instance fetched from ThreadLocal variable.
	 * @return Thread safe ExtentTest instance.
	 */
	 static ExtentTest getExtentTest() {
		return extTest.get();
	}

	/**
	 * Set the ExtentTest instance to thread local variable
	 * @param test ExtentTest instance that needs to saved from Thread safety issues.<p>
	 */
	static void setExtentTest(ExtentTest test) {
		if(Objects.nonNull(test)) {
		extTest.set(test);
		}
	}

	/**
	 * Calling remove method on Threadlocal variable ensures to set the default value to Threadlocal variable.
	 */
	static void unload() {
		extTest.remove();
	}
}
