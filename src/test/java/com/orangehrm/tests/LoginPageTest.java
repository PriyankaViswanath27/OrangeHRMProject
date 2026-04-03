package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class LoginPageTest extends BaseClass {
 
	private LoginPage loginPage;
	private HomePage homePage;
	
	//Before each Test method we are creating object for LoginPage and HomePage
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());// calling driver getter method getDriver() from BaseClass
		homePage = new HomePage(getDriver());// calling driver getter method getDriver() from BaseClass
	}
	@Test
	public void verifyValidLoginTest() {
		loginPage.login("admin","admin123");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin tab should be visible after successful login");
	    homePage.logout();
	    staticWait(2);
	}
	@Test
	public void invalidLoginTest() {
		String expectedErrorMessage = "Invalid credentials";
		loginPage.login("admin12","admin");//passing invalid username and password
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test Failed: Inavlid error message");
		
	}
}
