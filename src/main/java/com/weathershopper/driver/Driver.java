package com.weathershopper.driver;

import com.weathershopper.enums.ConfigProperties;
import com.weathershopper.exceptions.BrowserInvocationFailedException;
import com.weathershopper.factories.DriverFactory;
import com.weathershopper.utils.PropertyFileUtil;

import java.net.MalformedURLException;
import java.util.Objects;

public final class Driver {
    private Driver(){}
    /**
     * Gets the browser value and initialise the browser based on that
     * @param browser Value will be passed from Base. Values can be chrome and firefox
     *
     */
    public static void initDriver(String browser,String version)  {


        if(Objects.isNull(DriverManager.getDriver())) {
            try {
                DriverManager.setDriver(DriverFactory.getDriver(browser,version));

            } catch (MalformedURLException e) {
                throw new BrowserInvocationFailedException("Please check the capabilities of browser");
            }
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().get("https://weathershopper.pythonanywhere.com/");

        }
    }

    /**
     * Terminates the browser instance.
     * Sets the threadlocal to default value, i.e null.
     */
    public static void quitDriver() {
        if(Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
