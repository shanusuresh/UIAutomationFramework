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

public class ProductMap extends BasePage {

    private static final String productNames = "//div[@class='text-center col-4']/p";
    private final String cartItemCount = "//span[@id='cart']";

    public static List<WebElement> getProductMatchingText(String text) {
        List<WebElement> productNamesMatchingText = DriverManager.getDriver().findElements(By.xpath(productNames))
                .stream()
                .filter(e -> e.getText().contains(text))
                .collect(Collectors.toList());
        return productNamesMatchingText;
    }

    /**
     * Retrieving the price of each product matching the specific text and storing it in a map
     * @param text
     * @return Hashmap -> Product name , Price
     */
    public static Map<String, Integer> getProductMap(String text) {
        HashMap<String, Integer> productMap = new HashMap<>();

        for (WebElement items : getProductMatchingText(text)) {
            String price = DriverManager.getDriver().findElement(By.xpath("//*[text()='" + items.getText() + "']/following-sibling::p")).getText();
            price = price.replaceAll("[^0-9]", "");
            productMap.put(items.getText(), Integer.parseInt(price));
        }
//        productMap.forEach((k, v) -> System.out.println(k + ":" + v));
        Map<String, Integer> sortedProductMap = productMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        return sortedProductMap;
    }

    public int getCartItemsCount(){
//        String itemCount = DriverManager.getDriver().findElement(By.xpath(cartItemCount)).getText();
        String itemCount = getElementText(By.xpath(cartItemCount), WaitStrategy.PRESENCE,"Cart item count");
        if(itemCount.equals("Empty")){ return 0;}
        else {
            itemCount = itemCount.replaceAll("[^0-9]","");
            return Integer.parseInt(itemCount);
        }
    }
}
