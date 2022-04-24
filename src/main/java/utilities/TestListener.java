package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;

public class TestListener extends BaseTest implements ITestListener {

	// Invoked each time before a test
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println(iTestResult.getName() + " test case started");
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
	}

	// Invoked on the success of any Test
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("The name of the testcase passed is :" + iTestResult.getName());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
		ExtentTestManager.endTest();
	}

	// Invoked on the failure of any Test
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("The name of the testcase failed is :" + iTestResult.getName());
		Object currentClass = iTestResult.getInstance();
		WebDriver driver = ((BaseTest) currentClass).getDriver();
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed", iTestResult.getThrowable().toString());
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed Screenshot",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		ExtentTestManager.endTest();
	}

	// Invoked on skipped of any Test
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("The name of the testcase Skipped is :" + iTestResult.getName());
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
		ExtentTestManager.endTest();
	}

	// below method executes each time Test fails but is within success percentage
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}

	// Invoked before starting all tests
	@Override
	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", this.driver);
	}

	// Invoked after ending all tests
	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentManager.getReporter().flush();
	}

}
