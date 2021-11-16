package com.weathershopper.tests;

import com.weathershopper.driver.DriverManager;
import com.weathershopper.pages.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static com.weathershopper.enums.LogType.PASS;
import static com.weathershopper.reports.FrameworkLogger.log;

public final class EndToEndTest extends BaseTests{
    Map<String,Integer> chosenMoistProductMap = new HashMap<>();
    Map<String,Integer> chosenSunscreenProductMap = new HashMap<>();
    private EndToEndTest(){}

    @Test
    public void endToEndTest(Map<String,String> data) throws InterruptedException {


        HomePage homePage = new HomePage();
        Assertions.assertThat(homePage.getTitle())
                .isNotNull()
                .isEqualTo("Current Temperature");
        int temperatureDisplayed = homePage.getTemperatureText();
        if(temperatureDisplayed<19){
            chosenMoistProductMap.clear();
            MoisturizersPage moisuturizerPage = homePage.clickBuyMoisturizers();
            String moisturizerpageTitle = moisuturizerPage.getTitle();
            Assertions.assertThat(moisturizerpageTitle)
                    .isNotNull()
                    .isEqualTo("The Best Moisturizers in the World!");
            Assertions.assertThat(moisuturizerPage.getCartItemsCount()).isZero();
            moisuturizerPage.clickAddLeastExpensiveMoisturizer("Aloe");
            storeProductDetailsOfMositurizer("Aloe");
            moisuturizerPage.clickAddLeastExpensiveMoisturizer("Almond");
            storeProductDetailsOfMositurizer("Almond");
            Assertions.assertThat(moisuturizerPage.getCartItemsCount())
                    .isNotNegative()
                    .isNotZero()
                    .isEqualTo(2);
            CheckOutPage checkOutPage = moisuturizerPage.clickCart();
            String checkOutPageTitle = checkOutPage.getTitle();
            Assertions.assertThat(checkOutPageTitle)
                    .isNotNull()
                    .isEqualTo("Cart Items");
            Map<String, Integer> productsDisplayedInCheckout = checkOutPage.getItemsFromCheckoutPage();
            Assertions.assertThat(productsDisplayedInCheckout)
                    .isNotEmpty()
                    .containsAllEntriesOf(chosenMoistProductMap);
            log(PASS,"The items and the price displayed in the cart is matching with the chosen products");
            int totalPrice = checkOutPage.calculateTotalPrice();
            Assertions.assertThat(checkOutPage.getTotalPrice()).isEqualTo(totalPrice);
            log(PASS,"The total price is displayed correctly as "+totalPrice);
            StripePaymentPage stripePaymentModal = checkOutPage.clickPayWithCard();
            String stripePaymentModalTitle= stripePaymentModal.getTitle();
            Assertions.assertThat(stripePaymentModalTitle)
                    .isNotNull()
                    .isEqualTo("Stripe.com");
            Assertions.assertThat(stripePaymentModal.getButtonText()).isEqualTo(totalPrice*100);
            log(PASS,"The total price displayed in stripe modal is "+totalPrice);
            ConfirmationPage paymentConfirmationPage = stripePaymentModal.enterEmailId(data.get("email"))
                    .enterCardNumber(data.get("cardNumber"))
                    .enterCardExpiry("03/24")
                    .enterCVV(data.get("cvv"))
//                .enterPincode(data.get("zipcode"))
                    .clickPayButton();

            String paymentConfirmationPageTitle = paymentConfirmationPage.getTitle();
            Assertions.assertThat(paymentConfirmationPageTitle)
                    .isNotNull()
                    .isEqualTo("Confirmation");

            String paymentStatus = paymentConfirmationPage.getPaymentConfirmationStatus();
            Assertions.assertThat(paymentStatus)
                    .containsIgnoringCase("PAYMENT SUCCESS");
//            doPayment(checkOutPage,data);
        }
        else if(temperatureDisplayed>34){
            chosenSunscreenProductMap.clear();
            SunscreensPage sunscreensPage = homePage.clickBuySunscreens();
            String sunscreensPageTitle = sunscreensPage.getTitle();
            Assertions.assertThat(sunscreensPageTitle)
                    .isNotNull()
                    .isEqualTo("The Best Sunscreens in the World!");
            Assertions.assertThat(sunscreensPage.getCartItemsCount()).isZero();
            storeProductDetailsOfSunscreen("SPF-50");
            sunscreensPage.clickAddLeastExpensiveSunscreen("SPF-50");
            storeProductDetailsOfSunscreen("SPF-30");
            sunscreensPage.clickAddLeastExpensiveSunscreen("SPF-30");

            Assertions.assertThat(sunscreensPage.getCartItemsCount())
                    .isNotNegative()
                    .isNotZero()
                    .isEqualTo(2);
            CheckOutPage checkOutPage = sunscreensPage.clickCart();
            String checkOutPageTitle = checkOutPage.getTitle();
            Assertions.assertThat(checkOutPageTitle)
                    .isNotNull()
                    .isEqualTo("Cart Items");
            Map<String, Integer> productsDisplayedInCheckout = checkOutPage.getItemsFromCheckoutPage();
            Assertions.assertThat(productsDisplayedInCheckout)
                    .isNotEmpty()
                    .containsAllEntriesOf(chosenSunscreenProductMap);
            log(PASS,"The items and the price displayed in the cart is matching with the chosen products");

//            doPayment(this.checkOutPage,data);
            int totalPrice = checkOutPage.calculateTotalPrice();
            Assertions.assertThat(checkOutPage.getTotalPrice()).isEqualTo(totalPrice);
            log(PASS,"The total price is displayed correctly as "+totalPrice);
            StripePaymentPage stripePaymentModal = checkOutPage.clickPayWithCard();
            String stripePaymentModalTitle= stripePaymentModal.getTitle();
            Assertions.assertThat(stripePaymentModalTitle)
                    .isNotNull()
                    .isEqualTo("Stripe.com");
            Assertions.assertThat(stripePaymentModal.getButtonText()).isEqualTo(totalPrice*100);
            log(PASS,"The total price displayed in stripe modal is "+totalPrice);
            ConfirmationPage paymentConfirmationPage = stripePaymentModal.enterEmailId(data.get("email"))
                    .enterCardNumber(data.get("cardNumber"))
                    .enterCardExpiry("03/24")
                    .enterCVV(data.get("cvv"))
//                .enterPincode(data.get("zipcode"))
                    .clickPayButton();

            String paymentConfirmationPageTitle = paymentConfirmationPage.getTitle();
            Assertions.assertThat(paymentConfirmationPageTitle)
                    .isNotNull()
                    .isEqualTo("Confirmation");

            String paymentStatus = paymentConfirmationPage.getPaymentConfirmationStatus();
            Assertions.assertThat(paymentStatus)
                    .containsIgnoringCase("PAYMENT SUCCESS");
        }



    }

