package letzNavApplicationsToTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class workDay {
	
	public WebDriver driver;
	public String loginWithIdPwd = "//div[@class='GNEOE0TCGK']/div[text()='Login with Workday ID/Password']";
	public String userId = "//div[text()='user name']/../input";
	public String userPassword = "//div[text()='password']/../input";
	public String signInButton = "//button[text()='Sign In']";
	
	public workDay(WebDriver Idriver) {
		this.driver = Idriver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void login(String loginId,String password) {
		driver.findElement(By.xpath(loginWithIdPwd)).click();
		driver.findElement(By.xpath(userId)).sendKeys(loginId);
		driver.findElement(By.xpath(userPassword)).sendKeys(password);
		driver.findElement(By.xpath(signInButton)).click();
	}
	
	
 
}
