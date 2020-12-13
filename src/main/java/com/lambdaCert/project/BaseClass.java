package com.lambdaCert.project;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass
{
	public String username = "anijain555";
	public String accesskey = "KWFQ6lJuAWtay0SsqRDzqtvWJUNZRzDs9BVopHRMRMw4MpiKHE";
	public static RemoteWebDriver remoteDriver = null;
	protected WebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;
	
	public void GoToSleep(int TimeInMillis) {
		try {
			Thread.sleep(TimeInMillis);
		}catch(Exception e) {
		}
	}

	public RemoteWebDriver initializeRemoteDriver() throws Exception
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("version", "70.0");
		capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
		capabilities.setCapability("build", "LambdaTestSampleApp");
		capabilities.setCapability("name", "LambdaTestJavaSample");
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true); // To enable step by step screenshot
		capabilities.setCapability("video", true); // To enable video recording
		capabilities.setCapability("console", true); // To capture console logs
		try {
			remoteDriver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return remoteDriver;
	}
	
	public WebDriver initializeNormalDriverHeadless() throws Exception
	{
		try {
			WebDriverManager.chromedriver().version("87.0.4280.66").setup();
	    	ChromeOptions options = new ChromeOptions();
	    	options.addArguments("start-maximized"); 
	    	options.addArguments("enable-automation"); 
	    	options.addArguments("--no-sandbox"); 
	    	options.addArguments("--disable-infobars");
	    	options.addArguments("--disable-dev-shm-usage");
	    	options.addArguments("--disable-browser-side-navigation"); 
	    	options.addArguments("--disable-gpu");
	    	options.addArguments("headless");
	    	driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return driver;
	}
	
	public WebDriver initializeNormalDriver() throws Exception{
		try {
			String path = System.getProperty("user.dir") + "\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", path );
			driver = new ChromeDriver();
			String downloadFilepath = System.getProperty("user.dir");
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
//			options.addArguments("headless");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return driver;
	}
}
