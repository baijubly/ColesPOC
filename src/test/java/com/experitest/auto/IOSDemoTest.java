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
	@Parameters({"deviceQuery", "testname"})
	public void setUp(@Optional("@os='ios'") String deviceQuery, String testname) throws Exception {
		init(deviceQuery);
		// Init application / device capabilities
//		dc.setCapability(MobileCapabilityType.APP, "com.experitest.ExperiBank");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		dc.setCapability("testName", "IOSDemoTest");
		dc.setCapability("testName", testname);
		dc.setCapability("instrumentApp", true);
		driver = new IOSDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
	}

	@Test
	public void test() {
		 /* driver.executeScript("client:client.deviceAction(\"Wake\")");
		  driver.executeScript("client:client.deviceAction(\"Unlock\")");*/
		//  driver.context("NATIVE_APP_INSTRUMENTED");
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@accessibilityLabel='Username']")));
		  driver.findElement(By.xpath("//*[@accessibilityLabel='Username']")).sendKeys("company");
		  driver.findElement(By.xpath("//*[@accessibilityLabel='Password']")).sendKeys("company");
		  driver.findElement(By.xpath("//*[@accessibilityLabel='loginButton']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@accessibilityLabel='logoutButton']")));
		  driver.findElement(By.xpath("//*[@accessibilityLabel='logoutButton']")).click();
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
