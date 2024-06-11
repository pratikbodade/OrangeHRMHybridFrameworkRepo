package com.orangeHRM.qa.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityMethods {
	
	public static WebElement findElement(WebDriver driver, By selector, Duration timeOutInSeconds) 
	{
         WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
         wait.until(ExpectedConditions.presenceOfElementLocated(selector));
         return findElement(driver, selector, timeOutInSeconds);
	}
	
	public static Boolean checkIfElementEnabled(WebElement element)
	{
	return element.isEnabled();
	}
	
	public static Boolean checkIfElementDisplayed(WebElement element)
	{
	return element.isDisplayed();
	}
	
	public static Boolean checkIfElementSelected(WebElement element)
	{
	return element.isSelected();
	}
	
	public static void selectFromDropDownByValue(WebElement element, String selection)
	{
		Select select = new Select(element);
		select.selectByValue(selection);
	}
	
	public static void selectFromDropDownByIndex(WebElement element, int selection)
	{
		Select select = new Select(element);
		select.selectByIndex(selection);
	}
	
	public static void selectFromDropDownByVisibleText(WebElement element, String selection)
	{
		Select select = new Select(element);
		select.selectByVisibleText(selection);
	}
	
	public static void deselectFromDropDownByValue(WebElement element, String selection)
	{
		Select select = new Select(element);
		select.deselectByValue(selection);
	}
	
	public void deselectFromDropDownByIndex(WebElement element, int selection)
	{
		Select select = new Select(element);
		select.deselectByIndex(selection);
	}
	
	public static void deselectFromDropDownByVisibleText(WebElement element, String selection)
	{
		Select select = new Select(element);
		select.deselectByVisibleText(selection);
	}
	
	public static List<WebElement> alldropdownvalues(WebElement element)
	{
		Select select = new Select(element);
		List<WebElement> elements =  select.getOptions();
		return elements;
	}
	
	public static Set<String> getAllWindows(WebDriver driver) 
	{
        Set<String> allWindowHandles = driver.getWindowHandles();
        return allWindowHandles;
	}
	
	public static void switchToChildWindows(WebDriver driver, String id) 
	{
		String parentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles)
        {
        	if (!handle.equals(parentWindowHandle)&& handle.equals(id)) 
        	{
                driver.switchTo().window(handle);
        	}
        }
	}
	
	public static void switchToParentWindow(WebDriver driver) 
	{
		String parentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles)
        {
        	if (handle.equals(parentWindowHandle)) 
        	{
                driver.switchTo().window(handle);
        	}
        }
	}
	
	public static void switchToWindowByNumber(WebDriver driver, int num) {
        Set<String>it=driver.getWindowHandles();
        Iterator<String>id=it.iterator();
        ArrayList<String>a=new ArrayList<String>();
        while(id.hasNext()) {
        a.add(id.next());
        }
        driver.switchTo().window(a.get(num));
    }
	
	public static void sendTextToElement(WebDriver driver,WebElement element,  String text)
	{
		element.sendKeys(text);
	}
	
	public static String getTextFromElement(WebDriver driver,WebElement element)
	{
		return element.getText();
	}
	
	public static void clickOnElement(WebDriver driver,WebElement element)
	{
		element.click();
	}
	
	public static String gettitleofpage(WebDriver driver) 
	{
        String title=driver.getTitle();
        return title;
	}
	
	public static List<WebElement> getallelements(WebDriver driver,String locatorValue)
	{
		List<WebElement> elements = null;
		if(!(driver.findElements(By.xpath(locatorValue))==null))
		{
			elements = driver.findElements(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.id(locatorValue))==null))
		{
			elements = driver.findElements(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.className(locatorValue))==null))
		{
			elements = driver.findElements(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.cssSelector(locatorValue))==null))
		{
			elements = driver.findElements(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.linkText(locatorValue))==null))
		{
			elements = driver.findElements(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.name(locatorValue))==null))
		{
			elements = driver.findElements(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.partialLinkText(locatorValue))==null))
		{
			elements = driver.findElements(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.tagName(locatorValue))==null))
		{
			elements = driver.findElements(By.xpath(locatorValue));
		}
				return elements;
				
	}
	
	public static void clickAnElementFromAllElements(WebDriver driver,String locatorValue, String expectedTextInElement)
	{
		List<WebElement> elements= getallelements(driver, locatorValue);
		for(WebElement element : elements) {
			if(element.getText().contains(expectedTextInElement))
			{
				element.click();
			}
		}
	}
	public static void sendTextToElementFromAllElements(WebDriver driver,String locatorValue, String expectedTextInElement, String text)
	{
		List<WebElement> elements= getallelements(driver, locatorValue);
		for(WebElement element : elements) {
			if(element.getText().contains(expectedTextInElement))
			{
				element.sendKeys(text);
			}
		}
	}
	
	public static WebElement getelement(WebDriver driver,String locatorValue)
	{
		WebElement element = null;
		if(!(driver.findElement(By.xpath(locatorValue))==null))
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.id(locatorValue))==null))
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.className(locatorValue))==null))
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.cssSelector(locatorValue))==null))
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.linkText(locatorValue))==null))
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.name(locatorValue))==null))
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.partialLinkText(locatorValue))==null))
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
		else if(!(driver.findElements(By.tagName(locatorValue))==null))
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
				return element;
				
	}
	
	public static void displayAllValuesFromTable(WebElement tableElement)
	{
		
		List<WebElement> rowsList = tableElement.findElements(By.tagName("tr"));
        List<WebElement> columnsList = null;

       for (WebElement row : rowsList) 
       {
               System.out.println();
               columnsList = row.findElements(By.tagName("td"));
                for (WebElement column : columnsList)
                {
                       System.out.print(column.getText() + ", ");
                }
       }
	}
	
	public static String returnAdminTableData(WebDriver driver,List<WebElement> Headers,int rows)
	{

		List<WebElement> columnHeaders = Headers;
		List<WebElement> rowsValues= null;
		String result = "";
		for(int i=1;i<=rows;i++)
		{
			result += i+") ";
			 rowsValues =  driver.findElements(By.xpath("//div[@class='oxd-table-card']["+i+"]/div//div[@class='oxd-table-cell oxd-padding-cell']/div"));;
		for (int j=1;j<rowsValues.size()-1;j++) 
	       {
	                       result += columnHeaders.get(j).getText() +" = "+rowsValues.get(j).getText()+ ", ";
	       }
		result+="\n";
		}
		return result;
	}
	
	
	
	
	
}
