package com.orangeHRM.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("OrangeHRM Test Automation Results");
		sparkReporter.config().setDocumentTitle("OHRM Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\orangeHRM\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	
		extentReport.setSystemInfo("Application URL : ", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name : ", prop.getProperty("browserName"));
		extentReport.setSystemInfo("Id : ", prop.getProperty("validId"));
		extentReport.setSystemInfo("Password : ", prop.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System : ", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username : ", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version : ", System.getProperty("java.version"));
		
		return extentReport;

	}
}
