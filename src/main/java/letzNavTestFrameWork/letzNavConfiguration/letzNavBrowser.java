package letzNavTestFrameWork.letzNavConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class letzNavBrowser {

	static WebDriver driver;
	//static String chromeDriverpath = letzNavPropertiesReader.getValue("chromedriver");
	public static WebDriver startBrowser(String browser, String url, String component) {
		ChromeOptions options;
		DesiredCapabilities capabilities;
		
	//	String chromeDriverpath = System.getProperty("user.dir") + "\\ExecutionBrowserDrivers\\chromedriver.exe";
		String selectBrowser = browser.toLowerCase();
		switch (selectBrowser) {

		case "chrome":

			// logs=Logger.getLogger(letzNavRegressionTest.class);

			//System.setProperty("webdriver.chrome.driver", chromeDriverpath);
		//	System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
//			
//				options = new ChromeOptions();
//			if (component.equalsIgnoreCase("player")) {
//				options.addExtensions(
//						new File(System.getProperty("user.dir") + "//ExecutionBrowserDrivers//letznav_player.crx"));
//				options.setBinary(new File("/opt/google/chrome"));
//			} else if (component.equalsIgnoreCase("editor")) {
//				options.addExtensions(
//						new File(System.getProperty("user.dir") + "//ExecutionBrowserDrivers//letznav_editor.crx"));
//				options.setBinary(new File("/opt/google/chrome"));
//			}
//		
			    options = new ChromeOptions();
			   // options.addArguments("–load-extension=” + pathToExtension);
//			    options.addExtensions(
//						new File(System.getProperty("user.dir") + "//ExecutionBrowserDrivers//letznav_editor.crx"));
//				options.setBinary(new File("/opt/google/chrome"));
			  //  options.addArguments("--load-extension=D:\\PC Plugins\\FF & IEv1.5.5.149\\download");
			    
			    options.addArguments("--browsertime.xvfb");
			    options.addArguments("--load-extension=/home/circleci/circleCiTesting/ExecutionBrowserDrivers/letznav_editor.crx");
			   // options.addExtensions(new File"/home/circleci/circleCiTesting/target/surefire-reports/naren_sc_shot_1.png");
		      //  options.addArguments("--headless", "--disable-gpu");
				driver = new ChromeDriver(options);
			
 		/*   capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
		*/	//driver.manage().window().maximize();

			break; 
		case "firefox":
			break;
		case "internetExplorer":
			break;
		case "safari":
			break;
		default:
		//	System.setProperty("webdriver.chrome.driver", chromeDriverpath);
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			options = new ChromeOptions();
			if (component.equalsIgnoreCase("player")) {
				options.addExtensions(
						new File(System.getProperty("user.dir") + "//ExecutionBrowserDrivers//letznav_player.crx"));
			} else if (component.equalsIgnoreCase("editor")) {
				options.addExtensions(
						new File(System.getProperty("user.dir") + "//ExecutionBrowserDrivers//letznav_editor.crx"));
			}
			//options.addArguments("--headless", "--disable-gpu");
			//driver = new ChromeDriver(options);
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			 // now copy the  screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File("/home/circleci/circleCiTesting/target/surefire-reports/naren_sc_shot_1.png"));
			}
			 
			catch (IOException e)
			 {
			  System.out.println(e.getMessage());
			 
			 }	
		
		return driver;

	}

}
