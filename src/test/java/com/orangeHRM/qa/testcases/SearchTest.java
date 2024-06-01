package com.orangeHRM.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRM.qa.base.Base;
import com.orangeHRM.qa.pages.IndexPage;
import com.orangeHRM.qa.pages.LoginPage;
import com.orangeHRM.qa.utils.Utilities;

public class SearchTest extends Base{
	
public WebDriver driver;
LoginPage loginPage;
IndexPage indexPage;
	
	public SearchTest()
	{
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException
	{
	driver = initializeBrowserAndOpenApplication("chrome");
	loginPage = new LoginPage(driver);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	@Test(priority=0)
	public void verifySearchWithValidSection() 
	{
		indexPage = loginPage.login(prop.getProperty("validId"), prop.getProperty("validPassword"));
		indexPage.verifyandSearch(dataProp.getProperty("validSection"));
		Assert.assertEquals(indexPage.retrieveValidSearchSection(),dataProp.getProperty("validSection"),"Valid section Admin is not displayed in search results");
		
	}
	@Test(priority=1)
	public void verifySearchWithInvalidSection() 
	{
		indexPage = loginPage.login(prop.getProperty("validId"), prop.getProperty("validPassword"));
		indexPage.verifyandSearch(dataProp.getProperty("invalidSection"));
		Assert.assertEquals(indexPage.retrieveSearchSectionCount(),Integer.valueOf(dataProp.getProperty("noSectionCount")),"Showing section other than searched");
	}
	
	@Test(priority=2)
	public void verifySearchWithoutAnySection() 
	{
		indexPage = loginPage.login(prop.getProperty("validId"), prop.getProperty("validPassword"));
		Assert.assertEquals(indexPage.retrieveSearchSectionCount(),Integer.valueOf(dataProp.getProperty("allSectionCount")),"Searched a section");
	}
	
}
