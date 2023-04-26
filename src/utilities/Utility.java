package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {

    /*
     * This method will click on element
     * */
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }
    /*
     * This method will send text to element
     * */
    public void sendTextToElement(By by, String text){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
    /*
     * This method will get text from element
     * */
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }


    public void verifyTwoMessage(String expectedMessage, String actualMessage){
        Assert.assertEquals(expectedMessage,actualMessage);
    }
    public void selectFromDropDown(By by, String text){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);

    }

    public void selectMenu(String menu) {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='" + menu + "']"));
    }


}
