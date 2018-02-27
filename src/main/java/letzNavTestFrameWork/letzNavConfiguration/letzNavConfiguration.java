package letzNavTestFrameWork.letzNavConfiguration;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import letzNavApplicationsToTest.clarityPPM;
import letzNavTestFrameWork.letzNavComponents.letzNavPlayer;

public class letzNavConfiguration {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	letzNavPlayer player;
	clarityPPM clarity;
	letzNavExcelReader data;
	String component="player";
	letzNavInstaller installer;
	
	public void letzNavConfig(WebDriver driver,ExtentReports report,ExtentTest test,letzNavPlayer player,letzNavExcelReader data ) {
		
	}
	
	
}
