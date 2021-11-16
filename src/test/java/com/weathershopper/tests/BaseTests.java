package com.weathershopper.tests;

import com.weathershopper.driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

public class BaseTests {

    protected BaseTests(){}

    @BeforeMethod
    protected void setUp(Object[] data) { //Map<String,String>
        Map<String,String> map = (Map<String,String>)data[0];
        Driver.initDriver(map.get("browser"),map.get("version"));
    }

    /**
     * Terminates the browser instance
     */
    @AfterMethod
    protected void tearDown() {
        Driver.quitDriver();
    }

}
