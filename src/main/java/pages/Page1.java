package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import base.PageGenerator;

public class Page1 extends BasePage{

	public Page1(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	@FindBy (xpath="//div[@class='checkbox store styles__input___k3LJ2']")
	public WebElement VerifiedCheckbox;
	@FindBy (xpath="//div[@class='checkbox official styles__input___k3LJ2']")
	public WebElement Officialimage;
	@FindBy(xpath="//a[text()='Explore']")
	public WebElement ExploreTab;
	@FindBy(xpath="//header/a[@id='homeButton']/*[1]")
	public WebElement DockethubHome;
	@FindBy(xpath="//div[text()='Build and Ship any Application Anywhere']")
	public WebElement HomePageGetText;
	@FindBy(xpath="//a[text()='Pricing']")
	WebElement pricingTab;
	@FindBy(xpath="//div[@class='autocompleteSearchWrapper styles__searchBarWrapper___2kZcu']")
	WebElement SearchBox;
	@FindBy(xpath="//div[@class='checkbox amd64 styles__input___k3LJ2']")
	WebElement x86;
	
	
	public<T> void wait(T element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		System.out.println("Wait:"+wait);
		wait.until(ExpectedConditions.visibilityOf((WebElement)element));
	}
	

	public void verifyClick(){
		System.out.println("Inside verify click method");
		System.out.println("driver: "+driver);
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='checkbox store styles__input___k3LJ2']")));
		click(VerifiedCheckbox,"VerifiedCheckbox");
		click(Officialimage,"Officialimage");
		click(DockethubHome,"DockethubHome");
		getText(HomePageGetText, "HomePageGetText");
		try{
			Thread.sleep(4000);
		}
		catch(Exception e)
		{
			System.out.println("in side catch");
			e.printStackTrace();
		}
		click(ExploreTab, "ExploreTab");
		switchWindows(pricingTab,"pricingTab");
		click(ExploreTab, "ExploreTab");
		actionClick(VerifiedCheckbox, "VerifiedCheckbox");
		actionClick(Officialimage, "Officialimage");
		jsClick(SearchBox);
		SendText(SearchBox, "ubuntu");
		driver.navigate().refresh();
		scrollToElement(x86, "x86");
		try{
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			System.out.println("in side catch");
			e.printStackTrace();
		}
		
		
		
		//Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='dbutton dtab styles__tab___2xYWx styles__addBorder___2DCcx styles__productTab___2YNtd styles__selectedClass___x8pC0 styles__button___349c4 styles__new___28c7_']/following-sibling::button")));
		//click(ContainersButton, "Container button");
	}
}
