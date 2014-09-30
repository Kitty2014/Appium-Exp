package com.test;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.selendroid.app.screens.HomeScreen;
import com.test.selendroid.app.screens.UserRegistrationScreen;
import com.test.selendroid.app.screens.VerifyUserScreen;
import com.test.selendroid.app.screens.WebViewScreen;
import com.test.utils.TestAppUtils;

public class AppiumSelendroidAppTests {
	private TestAppUtils testAppUtils;
	private AppiumDriver driver;
	private UserRegistrationScreen userRegistrationScreen;
	private HomeScreen homeScreen;
	private VerifyUserScreen verifyUserScreen;
	private WebViewScreen webViewScreen;

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws IOException {
		TestAppUtils.loadConfigProp("config_selendroid_test_app.properties");
		testAppUtils = new TestAppUtils();
		testAppUtils.setCapability(CapabilityType.BROWSER_NAME, "");
		testAppUtils.setCapability("platformVersion", "4.4.2");
		testAppUtils.setCapability("appium-version", "1.2.2");
		testAppUtils.setCapability("platformName", "Android");
		testAppUtils.setCapability("deviceName", "Android");
		testAppUtils.setCapability("automationName", "Selendroid");
		testAppUtils.setCapability("app", new File(ClassLoader
				.getSystemResource(TestAppUtils.APPLICATION_NAME).getFile())
				.getAbsolutePath());
		testAppUtils.setCapability("newCommandTimeout", "3600");
		testAppUtils.setCapability("deviceReadyTimeout", "3600");
		testAppUtils.setCapability("appActivity", TestAppUtils.APP_ACTIVITY);
		testAppUtils.setCapability("appPackage", TestAppUtils.BASE_PKG);
		driver = testAppUtils.getDriver();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

	@Test(groups = { "Smoke" }, enabled = true)
	public void testRegisterUser() {
		homeScreen = new HomeScreen(driver);
		userRegistrationScreen = homeScreen.getUserRegistration();
		verifyUserScreen = userRegistrationScreen.fillUserName("TestUser")
				.fillEmailID("abc@abc.com").fillPassword("123456")
				.fillName("Test User").selectProgrammingLanguage("C++")
				.selectAddsRadio().verifyUser();
		verifyUserScreen.registerUser();
	}

	@Test(groups = { "Smoke" }, enabled = true)
	public void testWebView() {
		homeScreen = new HomeScreen(driver);
		webViewScreen = homeScreen.openWebView();
		webViewScreen.selectOptionFromList();
	}
}
