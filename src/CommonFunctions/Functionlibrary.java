package CommonFunctions;

import org.openqa.selenium.By;
import Constant.AppUtil;

public class Functionlibrary extends AppUtil{
	public static void  verifyLogin(String username, String password)
	{
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("ObjPassword"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("Objloginbtn"))).click();
	}
	
	}


