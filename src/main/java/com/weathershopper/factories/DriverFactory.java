package com.weathershopper.factories;

import com.weathershopper.enums.ConfigProperties;
import com.weathershopper.utils.PropertyFileUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public final class DriverFactory {

    private DriverFactory(){}

    public static WebDriver getDriver(String browser, String version) throws MalformedURLException {

        WebDriver driver=null;

        String runmode = PropertyFileUtil.get(ConfigProperties.RUNMODE);
        if(browser.equalsIgnoreCase("chrome")) {
            if(runmode.equalsIgnoreCase("remote")) {
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setBrowserName(BrowserType.CHROME);
                cap.setVersion(version);
                driver =new RemoteWebDriver(new URL(PropertyFileUtil.get(ConfigProperties.SELENIUMGRIDURL)), cap);
            }
            else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }
        else if(browser.equalsIgnoreCase("firefox")) {

            if(runmode.equalsIgnoreCase("remote")) {
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setBrowserName(BrowserType.FIREFOX);
                cap.setVersion(version);
                driver =new RemoteWebDriver(new URL(PropertyFileUtil.get(ConfigProperties.SELENIUMGRIDURL)), cap);
            } else {
//                System.setProperty("webdriver.gecko.driver","src/test/resources/executables/geckodriver");
//                System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        }
        else if(browser.equalsIgnoreCase("safari")) {

            if(runmode.equalsIgnoreCase("remote")) {
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setBrowserName(BrowserType.SAFARI);
                cap.setVersion(version);
                driver =new RemoteWebDriver(new URL(PropertyFileUtil.get(ConfigProperties.SELENIUMGRIDURL)), cap);
            } else {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
            }
        }
        return driver;

    }


}
