package tests.web.junittestsuite;
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
import base.WebTestBase;

 


public class WebJunitTest extends WebTestBase{

    WebDriverWait wait;

    @Test
    public void WebJunitTest() throws Exception {
    
                 driver.get("https://www.gmail.com"); 
                 driver.findElement(By.id("identifierId")).clear(); 
                 driver.findElement(By.id("identifierId")).sendKeys("demoqas2019@gmail.com"); 
                 driver.findElement(By.xpath("//div[@id='view_container']/div/div/div[2]/div/div/div/form/span/section/div")).click(); 
                 try {
					assertEquals("demoqas2019@gmail.com",  driver.findElement(By.id("identifierId")).getAttribute("value"));

				} catch (Throwable e) {
					verificationErrors.append(e.toString());
				} 
                 driver.findElement(By.xpath("//div[@id='identifierNext']/span/span")).click(); 
                 driver.findElement(By.name("password")).clear(); 
                 driver.findElement(By.name("password")).sendKeys("QAS@2019"); 
                 driver.findElement(By.xpath("//div[@id='view_container']/div/div/div[2]/div/div[2]/div/div")).click(); 
                 try {
					assertEquals("QAS@2019",  driver.findElement(By.name("password")).getAttribute("value"));

				} catch (Throwable e) {
					verificationErrors.append(e.toString());
				} 
    }

}
