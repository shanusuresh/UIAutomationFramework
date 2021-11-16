package com.weathershopper.pages;

import com.weathershopper.enums.WaitStrategy;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final String temperatureValue = "temperature";
    private final String btnBuyMoisturizers = "//button[text()='Buy moisturizers']";
    private final String btnBuySunscreens = "//button[text()='Buy sunscreens']";

    public int getTemperatureText() {
        return getElementTextAsInt(By.id(temperatureValue), WaitStrategy.VISIBLE, "Temperature value");
    }

    public MoisturizersPage clickBuyMoisturizers() {
        click(By.xpath(btnBuyMoisturizers), WaitStrategy.CLICKABLE, "Buy moisturizers");
        return new MoisturizersPage();
    }

    public SunscreensPage clickBuySunscreens() {
        click(By.xpath(btnBuySunscreens), WaitStrategy.CLICKABLE, "Buy moisturizers");
        return new SunscreensPage();
    }

    public String getTitle(){
        return getPageTitle();
    }


}
