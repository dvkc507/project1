package docker.testcases;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;
import base.BaseTest;

public class RunTestNG_docker extends BaseTest {

	public static void main(String[] args) {

		// Create Object of TestNG class
		TestNG runner = new TestNG();

		// Create a list of Strings to store XML location
		List<String> suitefiles = new ArrayList<String>();

		// Add XML file that needs to be executed
		suitefiles.add(System.getProperty("user.dir") + "\\testng.xml");

		// set the XML file for the execution
		runner.setTestSuites(suitefiles);

		// Finally execute the runner using run method
		runner.run();
	}
}
