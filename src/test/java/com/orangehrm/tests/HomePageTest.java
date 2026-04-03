package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class HomePageTest extends BaseClass {
	//creating private variables of LoginPage and HomePage classes
	private LoginPage loginPage;
	private HomePage homePage;
	
	//Before each Test method we are creating object for LoginPage and HomePage
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());// calling driver getter method getDriver() from BaseClass
		homePage = new HomePage(getDriver());// calling driver getter method getDriver() from BaseClass
	}
	
	@Test
	public void verifyOrangeHRMLogo() {
		loginPage.login("admin", "admin123");
		Assert.assertTrue(homePage.verifyOrangeHRMLogo(),"Test Failed:Logo is not visible");
	}
	@Test
	public void verifyAdminTabVisible() {
		loginPage.login("admin", "admin123");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Test Failed : Admin tab not visible");
	}
	
}
