package electronics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.util.Random;

public class ElectronicsTest extends Utility {

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
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {

        // Mouse hover over the "Electronics" tab
        Actions actions = new Actions(driver);
        WebElement electronics = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        actions.moveToElement(electronics).build().perform();
        Thread.sleep(1000);
        // Mouse Hover on “Cell phones” and click
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        Thread.sleep(1000);

        //  Verify the text “Cell phones”
        String expectedTextMessage = "Cell phones";
        String actualTextMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        verifyTwoMessage(expectedTextMessage, actualTextMessage);
    }


    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        // Mouse hover over the "Electronics" tab
        Actions actions = new Actions(driver);
        WebElement electronics = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        actions.moveToElement(electronics).build().perform();
        Thread.sleep(1000);
        // Mouse Hover on “Cell phones” and click
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        Thread.sleep(1000);

        //  Verify the text “Cell phones”
        String expectedTextMessage = "Cell phones";
        String actualTextMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        verifyTwoMessage(expectedTextMessage, actualTextMessage);
        Thread.sleep(1000);

        // Click on List View Tab
        clickOnElement(By.xpath("//a[normalize-space()='List']"));
        Thread.sleep(2000);
        // Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.xpath("//a[normalize-space()='Nokia Lumia 1020']"));
        Thread.sleep(2000);

        // Verify the text “Nokia Lumia 1020"
        String expectedNokiaLumia1020TextMessage = "Nokia Lumia 1020";
        String actualNokiaLumia1020TextMessage = getTextFromElement(By.xpath("//h1"));
        verifyTwoMessage(expectedNokiaLumia1020TextMessage, actualNokiaLumia1020TextMessage);
        Thread.sleep(1000);

        // Verify the price “$349.00”
        String expectedPriceTextMessage = "$349.00";
        String actualPriceTextMessage = getTextFromElement(By.xpath("//span[@id='price-value-20']"));
        verifyTwoMessage(expectedPriceTextMessage, actualPriceTextMessage);
        Thread.sleep(1000);

        sendTextToElement(By.xpath("//input[@id='product_enteredQuantity_20']"), "2");
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));
        Thread.sleep(1000);

        // Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String expectedaddedShoppingCartTextMessage = "The product has been added to your shopping cart";
        String actualaddShoppingCartTextMessage = getTextFromElement(By.xpath("//p[@class='content']"));
        verifyTwoMessage(expectedaddedShoppingCartTextMessage, actualaddShoppingCartTextMessage);
        Thread.sleep(1000);
        clickOnElement(By.xpath("//span[@title='Close']"));

        // Then MouseHover on "Shopping cart" and Click on "GO TO CART" button
        Actions action = new Actions(driver);
        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        action.moveToElement(shoppingCart).build().perform();
        // Mouse hover on "Go To Cart"
        clickOnElement(By.xpath("//button[normalize-space()='Go to cart']"));
        Thread.sleep(1000);

       // Verify the message "Shopping cart"
        String expectedShoppingCartTextMessage = "Shopping cart";
        String actualShoppingCartTextMessage = getTextFromElement(By.xpath("//h1"));
        verifyTwoMessage(expectedShoppingCartTextMessage, actualShoppingCartTextMessage);
        Thread.sleep(2000);

       // Verify the quantity is 2
        String expectedquantityTextMessage = "2";
        String actualquantityTextMessage = driver.findElement(By.xpath("//input[@class='qty-input']")).getAttribute("value");
        verifyTwoMessage(expectedquantityTextMessage, actualquantityTextMessage);
        Thread.sleep(2000);

        // Verify the Total $698.00
        String expectedtotalTextMessage = "$698.00";
        String actualtotalTextMessage = getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));
        verifyTwoMessage(expectedtotalTextMessage, actualtotalTextMessage);
        Thread.sleep(2000);

        // click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        // Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));
        Thread.sleep(1000);

        // Verify the Text “Welcome, Please Sign In!”
        String expectedWelcomeSignInMessage = "Welcome, Please Sign In!";
        String actualWelcomeSignInMessage = getTextFromElement(By.xpath("//h1"));
        verifyTwoMessage(expectedWelcomeSignInMessage, actualWelcomeSignInMessage);
        Thread.sleep(2000);

        // Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[normalize-space()='Register']"));

        //Verify the text “Register”
        String expectedRegisterTextMessage = "Register";
        String actualRegisterTextMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Register']"));
        verifyTwoMessage(expectedRegisterTextMessage, actualRegisterTextMessage);
        Thread.sleep(2000);

        // Fill the mandatory fields on Register page
        clickOnElement(By.xpath("//input[@id='gender-male']"));
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "Ketan");
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Patel");

        //Create everytime a different email while executing the test
        Random randomGenerator = new Random();// random generator class
        int randomNum = randomGenerator.nextInt(1000);

        //Find the 'Email' field element and enter 'Email' into field
        String userName = "Ketan";
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(userName +
                randomNum + "@gmail.com");

//        sendTextToElement(By.xpath("//input[@id='Email']"), "ketan1234@gmail.com");
        sendTextToElement(By.xpath("//input[@id='Password']"), "$Ab123456");
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "$Ab123456");
        clickOnElement(By.xpath("//button[@id='register-button']"));

        // Verify the message “Your registration completed”
        String expectedRegistrationCompletedMessage = "Your registration completed";
        String actualRegistrationCompletedMessage = getTextFromElement(By.xpath("//div[@class='result']"));
        verifyTwoMessage(expectedRegistrationCompletedMessage, actualRegistrationCompletedMessage);
        Thread.sleep(2000);

        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
        Thread.sleep(2000);

        //2.24 Verify the text “Shopping card”
        String expectedShoppingCartMessage = "Shopping cart";
        String actualShoppingCartMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        verifyTwoMessage(expectedShoppingCartMessage, actualShoppingCartMessage);
    }


}