    /*public void doPayment(CheckOutPage checkOutPage,Map<String,String> data) throws InterruptedException {
        int totalPrice = checkOutPage.calculateTotalPrice();
        Assertions.assertThat(checkOutPage.getTotalPrice()).isEqualTo(totalPrice);
        log(PASS,"The total price is displayed correctly as "+totalPrice);
        StripePaymentPage stripePaymentModal = checkOutPage.clickPayWithCard();
        String stripePaymentModalTitle= stripePaymentModal.getTitle();
        Assertions.assertThat(stripePaymentModalTitle)
                .isNotNull()
                .isEqualTo("Stripe.com");
        Assertions.assertThat(stripePaymentModal.getButtonText()).isEqualTo(totalPrice*100);
        log(PASS,"The total price displayed in stripe modal is "+totalPrice);
        ConfirmationPage paymentConfirmationPage = stripePaymentModal.enterEmailId(data.get("email"))
                .enterCardNumber(data.get("cardNumber"))
                .enterCardExpiry("03/24")
                .enterCVV(data.get("cvv"))
//                .enterPincode(data.get("zipcode"))
                .clickPayButton();

        String paymentConfirmationPageTitle = paymentConfirmationPage.getTitle();
        Assertions.assertThat(paymentConfirmationPageTitle)
                .isNotNull()
                .isEqualTo("Confirmation");

        String paymentStatus = paymentConfirmationPage.getPaymentConfirmationStatus();
        Assertions.assertThat(paymentStatus)
                .containsIgnoringCase("PAYMENT SUCCESS");

    }*/

    public void storeProductDetailsOfMositurizer(String text) {
        Map.Entry<String, Integer> productName = ProductMap.getProductMap(text).entrySet().stream().findFirst().get();
        chosenMoistProductMap.put(productName.getKey(), productName.getValue());
        System.out.println("------");
        chosenMoistProductMap.entrySet().forEach(System.out::println);
    }
    public void storeProductDetailsOfSunscreen(String text){
            Map.Entry<String, Integer> productName = ProductMap.getProductMap(text).entrySet().stream().findFirst().get();
            chosenSunscreenProductMap.put(productName.getKey(), productName.getValue());
            System.out.println("------");
            chosenSunscreenProductMap.entrySet().forEach(System.out::println);
        }



}
