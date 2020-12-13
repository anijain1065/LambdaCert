package com.lambdaCert.project;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POM extends BaseClass{
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	
	public static String username_txt = "//input[@id='username']";
	public static String password_txt = "//input[@id='password']";
	public static String login_btn = "//button[text()='Login']";
	public static String accept_lnk = "//a[text()='I ACCEPT']";
	public static String developerEmail_txt = "//input[@id='developer-name']";
	public static String onceAyear_RBtn = "//input[@value='Once a year']";
	public static String customerExp_chkBox = "//input[@id='customer-service']";
	public static String discount_chkBox = "//input[@id='discounts']";
	public static String deliveryTime_chkBox = "//input[@id='delivery-time']";
	public static String payment_dd = "//select[@id='preferred-payment']";
	public static String purchase_chkBox = "//input[@id='tried-ecom']";
	public static String slider_lnk= "//div[@id='slider']";
	public static String pointer_lnk = "//span[@tabindex='0']";
	public static String uploadImg_btn = "//label[@id='img']";
	public static String submit_btn = "//button[@id='submit-button']";
	
	public static String jenkins_img = "//img[@alt='LambdaTest Jenkins integration']";
	
	public static String thankYou_txt = "//h1[text()='Thank you!']";
	
	public POM(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}
	
	public String getLnk(String element) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		String link = driver.findElement(By.xpath(element)).getAttribute("href");
		return link;
	}
	
	public String getTxt(String element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		String value = driver.findElement(By.xpath(element)).getText();
		return value;
	}
	
	public void clickBtn(String element) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		driver.findElement(By.xpath(element)).click();
	}
	
	public void clickInpTxt(String element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
			this.driver.findElement(By.xpath(element)).click();
		}catch(Exception e) {
			GoToSleep(1000);
			this.driver.findElement(By.xpath(element)).click();
		}
	}
	
	public void fillTxt(String element, String keyword) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		clickInpTxt(element);
		driver.findElement(By.xpath(element)).sendKeys(new CharSequence[] {keyword});
	}
	
	public void waitForCompletepageLoad() {
		int i = 10;
		while(i > 0) {
			if(!((JavascriptExecutor)driver).executeScript("return window.performance.timimg.loadEventEnd").toString().equals("0"))
				break;
			GoToSleep(1000);
			System.out.println("Sleeping for 1 second");
			i--;
		}
		GoToSleep(1000);
	}
	
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }
	}
	
}
