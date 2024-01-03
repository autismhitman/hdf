package driver;

import org.json.simple.JSONObject;

import com.aventstack.extentreports.ExtentTest;

import keywords.ApplicationKeywords;
import util.Xls_Reader;

public class DriverScript {
	
	ApplicationKeywords app;
	JSONObject testData;
	
	public DriverScript() {
		
		app= new ApplicationKeywords();
	 
		
	}
	
/*	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\resources\\testcases\\TestCase.xlsx");
		DriverScript ds = new DriverScript();
		ds.executeTest(xls, "Sheet1", "createPortfolio");
		
	}
	*/
	
	public void executeTest(Xls_Reader xls, String sheet, String testName) {
		int rows = xls.getRowCount(sheet);
		
		for ( int rNum =2 ; rNum<=rows ; rNum++) {
			
			String tcid = xls.getCellData(sheet, "TCID", rNum);
			if( tcid.equalsIgnoreCase(testName)) {
				
				String keyword = xls.getCellData(sheet, "Keyword", rNum);
				String object = xls.getCellData(sheet, "Object", rNum);
				String datakey = xls.getCellData(sheet, "Data", rNum);
				String storeVal = xls.getCellData(sheet, "StoreVal", rNum);
				String stopOnFailure = xls.getCellData(sheet, "StopOnFailure", rNum);
				System.out.println(tcid+"--"+keyword+"--"+object+"--"+datakey);
				String data  =(String) testData.get(datakey);
				
				if(keyword.equals("click"))  
				   app.click(object);
				else if	(keyword.equals("clear"))  
					app.clear(object);
				else if	(keyword.equals("type"))  
					app.type(object, data);
				else if	(keyword.equals("waitForPageLoad"))  
					app.waitForPageLoad();
				else if	(keyword.equals("validateSelectedValueInDropDown"))  
					app.validateSelectedValueInDropDown(object, data);
				else if	(keyword.equals("acceptAlert"))  
					app.acceptAlert(); 
				else if	(keyword.equals("validateSelectedValueNotInDropDown"))  
					app.validateSelectedValueNotInDropDown(object, data);
				else if	(keyword.equals("selectByVisibleText"))  
					app.selectByVisibleText(object, data);
			}
			
			
		}
		
		
	}
	
	public void setReport(ExtentTest test) {
		app.setReport(test);
		
	}

	
	public void defaultLogin(String browser) {
		app.openBrowser(browser);
		app.defaultLogin();
		 
		
	}

	public void quit() {
		if (app!= null)
            app.quit();
		
	}

	public void setTestData(JSONObject data) {
		 
		testData = data;
	}
	
	public void log(String logMessage) {
		app.log(logMessage);
		
	}
	
	
	

}
