package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;

import base.BasePage;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends BasePage {

	public ExtentManager(WebDriver driver) {
		super(driver);
	}

	private static ExtentReports extent;

	public static String timestamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime());
	public static String workingDir = System.getProperty("user.dir");

	public static String reportPath = workingDir + "\\ExtentReports\\" + "DockerExtentReport_" + timestamp + ".html";

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports(reportPath, true);
		}
		return extent;
	}
}
