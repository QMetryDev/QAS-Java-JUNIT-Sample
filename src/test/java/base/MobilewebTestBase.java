package base;


import java.util.HashMap;
import java.util.Map;
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

public class MobilewebTestBase {
	protected static WebDriver driver;
	protected static String baseUrl = "https://qas.qmetry.com/bank/";
	protected static boolean acceptNextAlert = true;
	protected static StringBuffer verificationErrors = new StringBuffer();
	protected InputStream inputStream;
	@BeforeEach
	public void setUp() throws Exception {
		if (System.getenv("firefoxDriver") != null) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(System.getenv("qasHeadlessMode") != null
					? (System.getenv("qasHeadlessMode").equals("true") ? true : false)
					: false);
			driver = new FirefoxDriver(firefoxOptions);
		} else if(System.getenv("chromeDriver") != null){
		ChromeOptions chromeOptions = new ChromeOptions();
		// chromeOptions.addArguments("start-maximized", "disable-extensions", "test-type", "no-default-browser-check",
		// 		"ignore-certificate-errors");
		chromeOptions.setHeadless(System.getenv("qasHeadlessMode")!=null?(System.getenv("qasHeadlessMode").equals("true")?true:false):false);
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "iPhone X");
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		driver = new ChromeDriver(chromeOptions);
		}else{
			driver = getPropertiesAndSetValue();
			if(driver ==null){
				throw new IllegalArgumentException("Please provide valid driver name in application.properties");
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

		public boolean loadProperties(String isHeadlessMode) {
        if (isHeadlessMode != null && isHeadlessMode != "") {
            if(isHeadlessMode.equals("true")){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
	public WebDriver getPropertiesAndSetValue() {
		PropertyUtil p = new PropertyUtil(
				System.getProperty("application.properties.file", "resources/application.properties"));
		String driverName = p.getPropertyValue("driver.name");
		if (driverName != null && !driverName.equals("")) {
			if (driverName.equalsIgnoreCase("chromedriver")) {
					if (p.getPropertyValue("webdriver.chrome.driver") != null) {
					if (new File(p.getPropertyValue("webdriver.chrome.driver")).exists()) {
						System.setProperty("webdriver.chrome.driver", p.getPropertyValue("webdriver.chrome.driver"));
						ChromeOptions chromeOptions = new ChromeOptions();
						chromeOptions.setHeadless(System.getenv("qasHeadlessMode") != null
								? (System.getenv("qasHeadlessMode").equals("true") ? true : false)
								: false);
						Map<String, String> mobileEmulation = new HashMap<String, String>();
						mobileEmulation.put("deviceName", "iPhone X");
						chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
						return new ChromeDriver(chromeOptions);
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else if (driverName.equalsIgnoreCase("geckodriver")) {
				System.setProperty("webdriver.gecko.driver", p.getPropertyValue("webdriver.gecko.driver"));
				return new FirefoxDriver();
			} else if (driverName.equalsIgnoreCase("edgedriver")) {
				System.setProperty("webdriver.edge.driver", p.getPropertyValue("webdriver.edge.driver"));
				return new EdgeDriver();
			} else {
				System.setProperty("webdriver.ie.driver", p.getPropertyValue("webdriver.ie.driver"));
				return new InternetExplorerDriver();
			}
		}else{
			return null;
		}
	}
}
