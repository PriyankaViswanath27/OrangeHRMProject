package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {

	private ActionDriver actionDriver;

	// Define locators using By class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By orangeHRMLogo = By.xpath("//img[@alt='client brand banner']");

/*
 //Initialize the ActionDriver object by passing WebDriver instance in constructor

	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	} */
	
	//Calling actionDriver from the BaseClass as actionDriver instance is initiated in BaseClass 
		public HomePage(WebDriver driver)
		{
			this.actionDriver=BaseClass.getActionDriver();
		}

	// Method to verify if Admin tab is visible
	public boolean isAdminTabVisible() {
		return actionDriver.isDisplayed(adminTab);
	}

	// verify if orangeHRM logo is visible
	public boolean verifyOrangeHRMLogo() {
		return actionDriver.isDisplayed(orangeHRMLogo);
	}

//Method to perform logout operation
	public void logout() {
		actionDriver.click(userIDButton);
		actionDriver.click(logoutButton);
	}
}
