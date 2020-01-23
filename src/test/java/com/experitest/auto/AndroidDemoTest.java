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
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.android.daggermultimodule_playground/.MainActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.daggermultimodule_playground");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
		dc.setCapability("appVersion", "1.0");
		dc.setCapability("testName", "AndroidDemoTest");
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
	}


	@Test
	public void test() throws InterruptedException{
		// Enter the test code
		    driver.findElement(By.xpath("//*[@text='TOUCH ME!']")).click();
	        driver.findElement(By.xpath("//*[@text='Coles Supermarkets']")).click();
	        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Coles Online']")));
	        driver.findElement(By.xpath("//*[@text='Shop online']")).click();
	        if(driver.findElements(By.xpath("//*[@text='Your account']")).size()==1) {
	        	driver.findElement(By.xpath("//*[@text='Your account']")).click();
	        	driver.findElement(By.xpath("//*[@text='Log out']")).click();
	        	Thread.sleep(1000);
	        }else{
	        	driver.findElement(By.xpath("//*[@text='Login or sign up']")).click();
	        }	        
	        driver.findElement(By.xpath("//*[@id='login-email-input']")).sendKeys("baijubly2gmail.com");
	        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login-password-input']")));
	        driver.findElement(By.xpath("//*[@id='login-password-input']")).sendKeys("test1234");
	        driver.findElement(By.xpath("//*[@id='keyboardView']")).click();

	        }


	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
