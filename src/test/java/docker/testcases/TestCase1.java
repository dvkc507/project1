package docker.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Page1;

@Listeners(utilities.TestListener.class)
public class TestCase1 extends BaseTest{
	
	@Test (description= "Test case1 to click on buttons", alwaysRun=true, enabled=true, invocationCount=1)
	public void TestCase1_verifyButton() throws Throwable{
		(page.GetInstance(Page1.class)).verifyClick();
	
		
	}
		

}
