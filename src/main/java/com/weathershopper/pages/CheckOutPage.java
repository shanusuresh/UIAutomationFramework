package com.weathershopper.pages;

import com.weathershopper.driver.DriverManager;
import com.weathershopper.enums.WaitStrategy;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class CheckOutPage extends BasePage {

    private final String textTotal = "total";
    private final String btnPayWithCard ="//span[text()='Pay with Card']";

    public String getTitle() {
        return getPageTitle();
    }

    public Map<String, Integer> getItemsFromCheckoutPage() {
        Map<String, Integer> checkoutProductMap = new HashMap<>();
        for (int i = 1; i <= 2; i++) {
            String productName = DriverManager.getDriver().findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + i + "]/td[1]")).getText();
            String price = DriverManager.getDriver().findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + i + "]/td[2]")).getText();
            checkoutProductMap.put(productName, Integer.parseInt(price));
        }
        return checkoutProductMap;
    }

    public int calculateTotalPrice() {
        int total = 0;
        for (Map.Entry<String, Integer> price : getItemsFromCheckoutPage().entrySet()) {
            total += price.getValue();
        }
        return total;
    }

    public int getTotalPrice() {
        return Integer.parseInt(DriverManager.getDriver()
                .findElement(By.id(textTotal))
                .getText().replaceAll("[^0-9]", ""));
    }

    public StripePaymentPage clickPayWithCard() throws InterruptedException {
        click(By.xpath(btnPayWithCard), WaitStrategy.CLICKABLE,"Pay with card");
        Thread.sleep(5000);
        switchToFrame(0);
        return new StripePaymentPage();
    }

}
