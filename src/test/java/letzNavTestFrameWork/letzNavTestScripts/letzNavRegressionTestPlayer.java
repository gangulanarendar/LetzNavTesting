package letzNavTestFrameWork.letzNavTestScripts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import letzNavTestFrameWork.letzNavComponents.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import letzNavApplicationsToTest.*;
import letzNavTestFrameWork.letzNavConfiguration.letzNavBrowser;
import letzNavTestFrameWork.letzNavConfiguration.letzNavCsvData;
import letzNavTestFrameWork.letzNavConfiguration.letzNavExcelReader;
import letzNavTestFrameWork.letzNavConfiguration.letzNavExtentReports;
import letzNavTestFrameWork.letzNavConfiguration.letzNavInstaller;
import letzNavTestFrameWork.letzNavConfiguration.letzNavPropertiesReader;
import letzNavTestFrameWork.letzNavConfiguration.letzNavScreenShots;
import letzNavTestFrameWork.letzNavComponents.*;
import letzNavTestFrameWork.letzNavComponents.letzNavPlayer;

@SuppressWarnings("unused")
public class letzNavRegressionTestPlayer {
	Logger logs;
	public WebDriver driver;

	public String baseURL = letzNavPropertiesReader.getValue("url");
	public String clarityid = letzNavPropertiesReader.getValue("clarityid");
	public String claritypwd = letzNavPropertiesReader.getValue("claritypwd");
	ExtentReports report;
	ExtentTest test;
	letzNavPlayer player;
	clarityPPM clarity;
	letzNavExcelReader data;
	String component = "player";
	letzNavInstaller installer;
	String msg;

	// This meathod initializes browser and installs extentions to browser
	@BeforeClass
	public void openBrowserAndInstallExtensions() throws Exception {
		installer = new letzNavInstaller();
		installer.downloadExtension("player");
		logs = Logger.getLogger("devpinoyLogger");
		report = letzNavExtentReports.getInstance();
		driver = letzNavBrowser.startBrowser("chrome", letzNavPropertiesReader.getValue("url"), component);
		//player = new letzNavPlayer(driver);
		player = new letzNavPlayer(driver);
		clarity = new clarityPPM(driver);
		String testDataFile = System.getProperty("user.dir") + "\\TestData\\Book1.xlsx";
		letzNavExcelReader.setExcelFile(testDataFile, "EmployeeData");
	}
	
	// Data driven
	@DataProvider(name = "data")
	public Object[][] getData(Method method) {
		Object[][] testData = null;
		if (method.getName().equals("validateLauncherPosition")) {
			testData = letzNavExcelReader.getTestData("validateLauncherPosition");
			return testData;
		} else if (method.getName().equals("validateFlow")) {
			testData = letzNavExcelReader.getTestData("validateFlow");
			return testData;
		} else if(method.getName().equals("validateEditorFormPage")) {
			testData = letzNavExcelReader.getTestData("validateEditorFormPage");
			return testData;
		}
		
		else {
			return testData;
		}

	}

	// This test validates login to application
	@Test(groups = "clarity", description = "Clarity Login", priority = 1)
	public void validateLogin() {
		try {
		logs.debug("Test Clarity Login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test = report.startTest("Login Clarity PPM");
		test.log(LogStatus.INFO, "Validate clarity login");
		clarity.loginPage(clarityid, claritypwd);
		clarity.goToPortlets();
		}
		catch (Exception e) {
			msg=e.getMessage();
			test.log(LogStatus.ERROR, msg);
			e.printStackTrace();
		}
	}
	
	// This test validates the launcher position relative to webElement
	@Test(groups = "clarityPlayer", description = "Validate launcher Positining", priority = 2, dataProvider = "data")
	public void validateLauncherPosition(String path, String text) {
		try {

			logs.debug("Test launcher position");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			test = report.startTest("Validate launcher navigation");
			test.log(LogStatus.INFO, "Validate launcher position");
			boolean result = player.validateLauncherPosition(path, text, 0);
			Assert.assertTrue(result);
		} catch (Exception e) {
			msg=e.getMessage();
			test.log(LogStatus.ERROR, msg);
			e.printStackTrace();
		}
	}

	// This test vlaidates the proper workflow navigation
	@Test(groups = "clarityPlayer", description = "Validate launcher positining", priority = 3, dataProvider = "data")
	public void validateFlow(String flowPositionInPlayerList) {
		try {

			logs.debug("Test flow navigation");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			test = report.startTest("Validate flow navigation");
			test.log(LogStatus.INFO, "Validate flow navigation");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			player.openLetzNavPlayer();
			player.selectFlowToPlay(flowPositionInPlayerList);
			player.clickOnNextButton();
			player.clickOnNextButton();
			player.clickOnNextButton();
			player.clickOnNextButton();
			player.validateFeedBack();
			player.clickOnNextButton();
		} catch (Exception e) {
			msg=e.getMessage();
			test.log(LogStatus.ERROR, msg);
			e.printStackTrace();
		}
	}

	// This test validates the launcher navigation to other page
	@Test(groups = "clarityPlayer", description = "Validate launcher navigation", priority = 4)
	public void validateLauncherNavigation() {
		try {
			logs.debug("Test launcher navigation");
			letzNavPlayer player = new letzNavPlayer(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			test.log(LogStatus.INFO, "Validate launcher navigation");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			boolean result = player.validateLauncherNavigation("//h2[@title='Favorite Links']",
					"Favourite Links to New Page");
			Assert.assertTrue(result);
		} catch (Exception e) {
			msg=e.getMessage();
			test.log(LogStatus.ERROR, msg);
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		logs.debug("Test Execution Started");
	}

	// This method captures screenshot after testrun
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = letzNavScreenShots.takeScreenshot(driver, testResult.getName());
			String imagePath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Test Failed and Captured ScreenShot", imagePath);
			logs.debug("Screen Shot Captured on Failure");
		}
		String path = letzNavScreenShots.takeScreenshot(driver, testResult.getName());
		String imagePath = test.addScreenCapture(path);
		test.log(LogStatus.PASS, "Test Case Passed", imagePath);
		logs.debug("Test Case Finished");
		report.endTest(test);
		report.flush();
	}

	@AfterClass // This meathod will close browser
	public void afterClass() throws IOException {
		installer.clearExtensionResources();
		//driver.quit();

	}

}
