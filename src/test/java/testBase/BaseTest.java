package testBase;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.App;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import driver.DriverScript;
import keywords.ApplicationKeywords;
import reports.ExtentManager;
import runner.DataUtil;

public class BaseTest {
	
	public DriverScript ds ;
	///public ApplicationKeywords app; //this will be required in driverscript
	public ExtentReports rep;
	public ExtentTest test;
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest(ITestContext con) throws NumberFormatException, FileNotFoundException, IOException, ParseException {
		System.out.println("---------B4 test--------");
		
		String dataFlag = con.getCurrentXmlTest().getParameter("dataflag");
		String datafilePath = con.getCurrentXmlTest().getParameter("datafilePath");
		String iteration = con.getCurrentXmlTest().getParameter("iteration");
		JSONObject data =new DataUtil().getTestData(datafilePath, dataFlag, Integer.parseInt(iteration));
		
		con.setAttribute("data", data);
		
		String runmode = (String)data.get("runmode");
		
		
		
		System.out.println(dataFlag);
		rep = ExtentManager.getReports();
		test = rep.createTest(con.getCurrentXmlTest().getName());
		test.log(Status.INFO, "Starting Test : "+ con.getCurrentXmlTest().getName() );
 		con.setAttribute("rep", rep);
		con.setAttribute("test", test);
		
		  if(!runmode.equals("Y")) {
	        	test.log(Status.SKIP, "Skipping as Data Runmode is N");
	        	throw new SkipException("Skipping as Data Runmode is N");
			}
	
		ds = new DriverScript();//1 app keyword for entire test@ test
		ds.setReport(test);
		ds.defaultLogin("chrome");
		//below is used to pass the json data
		ds.setTestData(data);
		ds.setTestContext(con);
		con.setAttribute("driver", ds);
		
	
		
	}     
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(ITestContext con) {
		System.out.println("---------B4 method--------");
		test = (ExtentTest) con.getAttribute("test");
		
		String criticalError = (String) con.getAttribute("CriticalFailure");
		
			if(criticalError!=null && criticalError.equals("Y")) {
				
				test.log(Status.SKIP, "Critical Error occured in previous test method");
				throw new SkipException("Critical Error occured in previous test method");
				
			}
		
		ds = ( DriverScript) con.getAttribute("driver");
		rep = (ExtentReports) con.getAttribute("rep");
		
	}
	
	
	
	@AfterTest(alwaysRun=true)
	public void afterTest(ITestContext con) {
		
		ds =( DriverScript) con.getAttribute("driver");
		
		if(ds!=null)
             ds.quit();
		
		rep = (ExtentReports)con.getAttribute("rep");
		
		if(rep!=null)
		   rep.flush();
		
	}
}
