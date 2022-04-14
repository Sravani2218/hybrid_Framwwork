package DriverFactory;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.Functionlibrary;
import Constant.AppUtil;
import Utilities.ExcelFileUtil;

public class DriverScript  extends AppUtil{
	ExtentReports reports;
	ExtentTest test;
	String inputpath="C:\\Users\\Sravani\\workspace\\DDT_Framework\\TestInput\\Abcd.xlsx";
	String outputpath="C:\\DDT_Framework\\TestOutput\\DDTResults.xlsx";
	
	@Test
	public void validatelogin()throws Throwable
	{
		//define path for genereting html report
		reports=new ExtentReports("./ExtentReports/DDT.html");
		//create reference object to cell xl methods
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		//count no of rows in login sheet
		int rc=xl.rowCount("Login");
		//count no of cells  in first row
		int cc=xl.cellCount("Login");
		Reporter.log("no of rows are::"+rc+"  "+"No of cells::"+cc+"\n\n\n",true);
		
		for (int i =1; i<=rc; i++)
		{
			driver.get(config.getProperty("Url"));
			driver.manage().window().maximize();
			Thread.sleep(1000);
					
			//read username and password cell data 
			String username=xl.getcellData("Login", i, 0);
			Thread.sleep(3000);
			String password=xl.getcellData("Login", i, 1);
			//cell login method
			Functionlibrary.verifyLogin(username, password);
			String exppected="dashbord";
			String actual=driver.getCurrentUrl();
			if(actual.contains(exppected))
			{
				//write as login success in results cell
				xl.setCellData("Login", i, 2, "Login is success", outputpath);
				//write as pass  in status cell
				xl.setCellData("Login", i, 3, "pass", outputpath);
				test.log(LogStatus.PASS, "Login success:::"+exppected+"     "+actual);
				Reporter.log("Login success::"+exppected+"    "+actual,true);
			}
			else
			{
				//take screen shoot
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./screenshot/iteration/"+i+"Loginpage.png"));
				//caputure error message
				String errormessage=driver.findElement(By.xpath(config.getProperty("objerrormessage"))).getText();
			    xl.setCellData("Login", i, 2, errormessage, outputpath);
				xl.setCellData("Login", i, 3, "Fail", outputpath);
				test.log(LogStatus.FAIL, errormessage+"    "+exppected+"     "+actual);
				Reporter.log("Login success::"+exppected+"    "+actual,true);	
			}
			reports.endTest(test);
			reports.flush();
			}
			}
			
		}

















