package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	//define repository
	@FindBy(name="txtUsername")
	WebElement user;
	@FindBy(name="txtPassword")
	WebElement pass;
	@FindBy(name="Submit")
	WebElement Loginbtn;
	//write a method for login
	public void verifyLogin(String username,String password)throws Throwable
	{
		user.sendKeys(username);
		user.sendKeys(password);
		Loginbtn.click();
		Thread.sleep(5000);
	
		
	}
	}



