package com.test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.apidemo.app.screens.AppActivityScreen;
import com.test.apidemo.app.screens.AppMenuScreen;
import com.test.apidemo.app.screens.HomeScreen;
import com.test.apidemo.app.screens.ScreenOrientationScreen;
import com.test.utils.TestAppUtils;

public class AppiumApiDemoAppTests {
	private TestAppUtils testAppUtils;
	private AndroidDriver driver;
	private HomeScreen homeScreen;
	private AppMenuScreen appMenuPage;
	private AppActivityScreen appActivityPage;
	private ScreenOrientationScreen screenOrientationPage;

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws IOException {
		TestAppUtils.loadConfigProp("config_apidemo_test_app.properties");
		testAppUtils = new TestAppUtils();
		testAppUtils.setCapability(CapabilityType.BROWSER_NAME, "");
		testAppUtils.setCapability("platformVersion", "4.4.2");
		testAppUtils.setCapability("appium-version", "1.2.2");
		testAppUtils.setCapability("platformName", "Android");
		testAppUtils.setCapability("deviceName", "Android");
		testAppUtils.setCapability("automationName", "Appium");
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
	public void testAppActivity() {
		homeScreen = new HomeScreen(driver);
		appMenuPage = homeScreen.getAppMenuPage();
		appActivityPage = appMenuPage.getActivityPage();
		screenOrientationPage = appActivityPage.browseAppActivityScreen()
				.getScreenOrientationPage();
		Assert.assertEquals(
				screenOrientationPage.isItValidScreenOrientationPage(), true);
		screenOrientationPage.changeScreenOrientation("USER");
		Assert.assertEquals(screenOrientationPage.checkOrientationType(), true);
	}
}
