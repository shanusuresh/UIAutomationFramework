package com.weathershopper.pages;

import com.weathershopper.driver.DriverManager;
import com.weathershopper.enums.WaitStrategy;
import org.openqa.selenium.By;

public class StripePaymentPage extends BasePage{

    private final String title = "//div[@class='title']/h1";
    private final String email = "email";
    private final String cardNumber ="card_number";
    private final String cardExpiry ="cc-exp";
    private final String cvv="cc-csc";
    private final String pinCode ="billing-zip";
    private final String btnSubmit="submitButton";


    public String getTitle() {
        return getElementText(By.xpath(title),WaitStrategy.PRESENCE,"Stripe page title");
    }

    public StripePaymentPage enterEmailId(String emailId){
        sendKeys(By.id(email), emailId,WaitStrategy.PRESENCE,"Email Id");
        return this;
    }

    public StripePaymentPage enterCardNumber(String cardNum){
        sendKeysByJS(By.id(cardNumber), cardNum,"Card Number");
        return this;
    }

    public StripePaymentPage enterCardExpiry(String expiry){
        sendKeysByJS(By.id(cardExpiry), expiry,"Card Expiry");
        return this;
    }

    public StripePaymentPage enterCVV(String cvvNum){
        sendKeysByJS(By.id(cvv), cvvNum,"CVV/CSC");
        return this;
    }

    public StripePaymentPage enterPincode(String zipcode){
        sendKeys(By.id(pinCode), zipcode,WaitStrategy.PRESENCE,"ZIPCode");
        return this;
    }

    public int getButtonText(){
        return getElementTextAsInt(By.id(btnSubmit),WaitStrategy.CLICKABLE,"Button Text");
    }

    public ConfirmationPage clickPayButton() throws InterruptedException {
        click(By.id(btnSubmit),WaitStrategy.CLICKABLE,"Pay button");
        Thread.sleep(5000);
//        DriverManager.getDriver().switchTo().defaultContent();
        return new ConfirmationPage();
    }


}
