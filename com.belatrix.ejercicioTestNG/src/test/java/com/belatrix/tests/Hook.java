package com.belatrix.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.belatrix.base.Base;
import com.belatrix.pages.EbayDetalle;
import com.belatrix.pages.EbayPrincipal;

public class Hook extends Base {
	EbayPrincipal ebayPrincipal;
	EbayDetalle ebayDetalle;

	ExtentReports report;
	ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() {

		ExtentHtmlReporter extent = new ExtentHtmlReporter("./Reports/BelatrixReport.html");

		report = new ExtentReports();
		report.attachReporter(extent);

	}

	@BeforeMethod
	public void beforeMethod() {
		logger = report.createTest("Ejercicio Belatrix");
		ebayPrincipal = new EbayPrincipal(logger);
		ebayDetalle = new EbayDetalle(logger);
		ebayPrincipal.pagina("http://www.ebay.com/");
	}

	@AfterMethod
	public void afterMethod() {
		report.flush();
		cerrarNavegador();
	}

}
