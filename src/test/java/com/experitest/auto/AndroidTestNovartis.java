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


public class AndroidTestNovartis extends BaseTest {
	protected AndroidDriver<AndroidElement> driver = null;

	@BeforeTest
	@Parameters({"deviceQuery", "testname"})
	public void setUp(@Optional("@os='android'") String deviceQuery, String testname) throws Exception{
		init(deviceQuery);
		// Init application / device capabilities

		dc.setCapability(MobileCapabilityType.APP, "cloud:com.orgname.NovartisApp/.NovartisApp");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.orgname.NovartisApp");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".NovartisApp");
		dc.setCapability("appVersion", "1.0.0");
	//	dc.setCapability("instrumentApp", true);
		dc.setCapability("testName", testname);
//		dc.setCapability("fullReset", true);
//		dc.setCapability("instrumentApp", true);
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
	}


	@Test
	public void test() {
		driver.findElement(By.xpath("//*[@text='Proceed']")).click();
		driver.findElement(By.xpath("//*[@text='Azure Login']")).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='i0116']")));
		driver.findElement(By.xpath("//*[@id='i0116']")).sendKeys("ramarao.reddy@gmail.com");
		driver.findElement(By.xpath("//*[@id='idSIButton9']")).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='i0118']")));		
		driver.findElement(By.xpath("//*[@id='i0118']")).sendKeys("Test@123user");
		driver.findElement(By.xpath("//*[@id='idSIButton9']")).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.EditText']")));		
		driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).sendKeys("Ireland");	
	}



	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
