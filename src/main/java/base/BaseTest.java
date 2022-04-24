package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	//public WebDriverWait wait;	
	public static Properties prop;
	public PageGenerator page;
	public static String workingDir = System.getProperty("user.dir");
	public String propFilePath = workingDir + "\\config.properties";
	

	// Constructor to initialize the properties file
	public BaseTest() {
		try {
			prop = new Properties();
			FileInputStream fp = new FileInputStream(propFilePath);
			prop.load(fp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@BeforeClass
	public void classlevelsetup(){
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		//create object of chrome options
        
        //add the headless argument
        options.addArguments("headless");
        
        //pass the options parameter in the Chrome driver declaration
 		driver = new ChromeDriver(options);
		// Maximize Window
		driver.manage().window().maximize();
		Reporter.log("=====after maximize=====", true);
		// Delete all cookies
		driver.manage().deleteAllCookies();
		// Page load timeout for 30 seconds
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// Implicit wait timeout for 20 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//Explicit wait time for 20 sec
		//wait = new WebDriverWait(driver, 20);
		
		// Navigate to URL provided in properties file
		driver.get(prop.getProperty("URL"));
		//driver.get("https://hub.docker.com/");
		
		Reporter.log("=====after get url=====", true);
		
		
	}

	@BeforeMethod(alwaysRun = true)
	public void setup(){


		// Instantiate the Page class
		page = new PageGenerator(driver);
		
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		driver.quit();
		Reporter.log("=====after quit browser=====", true);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
