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

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSDemoTest extends BaseTest {
	protected IOSDriver<IOSElement> driver = null;

	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery) throws Exception {
		init(deviceQuery);
		// Init application / device capabilities
	
	    dc.setCapability(MobileCapabilityType.APP, "cloud:com.app.coles.ios.devdebug");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.app.coles.ios.devdebug");
		dc.setCapability("appVersion", "1");
		dc.setCapability("instrumentApp", true);
		dc.setCapability("testName", "IOSDemoTest");
		driver = new IOSDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
	}
	@Test
	public void test() throws InterruptedException {
		// Enter the test code
		    driver.findElement(By.xpath("//*[@text='Button']")).click();
	        driver.findElement(By.xpath("//*[@text='Back']")).click();
	        driver.findElement(By.xpath("//*[@text='Button']")).click();
	        driver.findElement(By.xpath("//*[@text='Button']")).click();
	        driver.findElement(By.xpath("//*[@text='First']")).click();
	        driver.findElement(By.xpath("//*[@text='Second']")).click();
	        driver.findElement(By.xpath("//*[@text='Third']")).click();
	        driver.findElement(By.xpath("//*[@text='Second']")).click();
	        driver.findElement(By.xpath("//*[@text='First']")).click();
	        driver.findElement(By.xpath("//*[@text='First']")).click();
	        driver.findElement(By.xpath("//*[@text='Button']")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
