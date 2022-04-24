package docker.testcases;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.DockerHubContainersPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utilities.TestListener.class)
public class Containers extends BaseTest {
	@Test(description = "Dockerhub Containers tab validation exercise", enabled=false)
	public void containers_Exercise() throws Throwable {
		DockerHubContainersPage dockerHubContainersPage = page.GetInstance(DockerHubContainersPage.class);
		dockerHubContainersPage.currentTabValidation();
		dockerHubContainersPage.imagesCheckboxValidation();
		dockerHubContainersPage.categoriesValidation();
		dockerHubContainersPage.checkVerifiedPublisherCheckbox();
		dockerHubContainersPage.checkAdditionalFilters();
		dockerHubContainersPage.validatingFilter();

	}
}