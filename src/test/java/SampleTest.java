import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class SampleTest {

    WebDriver driver;

    @Test
    public void runTestCase() throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.get("https://weathershopper.pythonanywhere.com/moisturizer");
        List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='text-center col-4']/p"));
//        System.out.println(items.size());
//        long count = items.stream().filter(e->e.getText().contains("Aloe")).count();
//        System.out.println(count);
//
//        List<WebElement> textWithAloe= items.stream().filter(e->e.getText().contains("Aloe")).collect(Collectors.toList());
////        textWithAloe.stream().forEach(e-> System.out.println(e.getText()));
//        textWithAloe.stream().filter(e->e.getText().matches("[0-9]")).forEach(System.out::println);

        List<WebElement> itemsMatchingText = productNames.stream().filter(e -> e.getText().contains("Aloe")).collect(Collectors.toList());
//        String priceXpath = "//*[text()='Vassily Aloe Attack']/following-sibling::p";
        HashMap<String, Integer> productMap = new HashMap<>();
        for (WebElement items : itemsMatchingText) {

            String price = driver.findElement(By.xpath("//*[text()='" + items.getText() + "']/following-sibling::p")).getText();
//
            price = price.replaceAll("[^0-9]", "");
            productMap.put(items.getText(), Integer.parseInt(price));
        }

        productMap.forEach((k, v) -> System.out.println(k + ":" + v));
        Map<String, Integer> sortedProductMap = productMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        sortedProductMap.forEach((k, v) -> System.out.println("Sorted map:" + k + ":" + v));
        String leastExpensiveProduct = sortedProductMap.keySet().stream().findFirst().get().toString();
        System.out.println(leastExpensiveProduct);
        String leastExpensiveProductValue = sortedProductMap.entrySet().stream().findFirst().get().toString();
        System.out.println("leastExpensiveProductValue "+leastExpensiveProductValue);
        Map<String,Integer> expectedMap = new HashMap<>();
//        expectedMap.putAll(sortedProductMap.entrySet().stream().findFirst().get());
        driver.findElement(By.xpath("//*[text()='" + leastExpensiveProduct + "']/following-sibling::button")).click();

        expectedMap.put("Sample",100);
        expectedMap.forEach((k, v) -> System.out.println("Sorted map:" + k + ":" + v));

//        driver.findElement(By.xpath("//*[text()='Vassily Aloe Attack']/following-sibling::p");
//        Thread.sleep(5000);

        Map.Entry<String, Integer> addedProducts = sortedProductMap.entrySet().stream().findFirst().get();
        String itemCount = driver.findElement(By.xpath("//span[@id='cart']")).getText();
        System.out.println(itemCount);
        if (itemCount.equals("Empty")) System.out.println(0);
        else {
            itemCount = itemCount.replaceAll("[^0-9]", "");
            System.out.println(Integer.parseInt(itemCount));
        }
        driver.findElement(By.xpath("//span[@id='cart']")).click();

        HashMap<String, Integer> pdtMap = new HashMap<>();
        for (int i = 1; i <= 1; i++) {
            String productName = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + i + "]/td[1]")).getText();
            String price = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + i + "]/td[2]")).getText();
            pdtMap.put(productName, Integer.parseInt(price));
        }
        pdtMap.forEach((k, v) -> System.out.println(k + ":" + v));

        Map.Entry<String, Integer> checkedProducts = pdtMap.entrySet().stream().findFirst().get();
        assertEquals(addedProducts, checkedProducts);

        int total=0;
        for(Map.Entry<String,Integer> price:pdtMap.entrySet()){
            total += price.getValue();
        }
        System.out.println(total);

        int price = Integer.parseInt(driver.findElement(By.id("total")).getText().replaceAll("[^0-9]",""));
        System.out.println(price);

        driver.findElement(By.xpath("//span[text()='Pay with Card']")).click();

        Thread.sleep(5000);

        driver.switchTo().frame(0);
        System.out.println(driver.findElement(By.xpath("//div[@class='title']/h1")).getText());
        driver.findElement(By.id("email")).sendKeys("shanmugapriya.sabapathy@gmail.com");
        JavascriptExecutor jse = ((JavascriptExecutor)driver);
        WebElement card = driver.findElement(By.id("card_number"));
        jse.executeScript("arguments[0].value='4242 4242 4242 4242';", card);
        WebElement ccexp = driver.findElement(By.id("cc-exp"));
        jse.executeScript("arguments[0].value='03 / 24';", ccexp);
//        driver.findElement(By.id("cc-exp")).sendKeys("03 / 24");
        driver.findElement(By.id("cc-csc")).sendKeys("123");
        driver.findElement(By.id("billing-zip")).sendKeys("60001");
        System.out.println(driver.findElement(By.id("submitButton")).getText().contains(String.valueOf(total)));
        driver.findElement(By.id("submitButton")).click();
Thread.sleep(4000);
        System.out.println(driver.findElement(By.xpath("//div[@class='container top-space-50']/div/h2")).getText().equals("PAYMENT SUCCESS"));



    }
}
