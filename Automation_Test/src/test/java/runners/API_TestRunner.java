package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/API/features",
        glue = { "stepDefinitions"},plugin = {"pretty", "html:target/cucumber-reports"}

)


@RunWith(Cucumber.class)

public class API_TestRunner {
	
}
