package com.lambdaCert.project;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lambdaCert.project.POM;

public class Solution extends BaseClass
{
	WebDriver driver;
	POM pom;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() throws Exception {
		try {
			BaseClass generic = new BaseClass();
			driver = generic.initializeRemoteDriver();
			pom = new POM(driver);
			wait = new WebDriverWait(driver, 20);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void TC_01() throws AWTException
	{
		String username = "lambda";
		String password = "lambda123";
		String developerEmailId = "anijain555@gmail.com";
		String url = "https://www.lambdatest.com/automation-demos/";
		String url2 = "https://www.lambdatest.com/selenium-automation";
		driver.get(url);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(POM.accept_lnk)));
		if(driver.findElement(By.xpath(POM.accept_lnk)) != null) {
			driver.findElement(By.xpath(POM.accept_lnk)).click();
			GoToSleep(1000);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.username_txt)));
		pom.clickInpTxt(POM.username_txt);
		pom.fillTxt(POM.username_txt, username);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.password_txt)));
		pom.clickInpTxt(POM.password_txt);
		pom.fillTxt(POM.password_txt, password);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.login_btn)));
		driver.findElement(By.xpath(POM.login_btn)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.developerEmail_txt)));
		pom.fillTxt(POM.developerEmail_txt, developerEmailId);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.onceAyear_RBtn)));
		pom.clickBtn(POM.onceAyear_RBtn);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.customerExp_chkBox)));
		pom.clickBtn(POM.customerExp_chkBox);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.discount_chkBox)));
		pom.clickBtn(POM.discount_chkBox);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.deliveryTime_chkBox)));
		pom.clickBtn(POM.deliveryTime_chkBox);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.payment_dd)));
		driver.findElement(By.xpath(POM.payment_dd)).click();
		
		Select paymentDD = new Select(driver.findElement(By.xpath(POM.payment_dd)));
		paymentDD.selectByVisibleText("Cash on delivery");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.purchase_chkBox)));
		driver.findElement(By.xpath(POM.purchase_chkBox)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.slider_lnk)));
		WebElement slider = driver.findElement(By.xpath(POM.slider_lnk));
		
		Actions move = new Actions(driver);
		Action action = move.dragAndDropBy(slider, 200, 0).build();
		action.perform();
		
		WebElement pointer = driver.findElement(By.xpath(POM.pointer_lnk));
		String percent = pointer.getAttribute("style");
		assertTrue(percent.contains("left: 88.8889%;"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.uploadImg_btn)));
		driver.findElement(By.xpath(POM.uploadImg_btn)).click();
		
		String path = System.getProperty("user.dir") + "\\jenkins.svg";
		
		StringSelection stringSelection = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		   
		Robot robot = new Robot();
		robot.setAutoDelay(250);
		robot.keyPress(KeyEvent.VK_CONTROL);
		GoToSleep(1000);
        robot.keyPress(KeyEvent.VK_V);
        GoToSleep(1000);
        robot.keyRelease(KeyEvent.VK_V);
        GoToSleep(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        GoToSleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        GoToSleep(1000);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        GoToSleep(2000);
        driver.switchTo().alert().accept();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POM.submit_btn)));
        driver.findElement(By.xpath(POM.submit_btn)).click();
        
        
        
        Boolean i = null;
        if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(POM.thankYou_txt))) != null) {
        	i = true;
        }
        assertTrue(i);
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
			driver.quit();
		}
//		driver.quit();
	}
}
