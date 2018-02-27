package letzNavTestFrameWork.letzNavConfiguration;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class letzNavScreenShots {

	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {

		fileName = fileName + ".png";
		String directory = System.getProperty("user.dir") + "//Reports//ScreenShots//";
		//String directory =  letzNavPropertiesReader.getValue("screenshots");
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		String destination = directory + fileName;
		return destination;
	}

}
