package com.orangeHRM.qa.pages;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangeHRM.qa.utils.UtilityMethods;

public class IndexPage {
	
	WebDriver driver;
	
	public IndexPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//h6[text()='Dashboard']")
	private WebElement dashboardTextOnIndexPage;
	
	@FindBy(xpath="//div[@class='oxd-main-menu-search']/button/i[@class='oxd-icon bi-chevron-left']")
	private WebElement expandSearchSectionleftbtn;
	
	@FindBy(xpath="//div[@class='oxd-main-menu-search']/button/i[@class='oxd-icon bi-chevron-right']")
	private WebElement expandSearchSectionrightbtn;
	
	@FindBy(xpath="//div[@class='oxd-main-menu-search']/input[@placeholder='Search']")
	private WebElement enterSearchText;
	
	//@FindBy(xpath = "//span[text()='Admin']")
//	@FindBy(xpath="//ul[@class='oxd-main-menu']//span")
//	private WebElement searchedSectionResult;
	
	
	//Used to get String values and int count of results
	@FindBy(xpath="//ul[@class='oxd-main-menu']//span")
	public
	List<WebElement> searchedSectionResults;
	
	@FindBy(xpath="//ul[@class='oxd-main-menu']//span")
	public WebElement searchedSectionResult;
	
	
	public boolean verifyDashboardTextOnIndexPage()
	{
		Boolean dashboardTextVisibleOnIndexPage = dashboardTextOnIndexPage.isDisplayed();
		return dashboardTextVisibleOnIndexPage;
	}
	public boolean verifyExpandSearchSection()
	{
		Boolean expandSearchSection = expandSearchSectionleftbtn.isDisplayed();
		return expandSearchSection;
	}
	public void clickToExpandSearchSection()
	{
		expandSearchSectionrightbtn.click();
	}
	
	public void enterSearchText(String searchtext)
	{
		enterSearchText.sendKeys(searchtext);
	}
	 public String retrieveValidSearchSection()
	 {
		 String validSearchSectionText = searchedSectionResults.get(0).getText();
		 return validSearchSectionText;
	 }
	 
	 public int retrieveSearchSectionCount()
	 {
		 int searchSectioncount = searchedSectionResults.size();
		 return searchSectioncount;
	 }
	 
	 public void verifyandSearch(String search)
	 {
		 Boolean Expand = verifyExpandSearchSection();
			if(!Expand)
			{
				clickToExpandSearchSection();
			}
			enterSearchText(search);
	 }
	 
	 public Boolean visibilityOfSectionResult(String search)
	 {
		 verifyandSearch(search);
		 return UtilityMethods.checkIfElementDisplayed(searchedSectionResults.get(0));
	 }
	 
	 public AdminPage searchAndOpenSection(String search)
	 {
		 Boolean Expand = verifyExpandSearchSection();
			if(!Expand)
			{
				clickToExpandSearchSection();
			}
			enterSearchText(search);
			//UtilityMethods.clickOnElement(driver, searchedSectionResults.get(0));
			searchedSectionResults.get(0).click();
			AdminPage adminPage = new AdminPage(driver);
			return adminPage;
	 }
}
