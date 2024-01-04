package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;

public class ValidationKeywords extends GenericKeywords {
	
 

	public void validateTitle() {
      
	}

	public void validateText() {

	}

	public void validateElementPresent(String locator) {
     
	}
	
	public void validateSelectedValueInDropDown(String locatorkey, String portfolioName) {
		 
	     Select s = new Select(getElement(locatorkey));
	     String text = s.getFirstSelectedOption().getText();
	     if(!text.equals(portfolioName)) {
	    	 
	    	 reportFailure("Portfolio :" +  portfolioName+ " Not present in the dropdown" + locatorkey, true);
	     }
		 
	}
	
	public void validateSelectedValueNotInDropDown(String locatorkey, String portfolioName) {
		 
	     Select s = new Select(getElement(locatorkey));
	     String text = s.getFirstSelectedOption().getText();
	     if(text.equals(portfolioName)) {
	    	 
	    	 reportFailure("Portfolio :" +  portfolioName+ "present in the dropdown" + locatorkey, true);
	     }
		 
	}
	
	public void verifyStockPresent(String companyName) {

		int row = getRowNumWithCellData("table_stock_css", companyName);
		if(row==-1)
			reportFailure("Stock Not present" + companyName, true);
        log("Stock found in list" + companyName);
	 
	}
 
}
