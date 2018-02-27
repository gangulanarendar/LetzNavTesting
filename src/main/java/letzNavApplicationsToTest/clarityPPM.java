package letzNavApplicationsToTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class clarityPPM {

	public WebDriver driver;
	String clarityUser = "ppm_login_username";
	String clarityPwd = "ppm_login_password";
	String button = "ppm_login_button";
	String home = "//span[@id='ppm_nav_app']";
	String portlets = "//tr/td[1]/a[@title='Portlets']";
	

	public void loginPage(String id, String pwd) {
		driver.findElement(By.id(clarityUser)).sendKeys(id);
		driver.findElement(By.id(clarityPwd)).sendKeys(pwd);
		driver.findElement(By.id(button)).click();
	}

	public clarityPPM(WebDriver Idriver) {
		this.driver = Idriver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void goToPortlets() {
//		driver.findElement(By.xpath(home)).click();
//		driver.findElement(By.xpath(portlets)).click();
		driver.navigate().to("http://ppmdemo.letznav.com/niku/nu#action:npt.myPortlets");
	}

}
