package driver;

import org.json.simple.JSONObject;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentTest;

import keywords.ApplicationKeywords;
import util.Xls_Reader;

public class DriverScript {
	
	ApplicationKeywords app;
	JSONObject testData;
	ITestContext con;
	
	public DriverScript() {
		
		app= new ApplicationKeywords();
	 
		
	}
	

	
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
				else if	(keyword.equals("findCurrentStockQuantity"))  
					app.findCurrentStockQuantity(data, storeVal);
				else if	(keyword.equals("clickEnterButton"))  
					app.clickEnterButton(object);
				else if	(keyword.equals("selectDateFromCalendar")) { 
					app.selectDateFromCalendar(data);
				System.out.println(con.getAttribute("quantityBeforeModification"));
				}
				else if	(keyword.equals("wait"))  
					app.wait(Integer.parseInt(datakey));
				else if	(keyword.equals("verifyStockPresent"))  
					app.verifyStockPresent(data);
				 
				
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



	public void setTestContext(ITestContext con) {
		// TODO Auto-generated method stub
		this.con = con;
		app.setTestContext(con);
	}
	
	
	

}
