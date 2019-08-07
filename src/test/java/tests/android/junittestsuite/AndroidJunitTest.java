package tests.android.junittestsuite;
/**
* @author: rinkesh.jain
*
*/
import org.hamcrest.CoreMatchers;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
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
import base.AndroidTestBase;

 


public class AndroidJunitTest extends AndroidTestBase{

    WebDriverWait wait;

    @Test
    public void AndroidJunitTest() throws Exception {
    
                 driver.findElement(MobileBy.AccessibilityId("email")).sendKeys("Bob"); 
                 try {
					assertEquals("Bob",  driver.findElement(MobileBy.AccessibilityId("email")).getText());

				} catch (Throwable e) {
					verificationErrors.append(e.toString());
				} 
                 driver.findElement(MobileBy.AccessibilityId("password")).sendKeys("Bob"); 
                 try {
					assertEquals("Bob",  driver.findElement(MobileBy.AccessibilityId("password")).getText());

				} catch (Throwable e) {
					verificationErrors.append(e.toString());
				} 
                 driver.findElement(MobileBy.AccessibilityId("signIn")).click(); 
                 try {
					driver.findElement(MobileBy.AccessibilityId("transacations"));
				} catch (Throwable e) {
					verificationErrors.append(e.toString());
				} 
                 driver.findElement(MobileBy.AccessibilityId("transacations")).click(); 
                 driver.findElement(MobileBy.AccessibilityId("enterAmount")).sendKeys("100"); 
                 try {
					assertEquals("100",  driver.findElement(MobileBy.AccessibilityId("enterAmount")).getText());

				} catch (Throwable e) {
					verificationErrors.append(e.toString());
				} 
                 try {
					driver.findElement(MobileBy.AccessibilityId("credit"));
				} catch (Throwable e) {
					verificationErrors.append(e.toString());
				} 
                 driver.findElement(MobileBy.AccessibilityId("credit")).click(); 
                 driver.findElement(MobileBy.AccessibilityId("Fund Transfer")).click(); 
                 driver.findElement(MobileBy.AccessibilityId("enterAmount")).sendKeys("100"); 
                 try {
					assertEquals("100",  driver.findElement(MobileBy.AccessibilityId("enterAmount")).getText());

				} catch (Throwable e) {
					verificationErrors.append(e.toString());
				} 
                 driver.findElement(MobileBy.AccessibilityId("fund transfer")).click(); 
    }

}
