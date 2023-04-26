package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDoem() {
            driver.close();
    }

    @Test
    public void ProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        String menuName = "Computers";
        selectMenu(menuName);
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));

        List<WebElement> beforeSortingElement = driver.findElements(By.xpath("//h2[@class='product-title']//a"));
        List<String> beforeSortingText = new ArrayList<>();
        for (WebElement elements : beforeSortingElement) {
            beforeSortingText.add(elements.getText());
        }
        clickOnElement(By.xpath("(//option[normalize-space()='Name: Z to A'])[1]"));
        Thread.sleep(1000);

        // Verify the Product will arrange in Descending order.
        List<WebElement> afterSortingElement = driver.findElements(By.xpath("//h2[@class='product-title']//a"));
        List<String> afterSortingText = new ArrayList<>();
        for (WebElement elements : afterSortingElement) {
            afterSortingText.add(elements.getText());
        }
        Collections.sort(beforeSortingText);
        Collections.reverse(beforeSortingText);
        Assert.assertEquals(beforeSortingText, afterSortingText);


    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        String menuName = "Computers";
        selectMenu(menuName);
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));
        clickOnElement(By.xpath("//option[@value='5']"));
        Thread.sleep(1000);
        clickOnElement(By.xpath("(//button[@type='button'][normalize-space()='Add to cart'])[1]"));

        // Processor selection from drop down list
        WebElement processordropDown = driver.findElement(By.xpath("//select[@id='product_attribute_1']"));
        Select processorselect = new Select(processordropDown);
        processorselect.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
        Thread.sleep(1000);
        // RAM selection from drop down list
        WebElement ramdropDown = driver.findElement(By.xpath("//select[@id='product_attribute_2']"));
        Select ramselect = new Select(ramdropDown);
        ramselect.selectByVisibleText("8GB [+$60.00]");
        Thread.sleep(1000);
        // click for HDD selection radio button
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        Thread.sleep(1000);
        // click for OS selection radio button
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        Thread.sleep(1000);
        // check box for Software selection
//        clickOnElement(By.xpath("//input[@id='product_attribute_5_10']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        Thread.sleep(1000);
        String expectedMessage = "$1,475.00";
        String actualMessage = getTextFromElement(By.xpath("//span[@id='price-value-1']"));
        verifyTwoMessage(expectedMessage, actualMessage);
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        Thread.sleep(1000);
        String expectedMessage1 = "The product has been added to your shopping cart";
        String actualMessage1 = getTextFromElement(By.xpath("//p[@class='content']"));
        verifyTwoMessage(expectedMessage1, actualMessage1);
        Thread.sleep(1000);
        // close the message
        clickOnElement(By.xpath("//span[@class='close']"));
        Thread.sleep(1000);
        // Mouse hover over the Shopping cart
        Actions actions = new Actions(driver);
        WebElement ShoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        actions.moveToElement(ShoppingCart).build().perform();
        Thread.sleep(1000);

        clickOnElement(By.xpath("//button[@type='button']"));
        Thread.sleep(1000);

        // Verify the message "Shopping cart
        String expectedMessage2 = "Shopping cart";
        String actualMessage2 = getTextFromElement(By.xpath("//h1"));
        verifyTwoMessage(expectedMessage2, actualMessage2);
        Thread.sleep(1000);

        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@id='updatecart']"));
        Thread.sleep(1000);

        String expectedPrice = "$2,950.00";
        String actualPrice = getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]"));
        verifyTwoMessage(expectedPrice, actualPrice);

        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        Thread.sleep(1000);

        clickOnElement(By.xpath("//button[@id='checkout']"));
        Thread.sleep(1000);

        // Verify the Text “Welcome, Please Sign In!”
        String expectedSignInMessage = "Welcome, Please Sign In!";
        String actualSignInMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        verifyTwoMessage(expectedSignInMessage, actualSignInMessage);
        Thread.sleep(1000);

        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));
        Thread.sleep(1000);

        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Ketan");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Patel");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "ketan123@gmail.com");

        WebElement countryDropDown = driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
        Select countrySelect = new Select(countryDropDown);
        countrySelect.selectByVisibleText("United Kingdom");

        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "3 Repton Road.");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "HA3 9QD");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07812345678");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        Thread.sleep(2000);
        // Call select FromDropDown method from Utility class to select Credit card type
        selectFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Ketan Patel");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5105105105105100");
        selectFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "06");
        selectFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2024");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "123");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        Thread.sleep(2000);

        //  Verify “Payment Method” is “Credit Card"

        String expectedPaymentMethodMessage = "Credit Card";
        String actualPaymentMethodMessage = getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        verifyTwoMessage(expectedPaymentMethodMessage, actualPaymentMethodMessage);
        Thread.sleep(2000);

        // Verify “Shipping Method” is “Next Day Air”
        String expectedShippingMethodMessage = "Next Day Air";
        String actualShippingMethodMessage = getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        verifyTwoMessage(expectedShippingMethodMessage, actualShippingMethodMessage);
        Thread.sleep(2000);

        // Verify Total is “$2,950.00”
        String expectedTotalMessage = "$2,950.00";
        String actualTotalMessage = getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]"));
        verifyTwoMessage(expectedTotalMessage, actualTotalMessage);
        Thread.sleep(2000);

        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));
        Thread.sleep(1000);

        // Verify the Text “Thank You”
        String expectedThankYouMessage = "Thank you";
        String actualThankYouMessage = getTextFromElement(By.xpath("//h1"));
        verifyTwoMessage(expectedThankYouMessage, actualThankYouMessage);
        Thread.sleep(2000);

        // Verify the message “Your order has been successfully processed!”
        String expectedSuccessFullMessage = "Your order has been successfully processed!";
        String actualSuccessFullMessage = getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));
        verifyTwoMessage(expectedSuccessFullMessage, actualSuccessFullMessage);
        Thread.sleep(2000);

        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));
        Thread.sleep(2000);

        // Verify the text “Welcome to our store”
        String expectedWelcomeToOurStoreMessage = "Welcome to our store";
        String actualWelcomeToOurStoreMessage = getTextFromElement(By.xpath("//h2"));
        verifyTwoMessage(expectedWelcomeToOurStoreMessage, actualWelcomeToOurStoreMessage);



    }
}
