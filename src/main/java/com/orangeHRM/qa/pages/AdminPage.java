package com.orangeHRM.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangeHRM.qa.utils.Utilities;
import com.orangeHRM.qa.utils.UtilityMethods;

public class AdminPage {


	WebDriver driver;
	
	public AdminPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//button[text()=' Reset ']")
	private WebElement resetBtn;
	
	@FindBy(xpath="//button[text()=' Search ']")
	private WebElement searchBtn;
	
	@FindBy(xpath="//form[@class='oxd-form']//div/input[1]")
	private WebElement usernameField;
	
	@FindBy(xpath="//div[@class='oxd-form-row']/div/div[2]//i")
	private WebElement userRoleField;
	
	@FindBy(xpath="//div[@role='listbox']//span[text()='ESS']")
	private WebElement userRoleValue;
	
	@FindBy(xpath="//span[@class='oxd-text oxd-text--span']")
	private WebElement recordsText;
	
	@FindBy(xpath="//form[@class='oxd-form']//div/input[@placeholder='Type for hints...']")
	private WebElement employeeNameField;
	
	@FindBy(xpath="//div[@class='oxd-autocomplete-wrapper']//div[@role='listbox' and contains(@class,'dropdown')]")
	private WebElement employeeNameSuggestions;
	
	@FindBy(xpath="//div[@class='oxd-table-header']/div//div[@role='columnheader']")
	private List<WebElement> columns;
	
	@FindBy(xpath="//div[@class='oxd-table-card']")
	private List <WebElement> rows;
	
	public void checkVisibilityAndClickOnResetButton()
	{
		if(UtilityMethods.checkIfElementDisplayed(searchBtn))
		{
		
		resetBtn.click();
		}
	}
	
	public void checkVisibilityAndClickOnSearchButton() 
	{
		if(UtilityMethods.checkIfElementDisplayed(searchBtn))
		{
			searchBtn.click();
		}
	}
	
	public String getRecordsText()
	{
		return recordsText.getText();
	}
	
	public void enterUsername(String uname)
	{
		usernameField.sendKeys(uname);
	}
	
	public void clickAndSelectUserRoleValue()
	{
		userRoleField.click();
		userRoleValue.click();
	}
	
	public void enterEmployeeName(String ename)
	{
		employeeNameField.sendKeys(ename);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
	}
	
	public Boolean checkIfEmployeeNameSuggestionsVisible()
	{
		return UtilityMethods.checkIfElementDisplayed(employeeNameSuggestions);
	}
	
	public String getTableData()
	{
		return UtilityMethods.returnAdminTableData(driver,columns,rows.size());
		
	}
}
