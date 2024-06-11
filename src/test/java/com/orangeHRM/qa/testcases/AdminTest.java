package com.orangeHRM.qa.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRM.qa.base.Base;
import com.orangeHRM.qa.pages.AdminPage;
import com.orangeHRM.qa.pages.IndexPage;
import com.orangeHRM.qa.pages.LoginPage;
import com.orangeHRM.qa.utils.Utilities;
import com.orangeHRM.qa.utils.UtilityMethods;

public class AdminTest extends Base{
	
	public WebDriver driver;
	LoginPage loginPage;
	IndexPage indexPage;
	AdminPage adminPage;
		
		public AdminTest()
		{
			super();
		}

		@BeforeMethod
		public void setup() throws InterruptedException
		{
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		loginPage = new LoginPage(driver);
		indexPage = loginPage.login(prop.getProperty("validId"), prop.getProperty("validPassword"));
		adminPage = indexPage.searchAndOpenSection(dataProp.getProperty("validSection"));
		adminPage.checkVisibilityAndClickOnResetButton();
		}
		
		@AfterMethod
		public void tearDown()
		{
			driver.quit();
		}
	
	@Test(priority=0)
	public void searchAdminsWithoutEnteringAnyDetails()
	{
		adminPage.checkVisibilityAndClickOnSearchButton();
		Assert.assertTrue(adminPage.getRecordsText().contains(dataProp.getProperty("mulitpleRecordsExpectedMessage")),"No Records Found");
	}
	
	@Test(priority=1)
	public void searchAdminsWithUsername()
	{
		adminPage.enterUsername(dataProp.getProperty("validUsername"));
		adminPage.checkVisibilityAndClickOnSearchButton();
		Assert.assertTrue((adminPage.getRecordsText().contains(dataProp.getProperty("singleRecordExpectedMessage"))|| adminPage.getRecordsText().contains(dataProp.getProperty("mulitpleRecordsExpectedMessage"))),"No Records Found");
	}
	
	@Test(priority=2)
	public void searchAdminsWithUserRole() throws InterruptedException
	{
		adminPage.clickAndSelectUserRoleValue();
		adminPage.checkVisibilityAndClickOnSearchButton();
		Thread.sleep(3000);
		Assert.assertTrue(adminPage.getRecordsText().contains(dataProp.getProperty("mulitpleRecordsExpectedMessage")),"No Records Found");
	}
	

	@Test(priority=3)
	public void suggestionWithEmployeeNameVisible()
	{
		adminPage.enterEmployeeName(dataProp.getProperty("validEmployeeName"));
		Assert.assertTrue(adminPage.checkIfEmployeeNameSuggestionsVisible(),"No Search Found");
	}
	
	@Test(priority=4)
	public void verifyPresenceOfTableData()
	{
		adminPage.checkVisibilityAndClickOnSearchButton();
		System.out.println(adminPage.getTableData());
		Assert.assertTrue(adminPage.getTableData().contains(dataProp.getProperty("adminTableDataExpectedText")),"No Table Contents Found");
	}
}