package com.experitest.auto;

import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


public class AndroidDemoTest extends BaseTest {
	protected AndroidDriver<AndroidElement> driver = null;

	@BeforeTest
	@Parameters({"deviceQuery", "testname"})
	public void setUp(@Optional("@os='android'") String deviceQuery, String testname) throws Exception{
		init(deviceQuery);
		// Init application / device capabilities

		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		dc.setCapability("testName", testname);
//		dc.setCapability("fullReset", true);
//		dc.setCapability("instrumentApp", true);
		String val = System.getenv("BUILD_NUMBER");
		dc.setCapability("build", val);
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
	}


	@Test
	public void loginTest(){
	/*	driver.executeScript("client:client.deviceAction(\"Wake\")"); 
		driver.executeScript("client:client.deviceAction(\"Unlock\")");*/
		driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		driver.executeScript("client:client.setShowReport(\"false\");");
		driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Login']")));
		driver.findElement(By.xpath("//*[@text='Login']")).click();
		driver.executeScript("client:client.setShowReport(\"true\");");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Logout']")));
		driver.findElement(By.xpath("//*[@text='Logout']")).click();
	}


	//@Test
	public void makePaymentTest(){
		int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		if(randomNum==2){
			Assert.fail("Test Failed due to unknown reasons");
		}
		else{

//			driver.executeScript("client:client.deviceAction(\"Wake\")");
//			driver.executeScript("client:client.deviceAction(\"Unlock\")");
			driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
			driver.executeScript("client:client.setShowReport(\"false\");");
			driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Login']")));
			driver.findElement(By.xpath("//*[@text='Login']")).click();
			driver.executeScript("client:client.setShowReport(\"true\");");
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='makePaymentButton']")));
			driver.findElement(By.xpath("//*[@id='makePaymentButton']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='phoneTextField']")));
			driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("8888888888");
			driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("experitest");
			driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("1");
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='countryButton']")));
			driver.findElement(By.xpath("//*[@id='countryButton']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Greece']")));
			driver.findElement(By.xpath("//*[@text='Greece']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='sendPaymentButton']")));
			driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='alertTitle' and @text='EriBank']")));
			driver.findElement(By.xpath("//*[@id='button1' and @text='Yes']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Logout']")));
			driver.findElement(By.xpath("//*[@text='Logout']")).click(); 
		}
	}



	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
