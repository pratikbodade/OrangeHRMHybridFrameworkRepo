package com.orangeHRM.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangeHRM.qa.base.Base;
import com.orangeHRM.qa.pages.IndexPage;
import com.orangeHRM.qa.pages.LoginPage;
import com.orangeHRM.qa.utils.Utilities;

public class LoginTest extends Base{

	public WebDriver driver;
	LoginPage loginPage;
	IndexPage indexPage;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		loginPage = new LoginPage(driver);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=0,dataProvider="validCredentialSupplier")
	public void verifyLoginWithValidCredentials(String id, String password) throws InterruptedException
	{
		indexPage = loginPage.login(id, password);
		Assert.assertTrue(indexPage.verifyDashboardTextOnIndexPage());
	}
	
	@Test(priority=1)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException
	{ 
		indexPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveInvalidCredentialsMessage().contains(dataProp.getProperty("idPasswordNoMatchWarning")),"Expected Warning message is not displayed");
		
		}
	@Test(priority=2)
	public void verifyLoginWithInvalidEmailAndValidPassword() throws InterruptedException
	{ 
		indexPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retrieveInvalidCredentialsMessage().contains(dataProp.getProperty("idPasswordNoMatchWarning")),"Expected Warning message is not displayed");
	
		}
	
	@Test(priority=3)
	public void verifyLoginWithValidEmailAndInvalidPassword() throws InterruptedException
	{ 
		indexPage = loginPage.login(prop.getProperty("validId"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveInvalidCredentialsMessage().contains(dataProp.getProperty("idPasswordNoMatchWarning")),"Expected Warning message is not displayed");
		
		}
	
	@Test(priority=4)
	public void verifyLoginWithoutProvidingCredentials() throws InterruptedException
	{ 
		loginPage.clickOnLoginSubmitButton();
		Assert.assertTrue(loginPage.retrivenoIdPasswordEnteredWarning().contains(dataProp.getProperty("noIdPasswordEnteredWarning")),"Expected Warning message is not displayed");
		 
		}
	
}
