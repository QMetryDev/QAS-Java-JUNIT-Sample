package base;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MobilewebTestBase {
	protected static WebDriver driver;
	protected static String baseUrl = "https://qas.qmetry.com/bank/";
	protected static boolean acceptNextAlert = true;
	protected static StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		ChromeOptions chromeOptions = new ChromeOptions();
		// chromeOptions.addArguments("start-maximized", "disable-extensions", "test-type", "no-default-browser-check",
		// 		"ignore-certificate-errors");
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "iPhone X");
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
