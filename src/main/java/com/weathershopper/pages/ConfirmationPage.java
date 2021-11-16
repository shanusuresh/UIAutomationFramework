package com.weathershopper.pages;

import com.weathershopper.enums.WaitStrategy;
import org.openqa.selenium.By;

public class ConfirmationPage extends BasePage{

    private final String confirmationText = "//div[@class='container top-space-50']/div/h2";
    public String getTitle(){
        return getPageTitle();
    }

    public String getPaymentConfirmationStatus(){
        return getElementText(By.xpath(confirmationText), WaitStrategy.PRESENCE,"Payment confirmation Status");
    }


}
