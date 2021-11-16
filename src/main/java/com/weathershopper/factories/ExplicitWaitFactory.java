package com.weathershopper.factories;

import com.weathershopper.constants.FrameworkConstants;
import com.weathershopper.driver.DriverManager;
import com.weathershopper.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Explicit wait factory produces different waits before operating on webelement
 */
public final class ExplicitWaitFactory {

	private ExplicitWaitFactory() {}

	/**
	 * @param waitstrategy Strategy to be applied to find a webelement
	 * @param by By locator of the webelement
	 * @return webelement Locates and return the webelement
	 */
	public static WebElement performExplicitWait(WaitStrategy waitstrategy, By by) {
		WebElement element = null;
		if(waitstrategy == WaitStrategy.CLICKABLE) {
			element = 	new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitWait())
					.until(ExpectedConditions.elementToBeClickable(by));
		}
		else if(waitstrategy == WaitStrategy.PRESENCE) {
			element =	new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitWait())
					.until(ExpectedConditions.presenceOfElementLocated(by));
		}
		else if(waitstrategy == WaitStrategy.VISIBLE) {
			element =new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitWait())
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		else if(waitstrategy == WaitStrategy.NONE) {
			element = DriverManager.getDriver().findElement(by);
		}
		return element;
	}

}
