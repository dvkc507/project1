package base;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentTestManager;

public class BasePage extends PageGenerator {

	public BasePage(WebDriver driver) {
		super(driver);
	}
	
	protected WebDriverWait wait = new WebDriverWait(this.driver,20);
	Actions action = new Actions(this.driver);
	JavascriptExecutor js = (JavascriptExecutor) this.driver;
	// Invoked when Test step is passed
	public void passLog(String log) {
		ExtentTestManager.getTest().log(LogStatus.PASS, log);
	}

	// Invoked when Test step is passed
	public boolean failLog(String log) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, log);
		throw new RuntimeException();
	}

	// Invoked to click on web element and log the message
	public void click(WebElement elementAttr, String log) {
		try {
			System.out.println("Inside click method");
			//System.out.println("wait:"+wait);
			boolean display = elementAttr.isDisplayed();
			boolean enabled = elementAttr.isEnabled();
			if(display||enabled)
			{
				System.out.println("Inside if block");
				wait.until(ExpectedConditions.visibilityOf(elementAttr));
				elementAttr.click();
			}
			else
			{
				System.out.println("Inside else block");
				wait.until(ExpectedConditions.elementToBeClickable(elementAttr));
				elementAttr.click();
			}
			Reporter.log("successfully clicked"+log);
			passLog("Successfully clicked on " + log);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	public void getText(WebElement elementAttr,String log){
		
	boolean display= elementAttr.isDisplayed();
	if(display)
	{
		String content = elementAttr.getText();
		System.out.println("Home page content:"+content);
	}
	else
	{
		wait.until(ExpectedConditions.visibilityOf(elementAttr));
		String content = elementAttr.getText();
		System.out.println("waited for:"+ elementAttr);
		System.out.println("Home page content:"+content);
	}
	}
	public void switchWindows(WebElement elementAttr,String log)
	{
		String ParentWindow = driver.getWindowHandle();
		click(elementAttr,log);
		Set<String> windows= driver.getWindowHandles();	
		Iterator<String> itr = windows.iterator();
		while(itr.hasNext())
		{
			String childWindow = itr.next();
			if(!(ParentWindow.equals(childWindow)))
			{
			System.out.println("Current window:"+childWindow);	
			driver.switchTo().window(childWindow);
			System.out.println("current page title: "+driver.getTitle());
			}
		}
		driver.switchTo().window(ParentWindow);
		System.out.println("current page title: "+driver.getTitle());
		
	}
	public void actionClick(WebElement elementAttr,String log){
		
		try{
		wait.until(ExpectedConditions.visibilityOf(elementAttr));
		action.moveToElement(elementAttr);
		action.click().perform();
		passLog("Successfully clicked on " + log);
		}
		catch(Throwable t)
		{
			t.getStackTrace();
			failLog("Not able to clicked on " + log);
		}

		
	}
	
	public void jsClick(WebElement elementAttr){
		try{
		wait.until(ExpectedConditions.visibilityOf(elementAttr));
		js.executeScript("arguments[0].click()",elementAttr);
		//js.executeScript("alert('Welcome to jsClick')");
		}
		catch(Throwable t)
		{
			t.getStackTrace();
			failLog("Not able to clicked on " + elementAttr);
		}
		
	}
	
	public void SendText(WebElement elementAtt,String log){
		
		js.executeScript("arguments[0].value='"+log+"';",elementAtt);
	}

	// Invoked to check if actual element is equal to expected element
	public <T> void assertEquals(T actual, T expected, String failLog) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (AssertionError e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, failLog);
			e.printStackTrace();
			throw e;
		}
	}
	
	public void scrollToElement(WebElement elementAtt,String log){
		try{
			wait.until(ExpectedConditions.visibilityOf(elementAtt));
			js.executeScript("arguments[0].scrollIntoView();", elementAtt);
			passLog("Successfully scrolled to:  " + log);
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}

}
