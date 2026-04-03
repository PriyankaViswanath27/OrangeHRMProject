package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utilities.LoggerManager;

public class BaseClass {

	protected static Properties prop;
	protected static WebDriver driver;
	private static ActionDriver actionDriver;
	//Initialize the logger in BaseClass
	public static final Logger logger=LoggerManager.getLogger(BaseClass.class); 
	//only once logger object can be initialized so declared as final
	
	@BeforeSuite
	public void loadConfig() throws IOException
	{
		// Load configuration file
				prop = new Properties();
				// read the file using FileInputStream object
				FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
				// load the file
				prop.load(fis);
				logger.info("config.properties file loaded");
	}
	
	@BeforeMethod 
	public void setup() throws IOException {
		
		System.out.println("Setting up web driver for:"+this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(2);
		
		logger.info("WebDriver initialized and Browser Maximized");
		logger.trace("This is a Trace message");
		logger.error("This is an error message");
		logger.debug("This is a debug message");
		logger.fatal("This is a fatal message");
		logger.warn("This is a warn message");
		
		//Initialize the actionDriver only once
		if(actionDriver==null) {
			actionDriver=new ActionDriver(driver);
			logger.info("ActionDriver instance is created");
		}
	}
	
	// Initialize the webDriver based on browser defined in config.properties file
	private void launchBrowser()
	{
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			logger.info("ChromeDriver Instance is created");
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			logger.info("FirefoxDriver Instance is created");
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			logger.info("EdgeDriver Instance is created");
		} else {
			throw new IllegalArgumentException("Browser not supported:" + browser);
		}
	}
	//add @BeforeMethod annotation because each test class calls this method before test execution
   
	/*to configure browser settings such as implicit wait, maximize browser and navigate to url*/
	private void configureBrowser()
	{
		// Implement implicit wait
				int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

				// Maximize the driver
				driver.manage().window().maximize();

				// navigate to url
				try {
					driver.get(prop.getProperty("url"));
				} catch (Exception e) {
					System.out.println("Failed to navigate to the URL:"+e.getMessage());
				}
	}
	

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit(); // closes all active browsers and close web driver session
			} catch (Exception e) {
				
				System.out.println("Unable to quit the driver:"+e.getMessage());
			} 
			// driver.close();//closes browser only but not end web driver session
		}
		logger.info("WebDriver instance is closed.");
		driver=null;
		actionDriver=null;

	}
	
	/*To access driver outside the package, we write getter and setter methods for driver, properties*/
	//Driver getter method
	/*public WebDriver getDriver()
	{
		return driver;
	}*/
	//Driver setter method
	public void setDriver(WebDriver driver)
	{
		this.driver=driver; 
	}
	
	//Getter method for prop
	public static Properties getProp()
	{
		return prop;
	}
	
	//Getter method for WebDriver
	public static WebDriver getDriver() {
		
			if(driver==null) 
			{
				System.out.println("WebDriver is not initialized");
				throw new IllegalStateException("WebDriver is not initialized");
			} 
		return driver;
	}
	
	//Getter method for actionDriver
	public static ActionDriver getActionDriver() {
		
		if(actionDriver==null) 
		{
			System.out.println("ActionDriver is not initialized");
			throw new IllegalStateException("ActionDriver is not initialized");
		} 
	return actionDriver;
}
	//static wait for pause
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
}
