package letzNavApplicationsToTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class clarityPPM {

	public WebDriver driver;
	String clarityUser = "ppm_login_username";
	String clarityPwd = "ppm_login_password";
	String button = "ppm_login_button";
	String home = "//span[@id='ppm_nav_app']";
	String portlets = "//tr/td[1]/a[@title='Portlets']";
	

	public void loginPage(String id, String pwd) {
		
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			 // now copy the  screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File("/home/circleci/circleCiTesting/target/surefire-reports/loginPage_clarity_login.png"));
			}
			 
			catch (IOException e)
			 {
			  System.out.println(e.getMessage());
			 
			 }	
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
