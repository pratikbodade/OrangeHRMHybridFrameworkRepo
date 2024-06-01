package com.orangeHRM.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangeHRM.qa.utils.Utilities;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='username']")
	private WebElement loginId;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement loginPassword;
	
	@FindBy(xpath="//button[@type='submit' and text()=' Login ']")
	private WebElement loginSubmitButton;
	
	@FindBy(xpath="//p[text()='Invalid credentials']")
	private WebElement invalidCredentialsMessage;
	
	@FindBy(xpath="//form[@class='oxd-form']/div[1]//span")
	private WebElement noIdPasswordEnteredWarning;
	
	public void enterLoginId(String id)
	{
		loginId.sendKeys(id);	
	}
	
	public void enterLoginPassword(String password)
	{
		loginPassword.sendKeys(password);	
	}
	
	public IndexPage clickOnLoginSubmitButton()
	{
		loginSubmitButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		return new IndexPage(driver);
	}
	
	public String retrieveInvalidCredentialsMessage()
	{
		String invalidCredentialsText = invalidCredentialsMessage.getText();
		return invalidCredentialsText;
	}
	
	public String retrivenoIdPasswordEnteredWarning()
	{
		String noIdPasswordEnteredText= noIdPasswordEnteredWarning.getText();
		return noIdPasswordEnteredText;
	}
	
	public IndexPage login(String id, String password)
	{
		loginId.sendKeys(id);
		loginPassword.sendKeys(password);
		return clickOnLoginSubmitButton();
		
	}
	
	

}
