package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class LoginPage {

	private ActionDriver actionDriver;
	
	//Define locators using By class
	private By userNameField=By.name("username");
	private By passwordField=By.cssSelector("input[type='Password']");
	private By loginButton=By.xpath("//button[text()=' Login ']");
	private By errorMessage=By.xpath("//p[text()='Invalid credentials']");
	
	//Initialize the ActionDriver object by passing WebDriver instance in constructor
	/*public LoginPage(WebDriver driver) {
		this.actionDriver= new ActionDriver(driver);
	}*/
	
	//Calling actionDriver from the BaseClass as actionDriver instance is initiated in BaseClass 
	public LoginPage(WebDriver driver)
	{
		this.actionDriver=BaseClass.getActionDriver();
	}
	//Method to perform login
	public void login(String userName,String password) {
		actionDriver.enterText(userNameField, userName);
		actionDriver.enterText(passwordField, password);
		actionDriver.click(loginButton);
	}
	
	//Method to check if error message is displayed for invalid credentials
	public boolean isErrorMessageDisplayed() {
		return actionDriver.isDisplayed(errorMessage);
	}
	
	//Method to get text from error message
	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage);
	}
	
	//verify if error message is displayed correct or not
	public boolean verifyErrorMessage(String expectedError) {
		return actionDriver.compareText(errorMessage, expectedError);
	}
}
