package ff.tests;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import driver.DriverScript;
import keywords.ApplicationKeywords;
import testBase.BaseTest;
import util.Xls_Reader;

public class PortfolioManagement extends BaseTest {

	@Test
	public void createPortfolio(ITestContext con) {
		// Before Test ---app,strore in context, reports , test, initialize the data,
		// login , browser
		// Before method--- executed before each test annotations---extract the data ,
		// test, report , app
		// Test--test cases
		// after test-- generate reports and quiting app
        ds.log("Staring create Portfolio test");
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "\\src\\test\\resources\\testcases\\TestCase.xlsx");
		ds.executeTest(xls, "Sheet1", "createPortfolio");// pass the data
		  ds.log("Ending create Portfolio test");
	}

	@Test
	public void deletePortfolio(ITestContext con) {
		  ds.log("Staring delete Portfolio test");
		  Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\resources\\testcases\\TestCase.xlsx");
		  ds.executeTest(xls, "Sheet1", "deletePortfolio");//pass the data
		  ds.log("Ending delete Portfolio test");
	}

	@Test
	public void selectPortfolio(ITestContext con) {
		  ds.log("Staring select Portfolio test");
		  Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\resources\\testcases\\TestCase.xlsx");
		  ds.executeTest(xls, "Sheet1", "selectPortfolio");//pass the data
		  ds.log("Ending select Portfolio test");
	}

}
