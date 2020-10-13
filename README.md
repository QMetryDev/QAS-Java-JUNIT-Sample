# Java Junit

### To Get Started

#### Pre-requisites
1.JAVA installed globally in the system.

2.Chrome or Firefox browsers installed.

3.JUnit installed in system.

4.MAVEN installed in system.

5.Compatible chromedriver path should be set in your system and in application.properties file if it is present in your project folder for command line execution.

#### About
* This is sample project with Maven directory structure:
* The 'src' directory contains all java files and is a place holder for other java files.

### If you want to run old project(Project-below QAS version 1.37.3) in headless mode then you have to add below steps.

### web
* Add below steps in setup() function located in src\test\java\base\WebTestBase.java
		if (System.getenv("firefoxDriver") != null) {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setHeadless(System.getenv("qasHeadlessMode") != null
						? (System.getenv("qasHeadlessMode").equals("true") ? true : false)
						: false);
				driver = new FirefoxDriver(firefoxOptions);
		} else {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setHeadless(System.getenv("qasHeadlessMode") != null
						? (System.getenv("qasHeadlessMode").equals("true") ? true : false)
						: false);
				driver = new ChromeDriver(chromeOptions);
		}
	driver=new ChromeDriver(chromeOptions);

### MobileWeb
* Add below steps in setup() function located in src\test\java\base\MobilewebTestBase.java
		if (System.getenv("firefoxDriver") != null) {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setHeadless(System.getenv("qasHeadlessMode") != null
						? (System.getenv("qasHeadlessMode").equals("true") ? true : false)
						: false);
				driver = new FirefoxDriver(firefoxOptions);
		} else {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setHeadless(System.getenv("qasHeadlessMode") != null
						? (System.getenv("qasHeadlessMode").equals("true") ? true : false)
						: false);
				Map<String, String> mobileEmulation = new HashMap<String, String>();
				mobileEmulation.put("deviceName", "iPhone X");
				chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
				driver = new ChromeDriver(chromeOptions);
		}

#### run the test
* To change/modify dependencies
  ```
  check pom.xml
  ```
* To run the project from command prompt, go to project home.
	```
	 mvn clean test
	 mvn clean site
	 ```
* To run project using Tags :
	```
	 mvn test -Dgroups=tag1,tag2
	 ```

#### View Results.
	Open dashboard.htm

```Note: ```This sample project uses chrome driver and it requires chrome driver binary.

#### Integrate with QTM
Please refer https://github.com/qmetry/qmetry-test-management-maven-plugin
