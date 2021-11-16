package com.weathershopper.pages;

import com.weathershopper.driver.DriverManager;
import com.weathershopper.enums.WaitStrategy;
import com.weathershopper.factories.ExplicitWaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.weathershopper.enums.LogType.PASS;
import static com.weathershopper.reports.FrameworkLogger.log;

public class BasePage {

    protected void click(By by, WaitStrategy waitstrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
        element.click();
        log(PASS, elementName + " is clicked");
    }

    protected String getPageTitle() {
        String pageTitle = DriverManager.getDriver().getTitle();
        log(PASS, "User is on the page : " + pageTitle);
        return pageTitle;

    }

    protected void sendKeys(By by, String value, WaitStrategy waitstrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
        element.sendKeys(value);
        log(PASS, value + " is entered successfully in " + elementName);
    }

    protected void sendKeysByJS(By by, String value, String elementName) {
        JavascriptExecutor jse = ((JavascriptExecutor)DriverManager.getDriver());
        WebElement element = DriverManager.getDriver().findElement(by);
//        WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
        jse.executeScript("arguments[0].value='"+value+"';", element);
        log(PASS, value + " is entered successfully in " + elementName);
    }

    protected String getElementText(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        String eleText = element.getText();
        log(PASS, "The displayed element text for " + elementName + " is " + eleText);
        return eleText;
    }

    protected int getElementTextAsInt(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        int eleText = Integer.parseInt(element.getText().replaceAll("[^0-9]",""));
        log(PASS, "The displayed element text for " + elementName + " is " + eleText);
        return eleText;
    }

    protected static void switchToFrame(int index){
        DriverManager.getDriver().switchTo().frame(index);
        log(PASS,"Switched to frame");
    }


}
