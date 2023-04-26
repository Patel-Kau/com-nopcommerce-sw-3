package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDoem() {
            driver.close();
    }

    public void selectMenu(String menu) {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='" + menu + "']"));
    }

    @Test
    public void verifyPageNavigation() {
        String menuName = "Electronics";
        selectMenu(menuName);
        String expectedMessage = "Electronics";
        String actualMessage = getTextFromElement(By.xpath("//h1"));
        verifyTwoMessage(expectedMessage, actualMessage);
    }


}
