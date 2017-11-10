package com.experitest.auto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


public class AndroidDemoTest extends BaseTest {
	protected AndroidDriver<AndroidElement> driver = null;
	
	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='android'") String deviceQuery) throws Exception{
		init(deviceQuery);
		// Init application / device capabilities
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		dc.setCapability("testName", "AndroidDemoTest");
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
	}
	
	@Test
	
	public void test(){
		  driver.executeScript("client:client.deviceAction(\"Wake\")");
		  driver.executeScript("client:client.deviceAction(\"Unlock\")");
		  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		  driver.executeScript("client:client.setShowReport(\"false\");");
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Login']")));
		  driver.findElement(By.xpath("//*[@text='Login']")).click();
		  driver.executeScript("client:client.setShowReport(\"true\");");
		  new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Logout']")));
		  driver.findElement(By.xpath("//*[@text='Logout']")).click();
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
