package tests.mobileweb.junittestsuite;
/**
* @author: nidhi.shah
*
*/
import org.hamcrest.CoreMatchers;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.*;
import static base.ConfigurationManager.changeDefaultLocalTo;
import static base.ConfigurationManager.getBundle;
import base.MobilewebTestBase;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
 


public class MobilewebTest extends MobilewebTestBase{

    WebDriverWait wait;
    String amountToBeCredited = "1000";
    String baseURL = "https://qas.qmetry.com/bank";
    String username = "Bob";
    String password = "Bob";
    int currentBalance;

    @Test
    public void MobilewebTest() throws Exception {
    
                // Navigate to URL
        driver.get(baseURL);
        // Maximize window
        driver.manage().window().maximize();

        // Login
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();

        // Assert - Successful login
        assertTrue(driver.findElement(By.xpath("//button")).isDisplayed());

        // Get the value of current balance
        currentBalance = Integer
                .parseInt((driver.findElement(By.id("user-globe-rank")).getText()).toString().split(" ")[1]);

        // Credit the amount
        driver.findElement(By.xpath("//div[2]/input")).clear();
        driver.findElement(By.xpath("//div[2]/input")).sendKeys(amountToBeCredited);
        driver.findElement(By.xpath("//div[2]/span/button")).click();
        currentBalance = currentBalance + Integer.parseInt(amountToBeCredited);

        // Assert - Transaction successful message is displayed
        try {
            assertTrue(driver.findElement(By.xpath("//div/div")).isDisplayed());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

        // Assert - Amount have been credited
        try {
            assertEquals("$ " + currentBalance, driver.findElement(By.id("user-globe-rank")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

        // Logout
        driver.findElement(By.xpath("//button")).click();

        // Assert - Successful logout
        try {
            assertTrue(driver.findElement(By.id("btnLogin")).isDisplayed());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

}
