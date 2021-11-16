package com.weathershopper.pages;

import com.weathershopper.driver.DriverManager;
import com.weathershopper.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoisturizersPage extends BasePage{
    private static final String productNames = "//div[@class='text-center col-4']/p";
    private final String cartItemCount = "//span[@id='cart']";

    public String getTitle() {
        return getPageTitle();
    }



    public MoisturizersPage clickAddLeastExpensiveMoisturizer(String text){
        String leastExpensiveProduct = ProductMap.getProductMap(text).keySet().stream().findFirst().get().toString();
        click(By.xpath("//*[text()='"+leastExpensiveProduct+"']/following-sibling::button"), WaitStrategy.CLICKABLE,"Add least expensive moisturize with text "+text);
        return this;
    }

    public int getCartItemsCount(){
//        String itemCount = DriverManager.getDriver().findElement(By.xpath(cartItemCount)).getText();
        String itemCount = getElementText(By.xpath(cartItemCount),WaitStrategy.PRESENCE,"Cart item count");
        if(itemCount.equals("Empty")){ return 0;}
        else {
            itemCount = itemCount.replaceAll("[^0-9]","");
            return Integer.parseInt(itemCount);
        }
    }

    public CheckOutPage clickCart() throws InterruptedException {
        Thread.sleep(5000);
      click(By.xpath(cartItemCount),WaitStrategy.CLICKABLE,"Cart button");
      return new CheckOutPage();
    }




}
