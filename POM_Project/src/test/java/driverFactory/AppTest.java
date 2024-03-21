package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.AddCustomer;
import config.AppUtils;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtils
{
   
	String inputpath ="./FileInput/CustomerData.xlsx";
	String outputpath = "./FileOutput/pomResults.xlsx";
	String sheet = "AddCustomer";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		report = new ExtentReports("./target/Reports/Customer.html");
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount(sheet);
		Reporter.log("no of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)	
		{
			logger = report.startTest("Validate Customer");
			String cname = xl.getCellData(sheet, i, 0);
			String Address = xl.getCellData(sheet, i, 1);
			String city = xl.getCellData(sheet, i, 2);
			String country = xl.getCellData(sheet, i, 3);
			String cperson = xl.getCellData(sheet, i, 4);
			String pnumber = xl.getCellData(sheet, i, 5);
			String Email = xl.getCellData(sheet, i, 6);
			String mnumber = xl.getCellData(sheet, i, 7);
			String notes = xl.getCellData(sheet, i, 8);
			AddCustomer cus = PageFactory.initElements(driver, AddCustomer.class);
			boolean res = cus.addCustomer(cname, Address, city, country, cperson, pnumber, Email, mnumber, notes);
			if(res)
			{
				xl.setCellData(sheet, i, 9, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Customer number matching");
			}
			else
			{
				xl.setCellData(sheet, i, 9, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Customer number Not matching");
			}
			report.endTest(logger);
			report.flush();
		}
				
	
	}
	
	
	
	
}
