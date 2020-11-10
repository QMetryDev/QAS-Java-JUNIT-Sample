package base.common;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import com.qmetry.qaf.automation.util.PropertyUtil;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StepsLibrary {
	
	public static void customStep(WebDriver driver) {
			 //Custom steps
			//  driver.get("http://www.google.com"); 
	}
}
