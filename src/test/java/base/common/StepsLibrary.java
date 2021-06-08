package base.common;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import com.qmetry.qaf.automation.util.PropertyUtil;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import java.util.NoSuchElementException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qmetry.qaf.automation.util.Validator;
import static base.ConfigurationManager.getBundle;

public class StepsLibrary {
	private static String getJsDndHelper() {
		return "function simulateDragDrop(sourceNode, destinationNode) {\r\n" +
				"		    var EVENT_TYPES = {\r\n" +
				"		        DRAG_END: 'dragend',\r\n" +
				"		        DRAG_START: 'dragstart',\r\n" +
				"		        DROP: 'drop'\r\n" +
				"		    }\r\n" +
				"		\r\n" +
				"		    function createCustomEvent(type) {\r\n" +
				"		        var event = new CustomEvent(\"CustomEvent\")\r\n" +
				"		        event.initCustomEvent(type, true, true, null)\r\n" +
				"		        event.dataTransfer = {\r\n" +
				"		            data: {\r\n" +
				"		            },\r\n" +
				"		            setData: function(type, val) {\r\n" +
				"		                this.data[type] = val\r\n" +
				"		            },\r\n" +
				"		            getData: function(type) {\r\n" +
				"		                return this.data[type]\r\n" +
				"		            }\r\n" +
				"		        }\r\n" +
				"		        return event\r\n" +
				"		    }\r\n" +
				"		\r\n" +
				"		    function dispatchEvent(node, type, event) {\r\n" +
				"		        if (node.dispatchEvent) {\r\n" +
				"		            return node.dispatchEvent(event)\r\n" +
				"		        }\r\n" +
				"		        if (node.fireEvent) {\r\n" +
				"		            return node.fireEvent(\"on\" + type, event)\r\n" +
				"		        }\r\n" +
				"		    }\r\n" +
				"		\r\n" +
				"		    var event = createCustomEvent(EVENT_TYPES.DRAG_START)\r\n" +
				"		    dispatchEvent(sourceNode, EVENT_TYPES.DRAG_START, event)\r\n" +
				"		\r\n" +
				"		    var dropEvent = createCustomEvent(EVENT_TYPES.DROP)\r\n" +
				"		    dropEvent.dataTransfer = event.dataTransfer\r\n" +
				"		    dispatchEvent(destinationNode, EVENT_TYPES.DROP, dropEvent)\r\n" +
				"		\r\n" +
				"		    var dragEndEvent = createCustomEvent(EVENT_TYPES.DRAG_END)\r\n" +
				"		    dragEndEvent.dataTransfer = event.dataTransfer\r\n" +
				"		    dispatchEvent(sourceNode, EVENT_TYPES.DRAG_END, dragEndEvent)\r\n" +
				"		}";
	}
	public static void customStep(WebDriver driver) {
			 //Custom steps
			//  driver.get("http://www.google.com");
	}

	public static void implicitWait(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

    public static void dragAndDropPerform(WebDriver driver,WebElement source, WebElement target) {
		((JavascriptExecutor) driver).executeScript(getJsDndHelper() + "simulateDragDrop(arguments[0], arguments[1])",source,target);
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source,target).build().perform();
	}

	public static void dragAndDropOnAndPerform(WebDriver driver, WebElement source, String Xtarget, String Ytarget) {
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(source,Integer.parseInt(Xtarget), Integer.parseInt(Ytarget)).build().perform();
	}

	public static void dragAndDropOnValuePerform(WebDriver driver, WebElement source, String value) {
		String executScriptValue ="arguments[0].setAttribute('value',"+Integer.parseInt(value)+");if(typeof(arguments[0].onchange) === 'function'){arguments[0].onchange('');}";
		((JavascriptExecutor) driver).executeScript(executScriptValue,source);
	}

	public static void executeJavaScript(WebDriver driver, String executScriptValue) {
		((JavascriptExecutor) driver).executeScript(executScriptValue);
	}

	public static void executeAsyncJavaScript(WebDriver driver, String executScriptValue) {
		((JavascriptExecutor) driver).executeAsyncScript(executScriptValue);
	}

	public static void acceptAlert(WebDriver driver) {
		if (checkAlert(driver,0)) {
			driver.switchTo().alert().accept();
		}
	}

	public static void dismissAlert(WebDriver driver) {
		if (checkAlert(driver,0)) {
			driver.switchTo().alert().dismiss();
		}
	}

	public static String getAlertText(WebDriver driver) {
		if (checkAlert(driver,0)) {
		Alert alert= driver.switchTo().alert();
		return alert.getText();
		}else{
			return "";
		}
	}

	public static void setAlertText(WebDriver driver,String input) {
		if (checkAlert(driver,0)) {
			driver.switchTo().alert().sendKeys(input);
		}
	}
	public static void verifyAlertPresent(WebDriver driver,String timeout) {
		if (!checkAlert(driver,Long.valueOf(timeout))){
			Validator.verifyFalse(true, "Alert is not present.", "Alert is present.");
		}
	}
	public static void verifyAlertNotPresent(WebDriver driver,String timeout) {
		if (checkAlert(driver,Long.valueOf(timeout))){
			Validator.verifyFalse(true, "Alert is present.", "Alert is not present.");
		}
	}

	public static void waitForAlert(WebDriver driver,String timeout) {
		try{
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(timeout));
		wait.until(ExpectedConditions.alertIsPresent());
		}catch(Exception e){
			System.out.println("Exception Occured during waitforAlert : "+e);
		}
	}

	public static void storeIntoVariable(String varKey,String varValue) {
		getBundle().addProperty(varKey, varValue);
	}

	public static boolean checkAlert(WebDriver driver,long timeout) {
		boolean returnvalue = false;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			returnvalue = true;
		} catch (NoSuchElementException e) {
			returnvalue = false;
		} catch (TimeoutException te) {
			returnvalue = false;
		} catch(NoAlertPresentException ex){
			returnvalue = false;
		}catch (Exception ex) {
			returnvalue = false;
		}
		return returnvalue;
	}
}
