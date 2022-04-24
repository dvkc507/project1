package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import base.BasePage;

public class DockerHubContainersPage extends BasePage {

	public DockerHubContainersPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[contains(@class,'selectedClass')]")
	public WebElement selectedTab;

	@FindBy(xpath = "//div[@id='imageFilterList']//input[@type='checkbox']")
	public List<WebElement> imagesCheckboxes;

	@FindBy(xpath = "//div[@id='imageFilterList']//label")
	public List<WebElement> imageslabels;

	@FindBy(xpath = "//input[@value='base']")
	public WebElement baseImagesCheckbox;

	@FindBy(xpath = "//input[@value='database']")
	public WebElement databasesCheckbox;

	@FindBy(xpath = "//div[@id='categoriesFilterList']//input[@type='checkbox']")
	public List<WebElement> categoryCheckboxes;

	@FindBy(xpath = "//div[@id='categoriesFilterList']//label")
	public List<WebElement> categoryLabels;

	@FindBy(xpath = "//input[@value='store']")
	public WebElement verifiedPublisherCheckbox;

	@FindBy(xpath = "//div[@class='dChip styles__chip___3ZtYi styles__clickable___1fzXr styles__withIcon___2uxjQ styles__closeFilter___1OZwH']")
	public List<WebElement> filters;

	// After navigating the URL, below method validates whether user landed on
	// Container tab or not.
	public void currentTabValidation() {
		assertEquals(selectedTab.getText(), "Containers", "Containers tab is not the default tab");
		passLog("Verified 'Containers' tab is the default tab");
	}

	// Below method validates number of checkboxes and labels of checkboxes present
	// under 'Image' filter
	public void imagesCheckboxValidation() {
		ArrayList<String> requiredLabelsText = new ArrayList<String>(
				Arrays.asList("Verified Publisher", "Official Images"));
		assertEquals(imagesCheckboxes.size(), requiredLabelsText.size(),
				"'Images' filter does not have " + requiredLabelsText.size() + " checkboxes");
		passLog("Verified 'Images' filter have " + requiredLabelsText.size() + " checkboxes");
		ArrayList<String> labelText = new ArrayList<String>();
		for (WebElement label : imageslabels) {
			labelText.add(label.getText().split("\n")[0]);
		}
		String[] result = checkElements(requiredLabelsText, labelText);
		String present = result[0];
		String notPresent = result[1];
		if (!present.equals("")) {
			passLog("Verified '" + present + "' checkboxes in 'Image' filter");
		}
		assertEquals(notPresent.equals(""), true, "'Image' filter doesn't have '" + notPresent + "' check boxes");
	}

	// Below method validates whether Analytics, Base Images, Databases and Storage
	// checkboxes present under Categories filter or not.
	public void categoriesValidation() {
		ArrayList<String> requiredLabelsText = new ArrayList<String>(
				Arrays.asList("Analytics", "Base Images", "Databases", "Storage"));
		assertEquals(categoryCheckboxes.size() >= requiredLabelsText.size(), true,
				"'Category' filter list doesn't have " + requiredLabelsText.size() + " checkboxes");
		ArrayList<String> labelText = new ArrayList<String>();
		for (WebElement we : categoryLabels) {
			labelText.add(we.getText());
		}
		String[] result = checkElements(requiredLabelsText, labelText);
		String present = result[0];
		String notPresent = result[1];
		if (!present.equals("")) {
			passLog("Verified '" + present + "' checkboxes in 'Category' filter");
		}
		assertEquals(notPresent.equals(""), true, "'Categoty' filter doesn't have '" + notPresent + "' check boxes");
	}

	// Below method checks Verified publisher checkbox and validates with the
	// filters displayed at the top
	public void checkVerifiedPublisherCheckbox() throws Throwable {
		click(verifiedPublisherCheckbox, "'Verified Publisher' Checkbox");
		String requiredText = "Publisher Content";
		boolean containsRequiredText = false;
		for (WebElement we : filters) {
			if (we.getText().equals(requiredText)) {
				containsRequiredText = true;
				break;
			}
		}
		assertEquals(containsRequiredText, true, "Publisher Content' filter is not displayed at the top");
		passLog("Verified 'Publisher Content' filter displayed at the top");

	}

	// Below method checks Base Images, Databases checkboxes and validates with the
	// filters displayed at the top
	public void checkAdditionalFilters() throws Throwable {
		click(baseImagesCheckbox, "'Base Images' Checkbox");
		click(databasesCheckbox, "'Databases' Checkbox");
		String baseImagesText = "Base Images";
		String DatabasesText = "Databases";
		boolean containsBaseImagesText = false, containsDatabasesText = false;
		for (WebElement we : filters) {
			String text = we.getText();
			if (text.equals(baseImagesText)) {
				containsBaseImagesText = true;
			} else if (text.equals(DatabasesText)) {
				containsDatabasesText = true;
			}
		}
		assertEquals(containsBaseImagesText && containsDatabasesText, true,
				"Additional Filters were not displayed at the top");
		passLog("Additional Filters are displayed at the top");

	}

	// Below method clicks on close icon of Base Image filter on the top and
	// validates the same in left filter pane
	public void validatingFilter() throws Throwable {
		String baseImagesText = "Base Images";
		for (WebElement webElement : filters) {
			if (webElement.getText().equals(baseImagesText)) {
				click(webElement, "close icon of 'Base Images' filter");
				if (baseImagesCheckbox.getAttribute("checked") == null)
					passLog("Validated checkboxes on the left side of the pane with the corresponding top filters");
				else
					failLog("Checkboxes are not unchecked when corresponding top filters are closed");
				break;
			}
		}
	}

	// Below method takes 2 lists i.e, "requiredText" and "givenText" as input
	// returns 2 Strings in String array.
	// "presentText" list stores the strings present in both "requiredText" and
	// "givenText" lists
	// "notPresentText" list stores the strings which are present in "requiredText"
	// list but not in "givenText" list
	private String[] checkElements(List<String> requiredText, List<String> givenText) {
		List<String> presentText = new ArrayList<String>();
		List<String> notPresentText = new ArrayList<String>();
		for (String str : requiredText) {
			if (givenText.contains(str))
				presentText.add(str);
			else
				notPresentText.add(str);
		}
		return new String[] { String.join(",", presentText), String.join(",", notPresentText) };
	}
}
