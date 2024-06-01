package com.orangeHRM.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.orangeHRM.qa.utils.ExtentReporter;
import com.orangeHRM.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	
	@Override
	public void onStart(ITestContext context) {
		//System.out.println("Execution on Project Tests started");
		extentReport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
//		System.out.println(testName+" started executing");
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" started executing" );
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//System.out.println(testName+" got successfully executed");
		extentTest.log(Status.PASS,result.getName()+" got successfully executed" );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println(testName+" got failed");
		//System.out.println(result.getThrowable());
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromPath(Utilities.captureScreenshot(driver,result.getName()));
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//System.out.println(testName+" got skipped");
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,result.getName()+" got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		//System.out.println("Finished executing Project Tests");
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
