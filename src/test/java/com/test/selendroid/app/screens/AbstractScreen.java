package com.test.selendroid.app.screens;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public abstract class AbstractScreen {

	public AndroidDriver driver;

	public AbstractScreen(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
