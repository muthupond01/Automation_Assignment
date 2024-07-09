package stepDefinitions;

import org.openqa.selenium.WebDriver;

import PageObjects.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import Utilities.ConfigReader;

import Utilities.WebDriverManagerTest;

public class HomePageStep   {
	
	
	@Before
    public void setup() {
       
    }
	private WebDriver driver;
	ConfigReader config = new ConfigReader();
	HomePage homePage=new HomePage(driver);
	

	public HomePageStep() {
		this.driver = WebDriverManagerTest.getDriver();
	}

	@Given("user launches the stc home page")
	public void user_launches_the_stc_home_page() {
		homePage.verifyHomePageDisplayed();
	}
	
	@Then("user should see the plan names")
	public void user_should_see_the_plan_names() {
		homePage.verifyPlanNamesInHome();

	}
	@Then("user should see the monthly prices {string}")
	public void user_should_see_the_monthly_prices(String countryName) {
		homePage.verifyMonthlyPrice(countryName);

	}
	@When("user switches to country {string}")
	public void user_switches_to_country(String countryName) {
		homePage.switchCountry(countryName);

	}
	
	

	// Add other step definitions as needed
}
