package com.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.utils.AppConstants;

public class BaseTest {

	private WebDriver driver;

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	
	private void init_driver(String browserName) throws Exception {
		System.out.println("Initialising Browser: " + browserName);

		browserName = browserName.toLowerCase();

		if (browserName.equals("chrome") || browserName.contains("chrome")) {
			tlDriver.set(new ChromeDriver());
		} else if (browserName.equals("firefox") || browserName.contains("firefox")) {
			tlDriver.set(new FirefoxDriver());
		} else if (browserName.equals("safari") || browserName.contains("safari")) {
			tlDriver.set(new SafariDriver());
		} else if (browserName.equals("edge") || browserName.contains("edge")) {
			tlDriver.set(new EdgeDriver());
		} else {
			throw new Exception(
					"Please enter Valid Browser Name \n Accepted names are: chrome, firefox, safari and edge");
		}

	}

	/*
	 * Used for getting the driver instance
	 */
	public static synchronized WebDriver getDriver() {

		return tlDriver.get();
	}
	
//	@BeforeSuite
//	public void before(ITestContext ctx) {
//	  ctx.getCurrentXmlTest().getSuite().getParameters().put("browser",System.getProperty("browser"));
//	}
	
	@Parameters("browser")
	@BeforeMethod
	protected void launchBrowser(String browserName) throws Exception {
		
		BaseTest baseTest = new BaseTest();
		baseTest.init_driver(browserName);

		driver = getDriver();
		driver.manage().window().maximize();

	}
	
	@BeforeMethod(dependsOnMethods = "launchBrowser")
	@Parameters("env")
	protected void setUp(String environment) throws Exception {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		switch (environment.toLowerCase()) {
		case "prod":
			driver.get(AppConstants.APP_PROD_URL);
			break;
		case "stg":
			driver.get(AppConstants.APP_STG_URL);
			break;
		default:
			driver.quit();
			throw new Exception("Invalid Environment! --> " + environment);
		}
		
	}
	
	@AfterMethod
	protected void quitBrowser() {
		driver.quit();
	}
	

}
