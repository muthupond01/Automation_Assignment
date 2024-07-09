package PageObjects;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ConfigReader;
import Utilities.WebDriverManagerTest;

public class HomePage{
	
	 Map<String, String> countryCodes = new HashMap<>();
	
//	Map<String, String> countryCodes = Map.of(
//			"ksa", "sa",
//			"bahrain", "bh",
//			"kuwait", "kw");

	private WebDriver driver;
	ConfigReader config = new ConfigReader();
	String config_planType = config.getConfigProperty("plantypes");
	String config_countryNames = config.getConfigProperty("plantypes");
	String[] planTypes = config_planType.split(",");
	
	
	private By headerChooseYourPlan = By.xpath("//h2[text()='Choose Your Plan']");
	private By logoSTC = By.id("jawwy-logo-web");
	private By lnkCountryName = By.id("country-name");
	private By titlePlan = By.id("name-lite");
	private By lblPlanTitle = By.xpath("//*[text()='Start your trial']/..//strong[@class='plan-title']");
	private By txtMonthlyPrice = By.xpath("//div[text()='Monthly price after free trial']");
	private By valueMonthlyPrice = By.xpath("//div[text()='Monthly price after free trial']/..//div[@class='price']");

	public HomePage(WebDriver driver) {
		this.driver = WebDriverManagerTest.getDriver();
	}

	

	public void verifyHomePageDisplayed() {
		driver.manage().window().maximize();
		String appURL = config.getConfigProperty("app.url");
		driver.get(appURL);
		System.out.println("Title is" + driver.getTitle());
		assertEquals(driver.getTitle(), "stc tv | Watch Movies, Series & Live TV - Enjoy Free Trial");
		assertTrue(driver.findElement(this.logoSTC).isDisplayed());
		assertTrue(driver.findElement(this.headerChooseYourPlan).isDisplayed());
		

	}


	public void switchCountry(String countryName) {
		 countryCodes.put("ksa", "sa");
		 countryCodes.put("bahrain", "bh");
		 countryCodes.put("kuwait", "kw");
		String selectedCountry=driver.findElement(lnkCountryName).getText().trim();
		if (!selectedCountry.equalsIgnoreCase(countryName)) {

			driver.findElement(lnkCountryName).click();
			if (countryCodes.containsKey(countryName)) {
				driver.findElement(By.id(countryCodes.get(countryName))).click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);			
			}
		}
	}

	public void verifyPlanNamesInHome() {

		List<WebElement> planTitle = driver.findElements(lblPlanTitle);
		assertEquals(planTitle.size(), this.planTypes.length);
		for (int i = 0; i < planTitle.size(); i++) {
			assertEquals(planTitle.get(i).getText(), this.planTypes[i].toUpperCase());

		}
	}

	public  void verifyMonthlyPrice(String expectedCountryName) {
		String monthlyPrice = "";
		String actualCountryName = driver.findElement(this.lnkCountryName).getText().trim();
		assertTrue(expectedCountryName.equalsIgnoreCase(actualCountryName));
		for (int i = 0; i < this.planTypes.length; i++) {
			monthlyPrice = driver.findElement(By.xpath(
					"//div[text()='Monthly price after free trial']/..//div[@id='currency-" + this.planTypes[i] + "']"))
					.getText();
			if (actualCountryName == "KSA") {
				assertTrue(checkPriceFormat(monthlyPrice, "SAR"));
			} else if (actualCountryName == "Bahrain") {
				assertTrue(checkPriceFormat(monthlyPrice, "BHD"));
			} else if (actualCountryName == "Kuwait") {
				assertTrue(checkPriceFormat(monthlyPrice, "KWD"));
			}
		}
	}

	public boolean checkPriceFormat(String actualText, String expectedCurrency) {
		String pattern = "\\d+(\\.\\d+)? " + expectedCurrency + "/month";
		Pattern compiledPattern = Pattern.compile(pattern);
		Matcher matcher = compiledPattern.matcher(actualText);
		return matcher.matches();

	}
	
}
