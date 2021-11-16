package com.weathershopper.driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class DriverManager {

    private DriverManager(){}

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>() ;

    /**
     * Returns the thread safe webdriver instance fetched from ThreadLocal variable.
     * @return webdriver instance.
     */
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    /**
     * Set the WebDriver instance to thread local variable
     * @param driverref {@link org.openqa.selenium.WebDriver} instance that needs to saved from Thread safety issues.<p>
     */
    static void setDriver(WebDriver driverref) {
        if(Objects.nonNull(driverref)) {
            threadLocalDriver.set(driverref);
        }
    }
    /**
     * Calling remove method on Threadlocal variable ensures to set the default value to Threadlocal variable.
     * It is much safer than assigning null value to ThreadLocal variable.
     */
    static void unload() {
        threadLocalDriver.remove();
    }
}
