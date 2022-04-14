package Constant;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	public static WebDriver driver;
	public static Properties config;
	@BeforeTest
	public void setUp()throws Throwable
	{
		config=new Properties();
		config.load(new FileInputStream("C:\\Users\\Sravani\\workspace\\DDT_Framework\\PropertyFile\\Login.Properties"));
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "./CommonDrivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
		else if(config.getProperty("Browser").equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./CommonDrivers/geckodriver.exe");
			driver=new FirefoxDriver();		
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else
		{
		System.out.println("Browser value is Not Matching");	
		}
	}
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
	
		}
	

