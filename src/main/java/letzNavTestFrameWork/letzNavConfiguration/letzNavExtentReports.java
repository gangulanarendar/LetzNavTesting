package letzNavTestFrameWork.letzNavConfiguration;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class letzNavExtentReports {

	public static ExtentReports getInstance() {

		Date date = new Date();

		String originalDateFormat = date.toString();

		String modifiedDateFormat = originalDateFormat.replace(":", "_").replace(" ", "_");

		String FileName = modifiedDateFormat + ".html";

		String FilePath = System.getProperty("user.dir") + "//Reports//" + FileName;
		//String FilePath = letzNavPropertiesReader.getValue("reports") + FileName;
		ExtentReports report = new ExtentReports(FilePath, true, DisplayOrder.NEWEST_FIRST);

		report.loadConfig(new File(System.getProperty("user.dir") + "//src//main//java//letzNavTestFrameWork//letzNavConfiguration//ReportsConfig.xml"));

		report.addSystemInfo("LetzNav TestAutomation Framework", "Version1.0")
				.addSystemInfo("LetzNavTestEnvironment", "LetzNavQA").addSystemInfo("Test FrameWork By: ", "Ankur");

		return report;

	}
}
